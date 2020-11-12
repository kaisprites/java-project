<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %><%
	session.setAttribute("id","admin");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Neotube KIDS</title>
<link rel="stylesheet" href="<c:url value="/resources/css/index_page.css?after" />">
<link href="<c:url value="/resources/css/index.css?after" />" type="text/css" rel="stylesheet" />
<!-- ���������� css -->
</head>
<body>
	<!-- ��� �׺���̼� -->
	<header>
		<nav class="nav_fix">
			<div id="main_icon">
				<h3>
					<a href="index.html"><img id="logo" src="img/logo3.png" width=150 height=30.61></a>
				</h3>
			</div>
			<div id="nav_category">
				<ul>
					<li><a href="">Sports</a></li>
					<li><a href="">Games</a></li>
					<li><a href="">Cooking</a></li>
					<li><a href="">Kids</a></li>
					<li><a href="">Supports</a></li>
					<li><a href="">Login</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- ���� ���̵�� -->
	<aside>
		<div style="margin-left: -65px;margin-top: 50px;"><a href="" style="font-size: 30px; ">Ȩ</a></div>
		<div style="margin-left: -65px;margin-top: 50px;"><a href="" style="font-size: 30px;">�α�</a></div>
		<div style="margin-left: -65px;margin-top: 50px;"><a href="" style="font-size: 30px;">����</a></div>
		<div style="margin-left: -65px;margin-top: 50px;"><a href="" style="font-size: 30px;">���ƿ��� ������</a></div>
		<div style="margin-left: -65px;margin-top: 50px;"><a href="" style="font-size: 30px;">��û���</a></div>
		<div style="margin-left: -65px;margin-top: 50px;"><a href="" style="font-size: 30px;">����</a></div>
	</aside>
	<!-- ���� -->
	<div class="main_page" >
		<h1>Mainpage</h1>
	</div>
	<div class="main_frame" style=" height:auto; overflow:hidden; width: 1950px; margin-left: 195px; ">
		<div id="item-list">
		</div>
	</div>

</body>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="<c:url value="/resources/index.js" />"></script>
</html>