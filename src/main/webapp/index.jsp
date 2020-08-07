<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HomePage</title>
<!-- Bootstrap CDN-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<!-- External CSS -->
<link rel="stylesheet" href="index.css">
</head>
<body>
	<div class="container-fluid main">
		<div class="header">
			<h1>CasHive</h1>
		</div>
		<br> <br>
		<!-- vertical_line_div -->
		<div class="vl"></div>

		<!-- Carousel_div -->
		<div class="caro">
			<div id="carouselExampleIndicators" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="Carousel-Images/img1.jpg" class="d-block w-100" alt="img1">
					</div>
					<div class="carousel-item">
						<img src="Carousel-Images/img2.jpg" class="d-block w-100" alt="img2">
					</div>
					<div class="carousel-item">
						<img src="Carousel-Images/img3.jpg" class="d-block w-100" alt="img3">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>

		<!-- Login_div -->
		<div class="login">
			<form>
				<img src="Carousel-Images/loginlogo.jpg" style="text-align: center;"
					class="img-fluid img" alt="LoginLogo" width="250" height="200">
				<div class="form-group">
					<label for="Username">
						<h4>Enter Username</h4>
					</label> <input type="text" class="form-control" id="username"
						placeholder="Username" name="uname">
				</div>
				<div class="form-group">
					<label for="Password">
						<h4>Enter Password</h4>
					</label> <input type="password" class="form-control" id="password"
						placeholder="Password" name="pass">
				</div>
				<button type="button" id="loginButton" class="btn btn-primary">Submit</button>
				<p style="color: red;"></p>
			</form>
		</div>
	</div>
</body>
<script>
	//Ajax call for login servlet
	$(function() {
		$("#loginButton")
				.on(
						"click",
						function() {
							var $username = $("#username");
							var $password = $("#password");
							$
									.ajax({
										type : "POST",
										url : "http://localhost:8080/BankApplication/LoginController",
										data : {
											username : $username.val(),
											password : $password.val(),
										},
										success : function() {
											// re-direct to next page
											window.location.href = "http://localhost:8080/BankApplication/home.jsp";
										},
										error : function() {
											$("p")
													.html(
															"<b>Wrong Username or password!!!</b>");
											$("input:text").val("");
											$("input:password").val("");
										},
									});
						});
	});
</script>
</html>