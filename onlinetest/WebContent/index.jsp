<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

	<h1 align="center">Welcome to Online Test Site</h1>

	<br>
	<br>
	<%
		try {
			String msg = (String) request.getAttribute("message");
			if (!msg.isEmpty()) {
	%>
	<p>
		<%
			out.print(msg);
		%>
	</p>
	<%
			}
		} catch (Exception e) {
		}
	%>
	<br>
	<br>
	<h3>
		Already Registered? <a
			href='<%=request.getContextPath() + "/login.jsp"%>'>Login</a>
	</h3>
	<br>
	<br>
	<h3>
		New User? <a href='<%=request.getContextPath() + "/register.jsp"%>'>Register</a>
	</h3>


</body>
</html>