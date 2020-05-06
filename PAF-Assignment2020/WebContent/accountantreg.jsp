<%@page import="model.accountantRegister"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accountant Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/account.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Accountant Management</h1>

				<form id="formItem" name="formItem" method="post"
					action="accountantreg.jsp">

					Accountant Code: <input id="Acode" name="Acode" type="text"
						class="form-control form-control-sm"> <br> Accountant
					Name: <input id="AName" name="AName" type="text"
						class="form-control form-control-sm"> <br> Accountant
					NIC No: <input id="ANIC" name="ANIC" type="text"
						class="form-control form-control-sm"> <br> Accountant
					Phone No: <input id="PhoneNo" name="PhoneNo" type="text"
						class="form-control form-control-sm"> <br> Accountant
					Email: <input id="Email" name="Email" type="text"
						class="form-control form-control-sm"> <br> Accountant
					Address: <input id="Address" name="Address" type="text"
						class="form-control form-control-sm"> <br> Accountant
					Password: <input id="Password" name="Password" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Register"
						class="btn btn-primary"> <a class="btn btn-danger"
						href="adminHome.html" role="button">Go To Admin Home Page</a> <input
						type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>

				<div id="divItemsGrid">
					<%
						accountantRegister itemObj = new accountantRegister();
					out.print(itemObj.readItems());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>