<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/invoice.css">
    <title>Invoice</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .invoice-container {
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 100%;
        }

        .invoice-header {
            text-align: center;
            padding-bottom: 20px;
            border-bottom: 2px solid #007bff;
        }

        .invoice-header h2 {
            color: #007bff;
            margin: 0;
        }

        .customer-info, .order-details {
            margin: 20px 0;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background: #f9f9f9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            background: white;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        .total-amount {
            text-align: right;
            font-size: 1.2em;
            font-weight: bold;
            margin-top: 10px;
        }

        .payment-status {
            text-align: right;
            font-style: italic;
            color: green;
        }

        .download-button {
            display: block;
            margin: 20px auto;
            padding: 12px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
            width: 100%;
        }

        .download-button:hover {
            background-color: #0056b3;
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
</head>
<body>
    <div class="invoice-container">
        <div class="invoice-header">
            <h2>Invoice</h2>
        </div>

        <div class="customer-info">
            <h3>Customer Information</h3>
            <p><strong>Name:</strong> ${customer.custName}</p>
            <p><strong>Email:</strong> ${customer.gmailId}</p>
            <p><strong>Mobile No:</strong> ${customer.mobileNo}</p>
            <p><strong>State:</strong> ${customer.state}</p>
            <p><strong>Address:</strong> ${customer.address}</p>
        </div>

        <div class="order-details">
            <h3>Order Items</h3>
            <table>
                <thead>
                    <tr>
                        <th>Food Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cartItems}" var="item">
                        <tr>
                            <td>${item.food.foodName}</td>
                            <td>${item.quantity}</td>
                            <td>&#8377; ${item.food.foodprice}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="total-amount">
            <strong>Total Amount:</strong> &#8377; <c:out value="${totalAmount}" />
        </div>

        <div class="payment-status">
            <strong>Payment Status:</strong> ${paymentStatus}
        </div>

        <button class="download-button" onclick="downloadInvoice()">Download Invoice</button>
    </div>

    <script>
        function downloadInvoice() {
            const invoiceContainer = document.querySelector('.invoice-container');
            html2canvas(invoiceContainer).then(canvas => {
                const imgData = canvas.toDataURL('image/png');
                const pdf = new jspdf.jsPDF('p', 'mm', 'a4');
                const imgWidth = 210;
                const imgHeight = (canvas.height * imgWidth) / canvas.width;
                pdf.addImage(imgData, 'PNG', 0, 0, imgWidth, imgHeight);
                pdf.save('invoice.pdf');
            });
        }
    </script>
</body>
</html>
