<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/cart.css">
    <link rel="stylesheet" href="CSS/home.css">
    <link rel="stylesheet" href="CSS/AdminDashBoard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Cart</title>
    <style>
        /* Existing styles */
        .cart-container {
            width: 80%;
            margin: auto;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background: #f4f4f4;
        }
        .btn-delete {
            background: red;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn-delete:hover {
            background: darkred;
        }
        .btn-checkout, .btn-invoice {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn-checkout {
            background: green;
        }
        .btn-checkout:hover {
            background: darkgreen;
        }
        .btn-invoice {
            background: #007bff; /* Bootstrap primary color */
            margin-left: 10px;
        }
        .btn-invoice:hover {
            background: #0056b3;
        }
        .total-amount {
            margin-top: 20px;
            font-size: 18px;
            font-weight: bold;
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
        <div class="cart-container">
            <h2>Your Cart</h2>
            <table>
                <thead>
                    <tr>
                        <th>Food Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cartItems}" var="cartItem">
                        <tr>
                            <td>${cartItem.food.foodName}</td>
                            <td>${cartItem.quantity}</td>
                            <td>${cartItem.food.foodprice}</td>
                            <td><a href="/removeFromCart/${cartItem.cartItemId}" class="btn-delete">Remove</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="total-amount">Total Amount: <c:out value="${totalAmount}" /></div>
            <a href="/checkout" class="btn-checkout">Proceed to Checkout</a>
            <a href="/generateInvoice" class="btn-invoice">Generate Invoice</a>
        </div>
    </div>
</body>
</html>