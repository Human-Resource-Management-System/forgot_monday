
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
/* Centering container vertically and horizontally */
body {
	background-color: #E6F1FF;
}

#container {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

#left-column {
	flex: 1;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

#right-column {
	flex: 1;
}

#message {
	padding: 20px;
	text-align: center;
}

.message-title {
	font-size: 24px;
	font-weight: bold;
	color: #333;
	margin-bottom: 10px;
}

.message-text {
	font-size: 18px;
	color: #666;
	margin-bottom: 0;
}

#data {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
}

#btn-div {
	text-align: center;
}

.error-msg {
	margin-top: 10px;
	text-align: center;
	color: red;
}

@media ( max-width : 768px) {
	#container {
		flex-direction: column;
	}
	#data {
		margin-right: 0;
		margin-bottom: 20px;
	}
}

.success-msg {
	color: green;
}

/* Centering form elements */
#myform {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

/* Add styles to input fields */
.form-group input[type="email"], .form-group input[type="text"],
	.form-group input[type="password"] {
	width: 300px; /* Adjust the width as desired */
	padding: 10px;
	border-radius: 5px;
	border: none;
	background-color: #ededed;
	box-shadow: inset 2px 2px 5px rgba(0, 0, 0, 0.1), inset -2px -2px 5px
		rgba(255, 255, 255, 0.7);
	outline: none;
}

/* Add padding to elements */
.form-group {
	padding: 10px;
}

/* Add styles to buttons */
.btn {
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	background-color: #007bff;
	color: #fff;
	cursor: pointer;
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2), -2px -2px 5px
		rgba(255, 255, 255, 0.7);
}

.error-msg {
	color: red;
	font-weight: bold;
	margin-top: 5px; /* Reduce the top margin */
	font-size: 12px; /* Adjust the font size as desired */
	text-align: center; /* Center the error message */
}

#timer {
	font-size: 14px;
	text-align: center;
	margin-top: 10px;
}

.success-message, .error-message {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 200px;
	width: 400px;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	font-size: 24px;
	font-weight: bold;
	text-align: center;
}

.success-message {
	color: #155724; /* Dark green text color */
	background-color: #d4edda; /* Light green background color */
}

.error-message {
	color: #721c24; /* Dark red text color */
	background-color: #f8d7da; /* Light red background color */
}

.success-message .tick {
	font-size: 48px;
	margin-right: 10px;
	color: #00b300; /* Green color for the tick mark */
}

