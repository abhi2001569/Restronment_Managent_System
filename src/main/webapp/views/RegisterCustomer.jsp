<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/home.css">
    <link rel="stylesheet" href="CSS/RegisterLogin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Register</title>
    <style>
        .error-message {
            color: red;
            font-size: 0.9em;
            margin-top: 5px;
        }
    </style>
</head>
<body>

    <div class="main">
        <div class="nav">
            <div class="navlogo">
                <img src="img/logo.png" alt="Logo">
            </div>
            <ul>
                <li><i class="fa-solid fa-id-card"></i> Register Customer</li>
                <li><a href="/"><i class="fa-solid fa-arrow-left"></i> Back</a></li>
            </ul>
        </div>

        <div class="sectionregister">
            <div class="formregi">
                <%-- Display error message if it exists --%>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">${errorMessage}</div>
                </c:if>

                <form action="/addsavecustomer" method="post">
                    <div class="forminner">
                        <label for="fullname"><i class="fa-solid fa-user"></i></label>
                        <input type="text" id="fullname" name="custName" placeholder="Enter Full Name">
                    </div>

                    <div class="forminner">
                        <label for="mobile"><i class="fa-solid fa-mobile-screen-button"></i></label>
                        <input type="text" id="mobile" name="mobileNo" placeholder="Enter Mobile No">
                    </div>

                    <div class="forminner">
                        <label for="email"><i class="fa-solid fa-envelope"></i></label>
                        <input type="email" name="gmailId" placeholder="Enter Email-ID">
                    </div>

                    <div class="forminner">
                        <label for="state"><i class="fa-solid fa-location"></i></label>
                        <input type="text" id="state" name="state" placeholder="Enter State">
                    </div>

                    <div class="forminner">
                        <label for="city"><i class="fa-solid fa-city"></i></label>
                        <input type="text" id="city" name="city" placeholder="Enter City">
                    </div>

                    <div class="forminner">
                        <label for="password"><i class="fa-solid fa-lock"></i></label>
                        <input type="password" id="password" name="password" placeholder="Enter Password">
                    </div>

                    <div class="forminner">
                        <label for="address"><i class="fa-solid fa-location-dot"></i></label>
                        <textarea id="address" placeholder="Enter Address" name="address"></textarea>
                    </div>


                    <div class="btnlogin">
                        <input type="submit" class="submit-btn" value="Regiter">
                        <a href="/logincustomer"> Already Account ? Login </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>