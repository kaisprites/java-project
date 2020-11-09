<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %><c:forEach items="${reply_bag}" var="replyvo">
	<div class="card">
		<div class="card-header">작성자: ${replyvo.getUser_id} / 작성일시: <fmt:formatDate value="${ replyvo.date }"/></div>
		<div class="card-content">${replyvo.content}</div>
	</div>
</c:forEach>