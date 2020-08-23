<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>

<body>
<jsp:include page="header.jsp"/>

<nav>
<a href="homepage">Home Page</a>
</nav>
<hr />
<br/>
<br/>
<form action="visitorlogin" method="post">

<div>
			<div><label for="username">UserName</label> </div>
			<div><input type="text" id="username" name="username" value="${visitorprof.userName}" required> </div>
		</div>
		<br/>
		<div>
			<div><label for="email">User Email ID</label> </div>
			<div><input type="text" id="email" name="email" value="${visitorprof.emailid}" required> </div>
		</div>
		<br/>
		<div>
			<div><label for="contact">User Contact Number</label> </div>
			<div><input type="text" id="contact" name="contact" maxlength="10" minlength ="10" value="${visitorprof.contact}" required> </div>
		</div>
		
		<div>
		<br/>
			<div><input type="submit" value="Login as visitor"> </div>
		</div>

</form>



<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>