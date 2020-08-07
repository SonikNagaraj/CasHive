<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.BankApplication.model.AccountsModel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard</title>
<!-- Boostrap CDN-->
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- External CSS -->
<link rel="stylesheet" href="home.css">
</head>
<body>
	<!-- Scriplet tag for key verification -->
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if (session.getAttribute("key") == null) {
		response.sendRedirect("index.jsp");
	}
	%>


	<div class="container-fluid main">
		<div class="header">
			<h1>CasHive</h1>
		</div>
		<br>
		<!-- Logout button -->
		<form action="LogoutController" class="logout" method="post">
			<button type="submit" class="btn btn-dark float-right"
				style="border: 2px solid white; box-shadow: 5px 5px 5px black;">Logout</button>
		</form>
		<br> <br> <br>
		
		<!-- Navpills_div -->
		<div class="row box">
			<div class="col-3">
				<div class="nav flex-column nav-pills" id="v-pills-tab"
					role="tablist" aria-orientation="vertical">
					<a class="nav-link active" id="v-pills-home-tab" data-toggle="pill"
						href="#v-pills-home" role="tab" aria-controls="v-pills-home"
						aria-selected="true">Upload</a> <a class="nav-link"
						id="v-pills-profile-tab" data-toggle="pill"
						href="#v-pills-profile" role="tab" aria-controls="v-pills-profile"
						aria-selected="false">Download</a>
				</div>
			</div>
			<div class="col-9">
				<div class="tab-content" id="v-pills-tabContent">
					<div class="tab-pane fade show active" id="v-pills-home"
						role="tabpanel" aria-labelledby="v-pills-home-tab">
						<div class="Heading1">
							<h1>
								Upload <i class="fa fa-upload" style="font-size: 36px"></i>
							</h1>
						</div>
						<div class="uploadbox">
							<form action="UploadController" method="post" enctype="multipart/form-data">
								<div class="custom-file">
									<input type="file" accept=".csv" onchange="checkfile(this);"
										class="custom-file-input" id="customFile" name="file" multiple>
									<label class="custom-file-label" for="customFile">Choose
										file</label>
								</div>
								<br> <br>
								<div class="row">
									<div class="col-5"></div>
									<div class="col">
										<button type="submit" id="uploadButton" class="btn btn-dark ">Submit</button>
									</div>
								</div>
								<p style="color: red;" id="Success"></p>
							</form>
						</div>
					</div>
					<div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
						aria-labelledby="v-pills-profile-tab">
						<div class="Heading1">
							<h1>
								Download <i class="fa fa-download" style="font-size: 36px"></i>
							</h1>
						</div>
						<div class="downloadbox">
							<form>
								<div class="col-10">
									<select class="browser-default custom-select"
										name="searchoption" id="searchoption">
										<option selected>Search Options</option>
										<option value="1">Account Number</option>
										<option value="2">Date Range(YYYY-MM-DD : YYYY-MM-DD)</option>
										<option value="3">Date(YYYY-MM-DD)</option>
									</select>
								</div>
								<div class="col-10">
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											aria-describedby="basic-addon2" name="searchcontent"
											id="searchcontent">
										<div class="input-group-append">
											<button class="btn btn-dark" type="button" id="SearchButton">Search</button>
										</div>
									</div>
								</div>
								<!-- Displaying the table -->
								<div class="displayTable" id="div1"></div>
								
								<!-- Displaying download options -->
								<h6 style="color: red;"></h6>
								<h5 id="helpText" style="visibility: hidden;">Download as:</h5>
								<a class="btn btn-success" id="downloadCSV" role="button"
									href="C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.csv"
									download="Records-csv" style="visibility: hidden;">CSV</a> <a
									class="btn btn-danger" id="downloadPDF" role="button"
									href="C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.pdf"
									download="Records-pdf" style="visibility: hidden;">PDF</a> <a
									class="btn btn-warning" id="downloadXLS" role="button"
									href="C:\\Users\\Sonik\\Desktop\\Virtusa Project\\Generated files\\Records.xls"
									download="Records-xls" style="visibility: hidden;">XLS</a>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<!-- Script download tab-->
<script>
	// Jquery for displaying file name
	$(".custom-file-input").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
	//Ajax for Download options
	$(function() {
		$("#SearchButton")
				.on(
						"click",
						function() {
							var $searchoption = $("#searchoption");
							var $searchcontent = $("#searchcontent");
							$
									.ajax({
										type : "POST",
										url : "http://localhost:8080/BankApplication/DownloadController",
										data : {
											searchoption : $searchoption.val(),
											searchcontent : $searchcontent
													.val(),
										},
										success : function() {
											document.getElementById('helpText').style.visibility = 'visible';
											document
													.getElementById('downloadCSV').style.visibility = 'visible';
											document
													.getElementById('downloadPDF').style.visibility = 'visible';
											document
													.getElementById('downloadXLS').style.visibility = 'visible';
											$("#div1").load("display-table.jsp");
											$("h6").html("");
											console.log("SUCCESS");
										},
										error : function(status) {
											$("h6")
													.html(
															"<b>No records found!!!</b>");
											console.log("ERROR");
										},
									});
						});
	});
</script>
<!-- Script for file validation  in upload tab-->
<script type="text/javascript">
	function checkfile(file) {
		var validExts = ".csv";
		var fileExt = file.value;
		fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
		if (validExts.indexOf(fileExt) < 0) {
			$("p").html("<b>Invalid File Selected</b>");
			document.getElementById('uploadButton').style.visibility = 'hidden';
			return false;
		} else {
			$("p").html("");
			document.getElementById('uploadButton').style.visibility = 'visible';
			return true;
		}
	}
</script>

</html>