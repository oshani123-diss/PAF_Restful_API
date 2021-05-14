<%@ page import="com.Registration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/users.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
			
				<h1>User Management</h1>
				
				<form id="formPayment" name="formPayment">
 						User Name:
 						<input id="username" name="username" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						Name:
 						<input id="name" name="name" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						Password:
 						<input id="password" name="password" type="text" class="form-control form-control-sm">
 						<br>
 						
 						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 						<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
				</form>

				<br>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divPaymentsGrid">
					<%
						Registration userObjRead = new Registration();
										out.print(userObjRead.readUser());
					%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>