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
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .main {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 80%;
            max-width: 600px;
        }

        nav {
            background-color: #333;
            color: white;
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navlogo img {
            height: 40px;
        }

        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
        }

        nav ul li {
            margin-left: 20px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
        }

        .checkout-container {
            padding: 30px;
            text-align: center;
        }

        .checkout-container h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .checkout-container p {
            margin-bottom: 15px;
            font-size: 1.1em;
            color: #555;
        }

        .btn-continue {
            display: inline-block;
            padding: 10px 20px;
            background-color: #5cb85c;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

        .btn-continue:hover {
            background-color: #4cae4c;
        }
    </style>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/js/all.min.js" integrity="sha512-qzUkQrWjK0F66tCykAaattGD9OVcjswpFdrj3YjdqeVcRQVAejZ/tWvhkp9v1DAWY5S3nRerlCQf05vEM67vYQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>