.error-message .cross {
	font-size: 48px;
	margin-right: 10px;
	color: #e60000; /* Red color for the cross mark */
}
</style>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

	<div id="container">
		<div id="left-column">
			<div id="message" class="form-group">
				<h3 class="message-title">Forgot Password?</h3>
				<p class="message-text">Don't worry, we're here to help you
					recover your account!</p>
			</div>
		</div>
		<div id="right-column">
			<form id="myform">
				<div id="data" class="form-group">
					<label for="mail">Email</label> <input type="email" id="mail"
						placeholder="Enter email" name="email" class="form-control">

					<div id="otp" class="form-group" style="display: none;">
						<label for="otpin">OTP</label> <input type="text" id="otpin"
							placeholder="Enter OTP" name="otp" class="form-control">
					</div>
					<div id="msg" class="form-group"></div>
					<div id="timer" class="form-group"></div>
				</div>
				<div id="btn-div" class="form-group">
					<input type="button" id="btn" value="Send OTP" onclick="ajaxMail()"
						class="btn btn-primary">
				</div>
				<div id="password-error" class="error-msg"></div>
			</form>
		</div>


	<script>
  
  var timerInterval;
  var remainingTime = 120; // 2 minutes in seconds
  var otpValidated = false;
  
  
  function startTimer() {
    timerInterval = setInterval(updateTimer, 1000); // Update the timer every second
  }

  function updateTimer() {
    if (remainingTime > 0) {
      var minutes = Math.floor(remainingTime / 60);
      var seconds = remainingTime % 60;

      // Display the remaining time on the component
      $("#timer").text("Time remaining: " + minutes + "m " + seconds + "s");

      if (remainingTime <= 10) {
          $("#timer").css("color", "red");
        }
      
      remainingTime--;
    } else {
      // Timer has expired
      clearInterval(timerInterval);

      // Display the time's up message
      $("#timer").text("Time's up!");

      // Redirect to index.jsp after 2 seconds
      if(!otpValidated){
      setTimeout(function() {
        window.location.href = "index.jsp";
      }, 1000);
      }
    }
  }
  
  
    function ajaxMail() {
    	
    	var email = $("#mail").val();
        var emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/;

        if (!emailPattern.test(email)) {
          $("#msg").html("Invalid email address. Please enter a valid email.")
                  .removeClass("success-msg")
                  .addClass("error-msg")
                  .show();
          return;
        }
    	
        // email data
        console.log($("#myform").serialize())
        
       // ajax call to send otp to the registered email
      $.ajax({
        url: "sendOtpmail",
        type: "GET",
        data: $("#myform").serialize(),
        contentType: "application/x-www-form-urlencoded",
        success: function(response) {
          var submitbtn = $("<button>")
            .attr("type", "button")
            .html("<b>Submit</b>")
            .addClass("btn btn-primary")
            .click(function() {
            	
            	// mail-otp data
            	console.log($("#myform").serialize());
            	
              $.ajax({
                url: "otpvalidate",
                type: "POST",
                data: $("#myform").serialize(),
                contentType: "application/x-www-form-urlencoded",
                success: function(response) {
                	console.log("getting");
                	
                	otpValidated = true;
                	
                // password field
                  var newField = $("<div>").addClass("passwordField form-group");
                  var passlabel = $("<label>").attr("for", "password1").text("Password");
                  var pasinput = $("<input>").attr({
                    type: "password",
                    id: "password1",
                    name: "password"
                  }).addClass("form-control");
                  
                  
                  // confirm password field

                  var Field = $("<div>").addClass("confirmField form-group");
                  var conlabel = $("<label>").attr("for", "confirm1").text("Confirm Password");
                  var coninput = $("<input>").attr({
                    type: "password",
                    id: "confirm1",
                    name: "confirm"
                  }).addClass("form-control");

                  $("#data").empty();
                  newField.append(passlabel);
                  newField.append(pasinput);
                  Field.append(conlabel);
                  Field.append(coninput);
                  $("#data").append(newField);
                  $("#data").append(Field);
                  
                  
                  var passwordErrorDiv = $('<div>').attr('id', 'password-error').addClass('error-msg');
                  $('#data').append(passwordErrorDiv);
                  $("#password-error").hide();

                  var changebtn = $("<button>")
                    .attr("type", "button")
                    .html("<b>Change Password</b>")
                    .addClass("btn btn-primary")
                    .click(function() {
                    	
                    	// password validaton
                    	
                    	
                    	// password data
                    	console.log($("#myform").serialize());
                         
                    	
                    	$("#confirm-error").hide();
                
                    	
                         var password = $("#password1").val();
                         var confirm = $("#confirm1").val();
                         
                         
                         
                      // Validate password
                         if (password.length < 8) {
                           $("#password-error").text("Password must be at least 8 characters long.").show();
                         }

                         else if (!/[A-Z]/.test(password)) {
                           $("#password-error").text("Password must contain at least one uppercase letter.").show();
                         }

                         else if (!/[a-z]/.test(password)) {
                           $("#password-error").text("Password must contain at least one lowercase letter.").show();
                           
                         }

                         else if (!/\d/.test(password)) {
                           $("#password-error").text("Password must contain at least one digit.").show();
                           
                         }

                         else if (!/[!@#$%^&*]/.test(password)) {
                           $("#password-error").text("Password must contain at least one special character (!@#$%^&*).").show();
                           
                         }

                         // Validate confirm password
                         else if (password !== confirm) {
                           $("#password-error").text("Confirm password does not match.").show();
                           
                         }
                      
                         else{
                    	
                        	 console.log("hello");
                        	 
                        $.ajax({
                        url: "changepassword",
                        type: "POST",
                        data: $("#myform").serialize(),
                        contentType: "application/x-www-form-urlencoded",
                        success: function(response) {
                        	 var successMessage = $("<div>")
                             .addClass("success-message")
                             .html('<span class="tick">&#10004;</span>Password Update Successful');

                           $("#container").empty().append(successMessage);

                           // Redirect to index.jsp after 2 seconds
                           setTimeout(function() {
                             window.location.href = "login.jsp";
                           }, 2000);
                            

                        },
                        error: function(xhr,status,error) {
                        	// otp validate error handling
                        	 $("#msg").html("Otp does not match")
                             .removeClass("success-msg")
                             .addClass("error-msg")
                             .show();
                        }
                      }); 
                    }
                    
                    
                    
                    });

                  $("#btn-div").empty().append(changebtn);
                  
                },
                error: function(xhr,status,error) {
                	 var errorMessage = $("<div>")
                     .addClass("error-message")
                     .html('<span class="cross">&#10008;</span>Something went wrong. Please try again.');

                   $("#container").empty().append(errorMessage);
                     
                     setTimeout(function() {
                         window.location.href = "index.jsp";
                       }, 2000);
                }
              });
            });

          $("#btn-div").empty().append(submitbtn);
          $("#msg").html(response)
          .removeClass("error-msg")
          .addClass("success-msg")
          .show();
          startTimer();
          $("#otp").show();
        },
        error: function(xhr,status,error) {
        	
        	// cases for email
        	
        	  $("#msg").html("Not a registered User")
              .removeClass("success-msg")
              .addClass("error-msg")
              .show();
        }
      });
    }
  </script>

</body>
</html>