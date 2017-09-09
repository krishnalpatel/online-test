<%@page import="pojo.Question"%>
<%@page import="pojo.Usertemp"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start ur test</title>
<script> 
<% 
Map<String, Object> model = (Map<String, Object>) request.getAttribute("model");
int timer = (Integer)(model.get("timer")); 
if (((Boolean) model.get("testStarted")).booleanValue()) {
%> 
var timeout = <%=timer%>; 
function clock() { 
	if( --timeout > 0 ) { 
		if(timeout==30){
			alert("30 seconds remaining!");
		}
		document.testform.clock.value = timeout; 
		document.testform.timer.value = timeout;
		window.setTimeout( "clock()", 1000 ); 
	} else { 
		document.testform.clock.value = "Time over"; 
		alert("Time Over");
		document.getElementById("quit").click();
	} 
} 
<%}%>
</script>
</head>
<body onload="clock()">
	<h1 align="center">Online Test Site</h1>

	<br>
	<%
		//Map<String, Object> model = (Map<String, Object>) request.getAttribute("model");
		try {

			String msg = (String) model.get("message");
			//String uid =(String) model.get("uid");
			if (!msg.isEmpty()) {
	%>
	<p align="center">
		<%
			out.println(msg);
					//out.println("Request.getcontextpath(): "+ request.getContextPath());
		%>
	</p>
	<%
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
	<br>
	<%
		if (!((Boolean) model.get("testStarted")).booleanValue()) {
			//out.println(request.getAttribute("model"));
	%>
	<form action="/onlinetest/qc/starttest" method="get">
		<input type="hidden" name="uid" value="<%=(String) model.get("uid")%>"> 
		<input type="hidden" name="testStarted" value="<%=(Boolean) model.get("testStarted")%>">
		 <input	type="hidden" name="message" value="<%=(String) model.get("message")%>"> 
		 <input	type="hidden" name="timer" value="<%=(Integer) model.get("timer")%>">
		<input type="hidden" name="questionCount" value="<%=(Integer) model.get("questionCount")%>"> 
	<h3 align="center">	<input	align="middle" type="submit" value="Start Test"></h3>
	</form>
	<br>

	<br>

	<h3 align="center">
		Time Remaining:
		<%=model.get("timer")%>
	</h3>

	<%
		} else {
	%>
	<form action="/onlinetest/qc/test" method="get" name="testform">
		<br>
		<h3 align="center">
		Time Remaining:
		<input type="text" name="clock" value="<%=timer%>" style="border:0px solid white" >
	</h3>
		<h3 align="center">Select the best possible choice</h3>
		<br> 
		<% Usertemp usertemp =(Usertemp) model.get("usertemp");
			Question question = usertemp.getQuestion();
		%>
		<h5 align="center"><% out.println(usertemp.getQno()+"\t"+question.getQues());%>
		<br><br>
		<input type="radio" name="ans" value="a" 
		<%if(usertemp.getUans()!=null)
			if(usertemp.getUans().equalsIgnoreCase("a")){%>
			checked="checked"
		<%} %>
		 >A. <%out.println(question.getA()); %><br>
		<input type="radio" name="ans" value="b" 
		<%if(usertemp.getUans()!=null)
			if(usertemp.getUans().equalsIgnoreCase("b")){%>
			checked="checked"
		<%} %>
		>B. <%out.println(question.getB()); %><br>
		<input type="radio" name="ans" value="c" 
		<%if(usertemp.getUans()!=null)
			if(usertemp.getUans().equalsIgnoreCase("c")){%>
			checked="checked"
		<%} %>
		>C. <%out.println(question.getC()); %><br>
		<input type="radio" name="ans" value="d" 
		<%if(usertemp.getUans()!=null)
			if(usertemp.getUans().equalsIgnoreCase("d")){%>
			checked="checked"
		<%} %>
		>D. <%out.println(question.getD()); %><br>
		<input type="hidden" name="questionCount" value="<%=(Integer) model.get("questionCount")%>">
		<input type="hidden" name="uid" value="<%=(String) model.get("uid")%>"> 
		<input type="hidden" name="message" value="<%=(String) model.get("message")%>"> 
		<input type="hidden" name="timer" value="<%=timer%>">
		<br>
		<%if(usertemp.getQno()>1){ %>
		<input type="submit" name="submit" value="First" >
		<input type="submit" name="submit" value="Previous">
		<%}
		if(usertemp.getQno()<10){ %>
		<input type="submit" name="submit" value="Next">
		<input type="submit" name="submit" value="Last">
		<%} %>
		<input type="submit" name="submit" value="Quit" id="quit">
		</h5>
		
		
	</form>

	<%
		}
	%>
</body>
</html>