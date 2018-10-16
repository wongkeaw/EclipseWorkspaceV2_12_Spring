<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
	</head>
	<body>
		<font color="red">${message}</font>
		<s:form action="login">
      <s:textfield name="username" label="Enter User Name"/><br/>
      <s:password name="password" label="Enter Password"/><br/>
      <s:submit></s:submit>
   </s:form>
	</body>
</html>