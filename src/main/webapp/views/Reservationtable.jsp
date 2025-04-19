<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/home.css">
    <link rel="stylesheet" href="CSS/RegisterLogin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Reservation Table</title>
</head>
<body>

    <div class="main">
        <div class="nav">
            <div class="navlogo">
                <img src="img/logo.png" alt="Logo">
            </div>
            <ul>
                <li><i class="fa-solid fa-list"></i> Reservation Table</li>
                <li><a href="/logoutcustomer"><i class="fa-solid fa-arrow-left"></i> Logout</a></li>
            </ul>
        </div>

        <div class="sectionregister">
            <div class="formregi">

            	<c:if test="${not empty successmessage}">
                    <div class="alert text-white bg-primary alert-primary fade show" role="alert">
					  ${successmessage}
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>

                </c:if>

                <c:if test="${not empty errormessage}">
                    <div class="alert text-white bg-danger alert-primary fade show" role="alert">
					  ${errormessage}
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>

                </c:if>

                <form action="/savediingtable" method="post">
                <img alt="img/chair.svg" class="imagetable" src="img/chair.svg">

                    <div class="forminner">
                        <label for="mobile"><i class="fa-solid fa-utensils"></i></label>
                        <select name="diningTable.tableId" required>
							    <option value="" disabled="disabled" selected="selected">Select Dining Table</option>
							    <c:forEach var="table" items="${diningTables}">
							        <option value="${table.tableId}">${table.tableNumber} (Capacity: ${table.tablCapacity})</option>
							    </c:forEach>
						</select>
                    </div>

                    <div class="forminner">
                        <label for="email"> <img alt="img/chair.svg" src="img/chair.svg"> </label>
                        <input type="datetime-local" required="required" placeholder="Enter Reservation Date and Time" name="reservationTime">
                    </div>

                    <div class="forminner">
                        <label for="state"><i class="fa-solid fa-users"></i></label>
                        <input type="number" id="state" required="required" name="numberOfGuests" placeholder="Enter No of Guest">
                    </div>

                    <div class="btnlogin">
                        <button type="submit" class="submit-btn">Reserve Table</button>
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
```