package fr.a2artal.a2camp.webapp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.simpledb.util.SimpleDBUtils;

/**
 * Servlet implementation class ConcertTicketConfirmationServlet
 */
public class ConcertTicketConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConcertTicketConfirmationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get:
		//1) concertId
		String concertId = request.getParameter("concertId");
		//2) numberOfTickets
		String numberOfTickets = request.getParameter("numberOfTickets");
		//3) seatNumber
		String seatNumber = request.getParameter("seatNumber");
		//4) creditCardNumber
		String creditCardNumber = request.getParameter("creditCardNumber");
		
		//format a string with these details
		String content2Store = 
			"concertId=" + concertId + 
			"\nnumberOfTickets=" + numberOfTickets +
			"\nseatNumber=" + seatNumber + 
			"\ncreditCardNumber=" + creditCardNumber;
		
		//create a folder for the user (if it does not exist)
		//create file which will have an UUID as name
		//save these values in this file
		UUID uuid = UUID.randomUUID();
		String basePath = request.getSession().getServletContext().getRealPath("/") + File.separator;
		String folderName = basePath + "concert-" + concertId;
		FileDataStore.getInstance().store(folderName, "", uuid.toString() + ".txt", content2Store);
		
		//send it through SQS
		SQSQueue sqsUpdateQueue = new SQSQueue("A2Camp-BI-UpdatesQueue", "AwsCredentials.properties");
		try {
			sqsUpdateQueue.pushData(content2Store);
		} catch (Exception e) {
			response.setStatus(500);
			return;
		}
		
		//return message indicating everything went fine!
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    response.setCharacterEncoding("UTF-8");
	    out.println("<title>Receipt</title><body bgcolor=FFFFFF>");
	    out.println("<h2>Ticket bought! Your account has been charged of 45 Euro!</h2>");
		out.close();
	}

}
