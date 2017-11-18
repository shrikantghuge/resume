$(document).ready(function(){
	
	/*Enquiry data submission event*/
	$("#enquiry_button").click(function(){
		var name = $("#name").val();
		var email = $("#email").val();
		var subject = $("#subject").val();
		var message = $("#message").val();
		var isValid = true;		
		if(name=="" || name =="Name Please"){
			$("#name").val("Name Please");
			isValid=false;
		}
		if(email=="" || email=="I would like to be in touch with you"){
			$("#email").val("I would like to be in touch with you");
			isValid = false;
		}
		if(subject=="" || subject=="What it is all about"){
			$("#subject").val("What it is all about");
			isValid = false;
		}
		if(message=="" || message=="Please elaborate"){
			$("#message").val("Please elaborate");
			isValid = false;
		}
		if(isValid==true){
			$.ajax({
				url : "registerEnquiry",
				type:"GET",
				data : { 
					"name":name,
					"email":email,
					"subject":subject,
					"message":message
				},
				contentType:"application/json",
				success : function(data){
					if(data="success"){
						$("#getInTouch").html("I'll get back to you soon. Thanks :) ");
						$(".contact").remove();
					}else{
						$("#getInTouch").html("There is an problem, Would you please mail me on <a href=\"mailto:ghuge.shrikant@gmail.com\">ghuge.shrikant@gmail.com</a> ");
						$(".contact").remove();
					}
										
				}, 
				error : function(){
					$("#getInTouch").html("There is an problem, Would you please mail me on <a href=\"mailto:ghuge.shrikant@gmail.com\">ghuge.shrikant@gmail.com</a> ");
					$(".contact").remove();
				}
			
			});
		}
	});
	
	
	$("#visitor-skip").click(function(){
		$(".first-banner-block").remove();
		$.ajax({
			url:"visitorInfo",
			type:"POST",			
			data : {
				"reqType" : "skip"
			}
		});
	});
	
	$("#visitor-submit").click(function(){
		var isValid = true;
		if($("#visitor-name").val()==""){
			isValid = false;
			document.getElementById("visitor-name").className += " formInvalid";
		}
		if($("#visitor-contact").val()==""){
			isValid = false;
			document.getElementById("visitor-contact").className += " formInvalid";
		}
		if(isValid){
			$.ajax({
				url:"visitorInfo",
				type:"POST",			
				data : {
					"reqType" : "submit",
					"name":$("#visitor-name").val(),
					"contactDetails":$("#visitor-contact").val()
				},
				success: function(data){
					$(".first-banner-block").remove();
				},
				failure : function(){
					$(".first-banner-block").remove();
				}
			});
		}
	});
	
});

