<%@page import="model.PharmacistRegister"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pharmacist Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/pharmacist.js"></script>

</head>
<body>
<div class="container">
	<div class="row" >
		<div class="col-6">
			<h1>Pharmacist Management</h1>
			
			<form id="formItem" name="formItem" method="post" action="pharmacistreg.jsp">

				Pharmacist Code:
				<input id="Pcode" name="Pcode" type="text" class="form-control form-control-sm">
				<br>
				 
				Pharmacist Name:
				<input id="PName" name="PName" type="text" class="form-control form-control-sm">
				<br>
				
				Pharmacist NIC No:
				<input id="PNIC" name="PNIC" type="text" class="form-control form-control-sm">
				<br>
				 
				Pharmacist Phone No:
				<input id="PhoneNo" name="PhoneNo" type="text" class="form-control form-control-sm">
				<br>
				
				Pharmacist Email:
				<input id="Email" name="Email" type="text" class="form-control form-control-sm">
				<br>
				
				Pharmacist Address:
				<input id="Address" name="Address" type="text" class="form-control form-control-sm">
				<br>
				
				Pharmacist Password:
				<input id="Password" name="Password" type="text" class="form-control form-control-sm">
				<br>
				
				
				<input id="btnSave" name="btnSave" type="button" value="Register" class="btn btn-primary">
				<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			</form>
			
			<div id="alertSuccess" class="alert alert-success">Registered Successfully</div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>

			<div id="divItemsGrid">
				<%
					PharmacistRegister itemObj = new PharmacistRegister();
					out.print(itemObj.readItems());
				%>
			</div>
		</div>
	</div>
</div>
</body>
</html>