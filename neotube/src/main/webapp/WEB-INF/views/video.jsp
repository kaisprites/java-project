<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${video.video_title} - NUTUBE</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
	p {
		margin: 0;
		padding: 0;
		font-size: 0.8em;
	}
	h1 {
		font-size: 1.2em;
	}
	h2 {
		font-size: 1em;
	}
	div {
		box-sizing: border-box;		
	}
	div.wrapper {
		display: flex;
		justify-content: center;
	}

	div.container {
		display: inline-block;
		width: 1200px;
		margin: 0 auto;
		padding: 0;	
	}
	
	div.video-container {
		display: inline-block;
		width: 900px;
	}
	
	div.video-description-control {
		position: relative;
		height: 0;
		top: -30px;
	}
	
	div.video-description-control div {
		right: -550px;
	}
	
	div.next-video-container {
		display: inline-block;
		width: 300px;
	}
	
</style>
</head>
<body>
<div class="wrapper">
	<div class="container">
		<div class="video-container">
			<div class="video">
				<iframe width="896" height="504" src="https://www.youtube.com/embed/${ video.video_id }" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
			</div>
			<div class="video-description">
				<div class="video-description-video">
					<p class="video-description-tag">
						<c:forEach items="${taglist}" var="tag">
							<a href="#"> ${tag} </a>
						</c:forEach>
					</p>
					<h1>${video.video_title}</h1>
					<p>조회수 <fmt:formatNumber value="${ video.play_num }" />회 • <fmt:formatDate value="${ video.video_date }" /></p>
				</div>
				<div class="video-description-control">
				<c:set var="like_num">
					<c:choose>
						<c:when test="${video.like_num > 10000}">
							<fmt:formatNumber type="number" value="${video.like_num div 10000}" pattern="#.#" />만
						</c:when>
						<c:when test="${video.like_num > 1000}">
							<fmt:formatNumber type="number" value="${video.like_num div 1000}" pattern="#.#" />천 
						</c:when>
						<c:otherwise>
							${video.like_num}
						</c:otherwise>
					</c:choose>
				</c:set>
				<c:set var="dislike_num">
					<c:choose>
						<c:when test="${video.dislike_num > 10000}">
							<fmt:formatNumber type="number" value="${video.like_num div 10000}" pattern="#.#" />만
						</c:when>
						<c:when test="${video.dislike_num > 1000}">
							<fmt:formatNumber type="number" value="${video.like_num div 1000}" pattern="#.#" />천 
						</c:when>
						<c:otherwise>
							${video.dislike_num}
						</c:otherwise>
					</c:choose>
				</c:set>
					<div class="btn-group">
						<button type="button" class="btn btn-dark" onclick="location.href=#">좋아요 ${like_num}</button>
						<button type="button" class="btn btn-dark" onclick="location.href=#">싫어요 ${dislike_num}</button> 
						<button type="button" class="btn btn-dark" onclick="location.href=#">리스트에 추가</button>
					</div>
				</div>
				<hr style="position: relative">
				<div class="video-description-channel">
					<div class="video-description-channel-thumbnail"></div>
					<div class="video-description-channel">
						<h3>${video.channel_title}</h3>
						<p>구독자 ##명</p>
					</div>
				</div>
			</div>
			<hr>
			<div class="reply"></div>
		</div>
		<div class="next-video-container"></div>
	</div>
</div>
</body>
	
</html>