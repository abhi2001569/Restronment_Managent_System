<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/home.css">
    <link rel="stylesheet" href="CSS/AdminDashBoard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/brands.min.css" integrity="sha512-58P9Hy7II0YeXLv+iFiLCv1rtLW47xmiRpC1oFafeKNShp8V5bKV/ciVtYqbk2YfxXQMt58DjNfkXFOn62xE+g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/fontawesome.min.css" integrity="sha512-v8QQ0YQ3H4K6Ic3PJkym91KoeNT5S3PnDKvqnwqFD1oiqIl653crGZplPdU5KKtHjO0QKcQ2aUlQZYjHczkmGw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Document</title>
</head>
<body>
    <div class="main">

        <nav>
            <div class="navlogo">
                <img src="img/logo.png" alt="">
            </div>

            <ul>
                
                <li><a href=""><i class="fa-solid fa-user-tie"></i> Admin : ${adminName}</a></li>
                <li><a href="/logoutadmin"><i class="fa-solid fa-right-from-bracket"></i> Logout</a></li>
            </ul>
        </nav>      

        <div class="section12">
        
        	<div class="card142">
                <div class="leftcard">
                    <span><i class="fa-solid fa-user"></i></span>
                </div>
                <div class="rightcard">
                    <span>Total Food Category</span>
                    <span>${FoodCatecount}</span>
                    <a href="/aisplayfoodcategory"><button>Manage</button></a>
                </div>

            </div>

            <div class="card142">
                <div class="leftcard">
                    <span><i class="fa-solid fa-user"></i></span>
                </div>
                <div class="rightcard">
                    <span>Total Users</span>
                    <span>${userscount}</span>
                    <a href="/displayusersdata"><button>Manage</button></a>
                </div>

            </div>

            <div class="card142">
                <div class="leftcard">
                    <span><i class="fa-solid fa-utensils"></i></span>
                </div>
                <div class="rightcard">
                    <span>Dinging Tables</span>
                    <span>${diningtablecount}</span>
                    <a href="/displayingingtables"><button>Manage</button></a>
                </div>

            </div>

            <div class="card142">
                <div class="leftcard">
                    <span><i class="fa-solid fa-user-check"></i></span>
                </div>
                <div class="rightcard">
                    <span>Reservations </span>
                    <span>${tablereservationcount }</span>
                    <a href="/displayReservationdata"><button>Manage</button></a>
                </div>

            </div>

            <div class="card142">
                <div class="leftcard">
                    <span><i class="fa-solid fa-utensils"></i></span>
                </div>
                <div class="rightcard">
                    <span>Foods </span>
                    <span>${foodcount }</span>
                    <a href="/displayfood"><button>Manage</button></a>
                </div>

            </div>            

            <div class="card142">
                <div class="leftcard">
                    <span><i class="fa-solid fa-money-check-dollar"></i></span>
                </div>
                <div class="rightcard">
                    <span>Payments </span>
                    <span>${paymentcount }</span>
                    <a href="/displaypayments"><button>Manage</button></a>
                </div>

            </div>


            <div class="card142">
                <div class="leftcard">
                    <span><i class="fa-solid fa-utensils"></i></span>
                </div>
                <div class="rightcard">
                    <span>Total Customer access Cart</span>
                    <span>${cartcount}</span>
                    <a href="#"><button>Manage</button></a>
                </div>

            </div>
            
        </div>
        
        <div class="seectionadd">

            <div class="card754">
                <div class="cardimg">
                    <span><i class="fa-solid fa-layer-group"></i></span>
                </div>
                <div class="cardtitle">
                    <a href="/FoodCateform"><button>Add Category</button></a>
                </div>

            </div>

            <div class="card754">
                <div class="cardimg">
                    <span><i class="fa-solid fa-utensils"></i></span>
                </div>
                <div class="cardtitle">
                    <a href="/tableaddform"><button>Add Table</button></a>
                </div>

            </div>

            <div class="card754">
                <div class="cardimg">
                    <span><i class="fa-solid fa-utensils"></i></span>
                </div>
                <div class="cardtitle">
                    <a href="/gofoodform"><button>Add Food</button></a>
                </div>

            </div>

        </div>

    </div>    
</body>
</html>