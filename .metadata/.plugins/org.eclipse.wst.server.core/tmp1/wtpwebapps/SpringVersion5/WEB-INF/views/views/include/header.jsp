<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<title>언론기사 관리게시판</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="/resources/css/bootstrap.css">
		<script src="/resources/js/jquery-3.3.1.slim.min.js"></script>
		<script src="/resources/js/bootstrap.bundle.min.js"></script>		

		<script type="text/javascript">
			function logout() {
				$(location).attr("href", "/logout.do");
			}
		</script>
	</head>
	
	<body>
		<!-- header -->
		<header>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">
					<h1><a class="navbar-brand" href="#"><img src="/resources/images/logo_woongjincoway.png" alt="웅진코웨이"></a></h1>

					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsGNB" aria-controls="navbarsGNB" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<!-- nav bar -->
					<div class="collapse navbar-collapse" id="navbarsGNB">
						<!-- nav -->
						<ul class="navbar-nav mr-auto">
							<li><h1>언론기사 관리게시판</h1></li>
						</ul>
						<!--// nav -->
					  
					  	<!-- utility -->
					  	<div class="utility-menu">
							<span class="welcome">${sessionUser.name}님<span class="hello"> 안녕하세요</span>!</span>
							<button class="btn btn-secondary btn-sm" type="button" id="logoutBtn" onclick="logout();">로그아웃</button>
					  	</div>
					  	<!--// utility -->
					</div>
					<!--// nav bar -->
				</div>
			</nav>
		</header>
		<!--// header -->

		<!-- container -->
		<div class="container-fluid" id="container">
			<div class="row">
