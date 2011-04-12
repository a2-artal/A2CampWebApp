<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login Page</title>
</head>
<body>

	<form action="LoginServlet" method="post">
		<a>Username: <input name="username" type="text" /></a><br />
		<a>Password: <input name="password" type="password" /></a> <br/>
		<input value="Get connected into the Cloud!" type="submit" />
	</form>

</body>
</html>