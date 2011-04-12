package fr.a2artal.a2camp.webapp;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get parameters: username/password
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//simple validation: username = password
		if(username == null || password == null || !username.equals(password)) {
			response.setStatus(403);
			request.setAttribute("error", "Wrong Username/Password !!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		//add user in session
		request.getSession().setAttribute("username", username);
		
		//set server adress
		request.getSession().setAttribute("hostname", InetAddress.getLocalHost().getHostName());
		request.getSession().setAttribute("hostaddress", InetAddress.getLocalHost().getHostAddress());
		
		//return appropriated page
		request.getRequestDispatcher("/loggedInPage.jsp").forward(request, response);
	}

}
