<!DOCTYPE html>
<html>
<head>
<title>My Page</title>
<link href="CSS/mycss.css" rel="stylesheet" type="text/css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="JS/myjs.js"></script>
</head>
<body>
	<header class="head">
		<div class="container">
			<h1>BLBZ</h1>
		</div>
	</header>
	<nav class="nav-bar">
		<div class="container">
			<ul>
				<li><a class="button home">Home</a></li>
				<li><a class="button login">Login</a></li>
				<li><a class="button register">Register</a></li>
				<li><a class="button userprof" hidden>User Profile</a></li>
				<li><a class="button logout" hidden>Log Out</a></li>
			</ul>
		</div>
	</nav>
	<section class="main">
		<div id="ajx" class="container">
		<%@ include file="home.jsp" %>
		</div>
	</section>
	<footer>
		<div class="container">Copyrights &copy; 2019, blbz.com</div>
	</footer>
</body>
</html>