$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});



///CLIENT-MODEL================================================================
function validatePaymentForm() {
	
    // CODE-------------------------------------
    if ($("#username").val().trim() == "") {
        return "Insert User Name";
    }
    // TYPE-------------------------------------
    if ($("#name").val().trim() == "") {
        return "Insert Name";
    }
    // TOTALPRICE-------------------------------
    if ($("#password").val().trim() == "") {
        return "Insert Password.";
    }
    return true;
}



///SAVE-BUTTON================================================================
$(document).on("click", "#btnSave", function (event) 
{
    // Clear alerts
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    
    // Form validation
    var status = validatePaymentForm();
    if (status != true) 
    {
        $("#alertError").text(status);
        $("#alertError").show();
        
        return;
    }
    
    // If valid
    var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "usersAPI",
            type: type,
            data: $("#formPayment").serialize(),
            dataType: "text",
            complete: function (response, status) {
            	onUserSaveComplete(response.responseText, status);
            }
        });
});


function onUserSaveComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully saved.");
	    				$("#alertSuccess").show();
	    				
	    				$("#divPaymentsGrid").html(resultSet.data);
    			} 
    			else if (resultSet.status.trim() == "error") 
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
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
    	
    	$("#hidPaymentIDSave").val("");
    	$("#formPayment")[0].reset();
}


///UPDATE-BUTTON================================================================
$(document).on("click", ".btnUpdate", function (event) 
{
    	$("#hidPaymentIDSave").val($(this).data("userid"));
    	$("#username").val($(this).closest("tr").find('td:eq(0)').text());
    	$("#name").val($(this).closest("tr").find('td:eq(1)').text());
    	$("#password").val($(this).closest("tr").find('td:eq(2)').text());
});


///DELETE-BUTTON================================================================
$(document).on("click", ".btnRemove", function (event) 
{
    $.ajax(
        {
            url: "usersAPI",
            type: "DELETE",
            data: "userid=" + $(this).data("userid"),
            dataType: "text",
            complete: function (response, status) 
            {
            	onUserDeleteComplete(response.responseText, status);
            }
        });
});


function onDeleteComplete(response, status) 
{
    	if (status == "success") 
    	{
    			var resultSet = JSON.parse(response);
    			
    			if (resultSet.status.trim() == "success") 
    			{
    					$("#alertSuccess").text("Successfully deleted.");
    					$("#alertSuccess").show();
    					
    					$("#divPaymentsGrid").html(resultSet.data);
    			}
    			
    			else if (resultSet.status.trim() == "error")
    			{
    					$("#alertError").text(resultSet.data);
    					$("#alertError").show();
    			}
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