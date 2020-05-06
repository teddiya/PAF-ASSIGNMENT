$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{		
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var method = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "accountantAPI",
		type : method,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onItemSaveComplete(response.responseText, status);
		}
	});
});

function onItemSaveComplete(response, status)
{	
	if (status == "success")
	{
		//console.log(response);
		//var resultSet = JSON.parse(response);
		//console.log(resultSet);
		//if (resultSet.status.trim() == "success")
	//	{
			console.log('trim success');
			$("#alertSuccess").text("Successfully Registerd Accontant.");
			$("#alertSuccess").show();
			//$("#divItemsGrid").html(resultSet.data);
			$("#divItemsGrid").load(" #divItemsGrid > *");
	//	} 
	//	else if (resultSet.status.trim() == "error")
	//	{
	//		$("#alertError").text(resultSet.data);
	//		$("#alertError").show();
	//	}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#Acode").val($(this).closest("tr").find('td:eq(0)').text());
	$("#AName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#ANIC").val($(this).closest("tr").find('td:eq(2)').text());
	$("#PhoneNo").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Email").val($(this).closest("tr").find('td:eq(4)').text());
	$("#Address").val($(this).closest("tr").find('td:eq(5)').text());
	$("#Password").val($(this).closest("tr").find('td:eq(6)').text());
});


//REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "accountantAPI",
		type : "DELETE",
		data : "AID=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		//var resultSet = JSON.parse(response);
		
		//if (resultSet.status.trim() == "success")
		//{
			$("#alertSuccess").text("Successfully deleted Accontant.");
			$("#alertSuccess").show();
			$("#divItemsGrid").load(" #divItemsGrid > *");
			
			//$("#divItemsGrid").html(resultSet.data);
		//} 
		//else if (resultSet.status.trim() == "error")
		//{
			//$("#alertError").text(resultSet.data);
			//("#alertError").show();
		//}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


function validateItemForm()
{
	// CODE
	if ($("#Acode").val().trim() == "")
	{
		return "Insert accontant Code.";
	}
	
	// NAME
	if ($("#AName").val().trim() == "")
	{
		return "Insert accontant Name.";
	}
	
	//nic-------------------------------
	if ($("#ANIC").val().trim() == "")
	{
		return "Insert accontant NIC no.";
	}
	
	// phone------------------------
	if ($("#PhoneNo").val().trim() == "")
	{
		return "Insert accontant Phone No.";
	}
	
	// email------------------------
	if ($("#Email").val().trim() == "")
	{
		return "Insert accontant Email.";
	}
	
	// address------------------------
	if ($("#Address").val().trim() == "")
	{
		return "Insert accontant Address.";
	}
	
	// password------------------------
	if ($("#Password").val().trim() == "")
	{
		return "Insert Pharmacist Password.";
	}
	
	return true;
}