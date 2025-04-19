<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/home.css">
    <link rel="stylesheet" href="CSS/ManageTables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/brands.min.css" integrity="sha512-58P9Hy7II0YeXLv+iFiLCv1rtLW47xmiRpC1oFafeKNShp8V5bKV/ciVtYqbk2YfxXQMt58DjNfkXFOn62xE+g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/fontawesome.min.css" integrity="sha512-v8QQ0YQ3H4K6Ic3PJkym91KoeNT5S3PnDKvqnwqFD1oiqIl653crGZplPdU5KKtHjO0QKcQ2aUlQZYjHczkmGw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Manage Users</title>
</head>
<body>
    <div class="main">

        <nav>
            <div class="navlogo">
                <img src="img/logo.png" alt="">
            </div>
            <ul>                
                <li><i class="fa-solid fa-right-to-bracket"></i> Manage Users</li>
                <li><a href="/admindashbaord"><i class="fa-solid fa-arrow-left"></i> Back</a></li>
            </ul>
        </nav>      

        <div class="section1244">      
        
        		<c:if test="${not empty successmessage}">
                    <div class="alert text-white bg-primary alert-primary fade show" role="alert">
					  ${successmessage}
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>

                </c:if>       
            <table>
                <thead>
                    <tr>
                        <th>custId</th>
                        <th>custName</th>
                        <th>gmailId</th>
                        <th>mobileNo</th>
                        <th>state</th>
                        <th>city</th>
                        <th>address</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${userlist}" var="user_data">
	                    <tr>
	                        <td>${user_data.custId}</td>
	                        <td>${user_data.custName}</td>
	                        <td>${user_data.gmailId}</td>
	                        <td>${user_data.mobileNo}</td>
	                        <td>${user_data.state}</td>
	                        <td>${user_data.city}</td>
	                        <td>${user_data.address}</td>
	                        <td><a href="<c:url value='editdinging/${user_data.custId}'/>"><i class="fa-solid fa-pen-to-square"></i></a>  <a href="<c:url value='/deletefoodCate/${user_data.custId}'/>"><i class="fa-solid fa-trash-can"></i></a></td>
	                    </tr>                    	
                    </c:forEach>
                    
                </tbody>
            </table>
        </div>
        
        
    </div>    
</body>
</html>