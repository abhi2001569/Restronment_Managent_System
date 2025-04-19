<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/CSS/home.css">
    <link rel="stylesheet" href="/CSS/RegisterLogin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Category</title>
</head>
<body>

    <div class="main">
        <div class="nav">
            <div class="navlogo">
                <img src="/img/logo.png" alt="Logo">
            </div>
            <ul>
                <li><i class="fa-solid fa-list"></i> Edit Category Form</li>
                <li><a href="/aisplayfoodcategory"><i class="fa-solid fa-arrow-left"></i> Back</a></li>
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
                
                <form action="/update/${cate_id}" method="post">
                    <div class="forminner">
                        <input type="text" id="fullname" name="categoryName" value="${foodCatedata.categoryName}" placeholder="Enter Food Category Name">

                    </div>               

        
                    <div class="btnlogin">
                        <button type="submit" class="submit-btn">Add Category</button>
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

