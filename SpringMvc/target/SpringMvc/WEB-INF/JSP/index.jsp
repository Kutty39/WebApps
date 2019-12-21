
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>My Page</title>


<style>
	*{
		box-sizing: border-box;}
	body {
		background-color: #f4f4f4;
		color: #555;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 16px;
		line-height: 1.6em;
		margin: 0;
	}

	p.hd-lbl {
		padding: 0;
		margin: 0;
		width: 100%;
		font-family: monospace;
		line-height: 1rem;
	}

	.hd-lbl {
		color: red;
		font-size: 0.7rem;
	}

	.container {
		width: 80%;
		margin: auto;
		overflow: hidden;
	}

	.form-header {
		text-align: center;
		width: 100%;
		margin: auto;
		margin-bottom: 1em;
		padding: 1px;
		background-color: #f57900;
	}

	.form-container, .out-container {
		width: 50%;
		background-color: white;
		margin: auto;
		overflow: hidden;
		border: 1rem #ccc solid;
		padding: 2em;
	}

	.button {
		background-color: #555753;
		padding: 10px;
		color: white;
		font-size: 1em;
	}

	.my-form input[type="text"], .my-form textarea, .my-form input[type="password"]
	{
		width: 100%;
		padding: 0.5rem;
		margin-bottom: 0.5rem;
		font-size: 1em;
		box-sizing: border-box
	}

	.head {
		background: #3c7be5;
	}

	.head h1 {
		color: white;
	}

	.nav-bar {
		background-color: #555753;
	}

	.nav-bar ul {
		padding-left: 0;
		list-style: none;
		text-decoration: none;
	}

	.nav-bar li {
		display: inline;
	}

	.nav-bar a {
		text-decoration: none;
	}

	.nav-bar a:hover {
		background: white;
		color: black;
	}

	footer {
		background-color: #555753;
		color: white;
		text-align: center;
		padding: 2%;
	}

	@media ( max-width : 600px) {
		nav, article {
			width: 100%;
			height: auto;
		}
	}

</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" type="application/javascript"></script>
<script>
	$(document).ready(function() {
		loadpage();
		// this will bind the method for currently available elements
		$("a.button").click(loadpage);
		// this on method will bind the methods to future elements
		$("#ajx").on("blur","input[type!='submit']",($(this), mandChecker));
		$("#ajx").on("keyup","input[type!='submit']",($(this), mandChecker));
		$("#ajx").on("blur","#eid",check);
		$("#ajx").on("blur","#usr",check);
		$("#ajx").on("submit","#regForm",function(e){
			var s=true;
			$("p.hd-lbl").each(function() {
				if (this.style.display === "block") {
					s=false;
				}
			});
			if(s){
				$.ajax({
					type : "POST",
					url : "regestration",
					data :$("#regForm").serialize(),
					success : function(msg) {
						$("#ajx").html(msg);
					},
					dataType : "html"
				});
			}
			e.preventDefault();
		});


		$("#ajx").on("submit","#lgnForm",function(e){
			var s=true;
			$("#errormsg").hide();
			$("#successmsg").hide();
			$("p.hd-lbl").each(function() {
				if (this.style.display === "block") {
					s=false;
				}
			});
			if(s){
				$.post("login",$("#lgnForm").serialize(),function(msg) {
					if(msg.search("Login")<0){
						lognaction();}
					$("#ajx").html(msg);
				});
			}
			e.preventDefault();
		});


		$("#ajx").on("submit","#profForm",function(e){
			let s = true;
			$("p.hd-lbl").each(function() {
				if (this.style.display === "block") {
					s=false;
					return;
				}
			});
			if(s){
				$.post("update",$("#profForm").serialize(),function(msg) {
					alert(msg);
				});
			}
			e.preventDefault();
		});

	});

	function lognaction(){
		$("a.button").each(function(){
			if($(this).attr('class').indexOf("home")<=0){
				$(this).toggle();}
		});
	}
	function logconfirm(){
		$("a.button.login").hide();
		$("a.button.register").hide();
		$("a.button.userprof").show();
		$("a.button.logout").show();
	}


	function loadpage() {
		var cls = $(this).attr("class").replace("button ","");
		if(cls===null){
			cls="home"
		}else{
			console.log(cls.replace("button ",""));
		}

		$.ajax({
			type : "post",
			url : "switchpage",
			data : "page=" + cls,
			success : function(msg) {
				if(cls==="logout"){
					lognaction();
				}
				$("#ajx").html(msg);
			},
			dataType : "html"
		});
	}

	function check(){
		var obj=$(this);
		if(obj!=null){
			$.ajax({
				type : "post",
				url : "validate",
				data : {"email":obj.val()},
				success : function(msg) {
					if(msg==="true"){
						if(obj.attr("name")==="eid"){
							$(".hd-lbl.eid").text("*Email alread exists.");
							$(".hd-lbl.eid").show();
						}if(obj.attr("name")==="username"){
							$(".hd-lbl.username").hide();
						}
					}else{
						if(obj.attr("name")==="username"){
							$(".hd-lbl.username").text("*Email is not exists. please register and come back");
							$(".hd-lbl.username").show();
						}else if(obj.attr("name")==="eid"){
							$(".hd-lbl.eid").hide();
						}
					}
				}
			});
		}
	}

	function mandChecker(element) {
		var val = element.target.value;
		var name = element.target.name;
		var obj = $(".hd-lbl." + name);
		if (val === "") {
			obj.show();
		} else {
			obj.show();
			var regx = "";
			if (name === "phn") {
				regx = /^\d{10}$/;
				if (regx.test(val)) {
					obj.hide();
				} else {
					obj.text("* Phone number should be 10 digit");
				}
			} else if (name === "eid") {
				regx = /^[a-zA-z\d._-]+\@[a-zA-Z\d.-]+\.[a-zA-Z]{2,4}$/;
				if (regx.test(val)) {
					obj.hide();
				} else {
					obj.text("* Please enter valid email.");
				}
			} else if (name === "pas") {
				regx = /^((?=.*[a-z])(?=.*[A-Z])(?!.*\s)(?=.*[@$!%*?&])).{8,15}$/;
				if (regx.test(val)) {
					obj.hide();
				} else {
					obj.show();
				}
			} else if (name === "conpas") {
				var ps = $("input[name='pas']").val();
				if (val === ps) {
					obj.hide();
				} else {
					obj.show();
				}
			} else {
				obj.hide();
			}
		}
	}
</script>
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
		</div>
	</section>
	<footer>
		<div class="container">Copyrights &copy; 2019, blbz.com</div>
	</footer>
</body>
</html>