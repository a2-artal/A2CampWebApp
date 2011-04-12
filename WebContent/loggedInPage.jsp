<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>User connected !</title>
</head>
<body>

	<h1>Congratulation '<%=session.getAttribute("username")%>', you're connected !!!</h1>
	
	Info about the machine on which you're connected:<br />
	<ul>
		<li>Machine name: '<%=session.getAttribute("hostname")%>'</li>
		<li>Machine address: '<%=session.getAttribute("hostaddress")%>'</li>
	</ul>
	
	<h1>Confirm ticket, price is 45 Euro</h1>
	<form action="ConcertTicketConfirmationServlet" method="post">
		Concert name: <input name="concertId" type="text"/><br />
		Number of tickets: <input name="numberOfTickets" type="text"/><br />
		Seat number: <input name="seatNumber" type="text"/><br />
		Credit card number: <input name="creditCardNumber" type="text"/><br />
		<input value="Buy !" type="submit"/>
	</form>

</body>
</html>