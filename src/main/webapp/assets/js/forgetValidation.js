$(document).ready(function() {


	$("#email_error").hide();
	$("#password_error").hide();


	var email_error = false;
	var password_error = false;
	var confirm_password_error = false;

	

	$("#mail").focusout(function() {
		$("#email_error").hide();
		$("#mail").css("border", "1px solid #ced4da");
	});
	$("#mail").keyup(function() {
		check_email();
	});
	$("#mail").focus(function() {
		check_email();
	});

	$("#pwd").focusout(function() {
		$("#password_error").hide();
		$("#pwd").css("border", "1px solid #ced4da");
	});
	$("#pwd").keyup(function() {
		check_password();
	});
	$("#pwd").focus(function() {
		check_password();
	});

	$("#cpwd").focusout(function() {
		$("#confirm_password_error").hide();
		$("#cpwd").css("border", "1px solid #ced4da");
	});
	$("#cpwd").keyup(function() {
		check_confirm_password();
	});
	$("#cpwd").focus(function() {
		check_confirm_password();
	});


	function check_email() {
		var pattern = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		var email = $("#mail").val();
		if (email.length == 0) {
			$("#email_error").html("Email can not be empty!!").css("color", "red");
			$("#email_error").show();
			$("#mail").css("border-bottom", "4px solid #F90A0A");
			email_error = true;
		} else if (!pattern.test(email)) {
			$("#email_error").html("Invalid Email!!").css("color", "red");
			$("#email_error").show();
			$("#mail").css("border-bottom", "4px solid #F90A0A");
			email_error = true;
		} else {
			$("#email_error").hide();
			$("#mail").css("border-bottom", "4px solid #34F458");
		}

	}
	function check_password() {
		var password_length = $("#pwd").val().length;
		if (password_length == 0) {
			$("#password_error").html("Password can not be empty!!").css("color", "red");
			$("#password_error").show();
			$("#pwd").css("border-bottom", "4px solid #F90A0A");
			password_error = true;
		} else {
			$("#password_error").hide();
			$("#pwd").css("border-bottom", "4px solid #34F458");
		}
	}

	function check_confirm_password() {
		var password = $("#pwd").val();
		var retype_password = $("#cpwd").val();
		if (retype_password === '') {
			$("#confirm_password_error").html("confirm password can not be empty!!").css("color", "red");
			$("#confirm_password_error").show();
			$("#cpwd").css("border-bottom", "4px solid #F90A0A");
			confirm_password_error = true;
		}
		else if (password !== retype_password) {
			$("#confirm_password_error").html("Passwords Did not Matched").css("color", "red");
			$("#confirm_password_error").show();
			$("#cpwd").css("border-bottom", "4px solid #F90A0A");
			confirm_password_error = true;
		} else {
			$("#confirm_password_error").hide();
			$("#cpwd").css("border-bottom", "4px solid #34F458");
		}
	}


	$("#forget_form").submit(function() {
		email_error = false;
		password_error = false;
		confirm_password_error = false;

		check_email();
		check_password();
		check_confirm_password();

		if (email_error === false && password_error === false && confirm_password_error === false) {
			alert("Password Updated successfully");
			return true;
		} else {
			alert("Please Fill the form Correctly");
			return false;
		}

	});


});