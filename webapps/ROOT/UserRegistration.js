$(document).ready(function(){
	$('input[type="submit"]').prop('disabled', true);
	//$("#btnServiceReg").click(function(){ 
		var serviceId=$('#serviceId').val();
		//alert(serviceId);
		$.ajax({url:"/Register?language=english&serviceId="+serviceId,
					success: function(data)
					{
						//alert(data);
						data = data.replace(/\[/gi,"");
						data = data.replace(/\]/gi,"");
						var fieldNames=data.split(",");
						for(fieldCount = 0; fieldCount < fieldNames.length; fieldCount++)
						{														
							$("#fieldset").append("<br>"+fieldNames[fieldCount]+":<input type='text' val='"+fieldNames[fieldCount]+"' name='"+fieldNames[fieldCount]+"' id='"+fieldNames[fieldCount]+"' >");
						}
						//$("#fieldset").append(data);
					}
		});
		$('body').on("click",":checkbox", function(){
	     	if($(this).is(":checked")) 
	     	{
	     		alert("*The registration information provided by a User is valid for 15 days counting from the date of registration.");
	     		$('input[type="submit"]').prop('disabled', false);	
	        }
	        else 
	     	{
	     		$('input[type="submit"]').prop('disabled', true);
	        }
	 
		});
});