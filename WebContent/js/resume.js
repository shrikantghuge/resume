$(document).ready(function(){
	$("#enquiry_button").click(function(){
		var name = $("#name").val();
		var email = $("#email").val();
		var subject = $("#subject").val();
		var message = $("#message").val();
		var isValid = true;
		console.log(name+email+subject+message);
		if(name=="" || name =="Name Please"){
			$("#name").val("Name Please");
			isValid=false;
		}
		if(email=="" || email=="I would like to be in touch with you"){
			$("#email").va("I would like to be in touch with you");
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
			$.ajax(function(){
				url : "registerEnquiry",
				data : {
					asd:asd
				}
			});
		}
	});
});