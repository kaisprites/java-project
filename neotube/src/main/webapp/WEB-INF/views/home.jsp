<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%
	session.setAttribute("id","admin");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NUTUBE KIDS</title>
<link href="<c:url value="/resources/index.css?after" />" type="text/css" rel="stylesheet" />
</head>
<body>
<h3>일단 영상목록을 띄워보자</h3>
<div id="item-list">
	
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="<c:url value="/resources/index.js" />"></script>
</html>

