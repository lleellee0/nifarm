<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
</head>
<body>
<form id="loginForm" method="post" action="/sketchbook/login">
	ID:	<input type="text" name="id" /> <input type="submit" value="send"/>
</form>
</body>
</html>
