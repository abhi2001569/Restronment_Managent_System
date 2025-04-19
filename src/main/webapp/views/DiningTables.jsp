<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/home.css">
    <link rel="stylesheet" href="CSS/dingingtables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Dining Tables</title>
</head>
<body>

    <div class="main">
        <div class="nav">
            <div class="navlogo">
                <img src="img/logo.png" alt="Logo">
            </div>
            <ul>
                <li><i class="fa-solid fa-utensils"></i> Check Availability Dining Tables</li>
                <li><a href="/"><i class="fa-solid fa-arrow-left"></i> Back</a></li>
                <li><a href="/reserve-table" class="btn btn-success">Make a Reservation</a></li> </ul>
        </div>

        <div class="sectionregister">
            <div class="dinnertable">
                <c:forEach items="${displaydining}" var="dingingdata">
                    <div class="tables card text-white
                        ${dingingdata.tableAvailable eq 'Booked' ? 'bg-primary' : 'bg-danger'}">
                        <div class="row g-0 align-items-center">
                            <div class="col-md-4">
                                <img src="img/dinng.png" class="img-fluid rounded-start" alt="Dining Table">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">Table No: ${dingingdata.tableNumber}</h5>
                                    <p class="card-text">Capacity: ${dingingdata.tablCapacity}</p>
                                    <p class="card-text">Status: ${dingingdata.tableAvailable}</p>
                                    </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</body>
</html>