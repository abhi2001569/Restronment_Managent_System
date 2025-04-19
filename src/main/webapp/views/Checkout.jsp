<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/checkout.css">
    <title>Checkout Confirmation</title>
</head>
<body>
    <div class="main">
        <nav>
            <div class="navlogo">
                <img src="img/logo.png" alt="Logo">
            </div>
            <ul>
                <li><a href="/gobackcusdash"><i class="fa-solid fa-arrow-left"></i> Back to Dashboard</a></li>
                <li><a href="/logoutcustomer"><i class="fa-solid fa-right-from-bracket"></i> Logout</a></li>
            </ul>
        </nav>
        <div class="checkout-container">
            <h2>Checkout Confirmation</h2>
            <p>Total Amount: ${payment.totalAmount}</p>
            <p>Payment Date: ${payment.paymentDate}</p>
            <p>Status: ${payment.paymentStatus}</p>
            <a href="/gobackcusdash" class="btn-continue">Continue Shopping</a>
        </div>
    </div>
</body>
</html>