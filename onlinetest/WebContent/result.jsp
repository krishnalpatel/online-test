<%@page import="pojo.Userdesc"%>
<%@page import="pojo.Usertemp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>
</head>
<body>
	<h1 align="center">ONLINE TEST RESULT</h1>
	<br>
	<%
	Map<String, Object> model = (Map<String, Object>) request.getAttribute("model");
	Userdesc userdesc = ((Userdesc) model.get("userdesc"));
	%>
	<p align="center">User Id: <%= userdesc.getUid() %></p><br>
	<%if(userdesc.getScore()>80){ %>
	<p align="center">Congratulations, <%=userdesc.getUname()%>!! You cleared the test!!</p><br>
	<%}
	else{%>
	<p align="center">Hard Luck, <%=userdesc.getUname()%>!! You failed the test!!</p><br>
	<%} %>
	
	<br><h3 align="center">Score</h3>
	<table border="1" align="center">
	<tr><td>Category</td><td>Obtained</td><td>Maximum</td></tr>
		
	<tr><td>Designing and Coding</td><td><%=(Integer) model.get("dc") %></td><td><%=(Integer) model.get("dcmax") %></td></tr>	
	<tr><td>Distribution Issues</td><td><%= (Integer) model.get("di")%></td><td><%=(Integer) model.get("dimax") %></td></tr>	
	<tr><td>Security and Signing Issues</td><td><%= (Integer) model.get("ss") %></td><td><%=(Integer) model.get("ssmax") %></td></tr>	
	<tr><td>Total</td><td><%= userdesc.getScore() %></td><td><%=(Integer) model.get("max") %></td></tr>	
		
	
</table>
	<br>
	<h3 align="center">Evaluation</h3>
	<%
	List<Usertemp> usertempList = (List<Usertemp>) model.get("usertemplist");
	%>
<table border="1" align="center">
	<tr><td>Question No.</td><td>Category</td><td>Correct</td><td>Attempted</td></tr>
	<%
	for(Usertemp u: usertempList){
		%>
		
	<tr><td><%=u.getQno() %></td><td><%=u.getCatcode() %></td><td><%= u.getRchoice()%></td><td
	
	<%if(u.getUans()!=null){
		if(!u.getRchoice().equalsIgnoreCase(u.getUans())){ %>
			bgcolor="red"
	<%	} 
	}
	else{%>
		bgcolor="red"
	<%} %>	
	><%=u.getUans() %></td></tr>	
		
	<% 
	}
	%>
</table>

<br>
<br>
<br>
<h3 align="center"><a href='<%=request.getContextPath() + "/index.jsp"%>'>Index</a>
	</h3>
</body>
</html>