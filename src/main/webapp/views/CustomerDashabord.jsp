<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/home.css">
    <link rel="stylesheet" href="CSS/customerDashabord.css">
    <link rel="stylesheet" href="CSS/ManageTables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" crossorigin="anonymous" />
    <title>Customer Dashboard</title>    
</head>
<body>
    <div class="main">
        <nav>
            <div class="navlogo">
                <img src="img/logo.png" alt="Logo">
            </div>
            <ul>
                <li><a href="/goreservetable"><i class="fa-solid fa-user-tie"></i> Reservation Dining Table</a></li>
                <li><a href="#"><i class="fa-solid fa-user-tie"></i> Customer: ${cutomername}</a></li>
                <li><a href="/logoutcustomer"><i class="fa-solid fa-right-from-bracket"></i> Logout</a></li>
                <li><a href="/viewCart"><i class="fa-solid fa-cart-shopping"></i> Cart</a></li>
            </ul>
        </nav>
        <div class="section12">
            <div class="container mt-5">
                <h2>Your Reservations</h2>
                <div class="reservation-container">
                    <c:forEach items="${myReservations}" var="reservation">
                        <div class="reservation-card">
                            <h3>Table No: ${reservation.diningTable.tableNumber}</h3>
                            <p><strong>Reservation Time:</strong> ${reservation.reservationTime}</p>
                            <p><strong>Guests:</strong> ${reservation.numberOfGuests}</p>
                            <a href="<c:url value='/Deletefreservetab/${reservation.reservationId}'/>" class="btn-delete">Delete</a>
                        </div>
                    </c:forEach>
                </div>
                <a href="/goreservetable" class="btn btn-primary">Make a New Reservation</a>
            </div>
        </div>
        
        <div class="section1244">     
            <c:forEach items="${listfoodsdata}" var="foodatalist">  
                <div class="card74">
                    <div class="card74top">
                        <c:if test="${not empty foodatalist.image}">
                            <img src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(foodatalist.image)}" alt="${foodatalist.foodName}" />
                        </c:if>
                    </div>
                    <div class="card74bottom">
                        <h2>${foodatalist.foodName}</h2>
                        <p>${foodatalist.foodDesc}</p>
                        <p>${foodatalist.foodprice}</p>
						<form action="/addtocart" method="post">
                            <input type="hidden" name="foodId" value="${foodatalist.foodId}">
                            <button type="submit">Add in Cart <i class="fa-solid fa-cart-shopping"></i></button>
                        </form>                    
                   </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>