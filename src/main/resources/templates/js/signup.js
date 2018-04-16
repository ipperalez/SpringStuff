$("document").ready(function() {
	/* validation */
	$("#register-form").validate({
		rules: {
			username: {
				required: true,
				minlength: 4,
				maxlength: 15
			},
			password: {
				required: true,
				minlength: 6,
				maxlength: 25
			},
			cpassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
		},
		messages: {
			username: "please enter user name",
			password: {
				required: "please provide a password",
				minlength: "password must have at least 6 characters"
			},
			email: "please enter a valid email address",
			cpassword: {
				required: "please retype your password",
				equalTo: "password doesn't match"
			}
		},
		submitHandler: submitForm
	});
	/* end validation */

	/* form submission */
	function submitForm() {
		var data = $("#register-form").find('input[name!=cpassword]').serialize();
		
		console.log(data);
		var urlString = "http://localhost:8080/registration?" + data;

		$.ajax({
			url: `${urlString}`,
			method: "POST",
			dataType: "json",
			contentType: "text/plain;charset=UTF-8",
			success: function() {
				alert("account created");
			}
		});
	}
	/* end form submission */
});