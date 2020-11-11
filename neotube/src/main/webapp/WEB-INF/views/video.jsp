<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${video.video_title} - NUTUBE</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value="/resources/video.css?after" />" type="text/css" />
</head>
<body>
<div class="wrapper">
	<div class="container">
		<div class="video-container">
			<div class="video">
				<iframe src="https://www.youtube.com/embed/${ video.video_id }" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
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
				<!-- 좋아요 싫어요 수치가 1천이상이면 축약표기 해주기 -->
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
					<!-- 비동기통신으로 버튼에 색깔 입히기 위해 긴급하게 db랑 직통하기 -->
						<sql:setDataSource
							url="jdbc:mysql://localhost:3366/nutube"
							driver="com.mysql.jdbc.Driver"
							user="root"
							password="1234"
							scope="application"
							var="db"
						/>
						<sql:query var="like_type" dataSource="${db}">
							select `like` from user_like
							where user_id = '${sessionScope.id}' and video_id = '${video.video_id}'
						</sql:query>
						<c:set var="like_classname">
							<c:choose>
								<c:when test="${like_type.getRowsByIndex()[0][0] == 1}">btn-primary</c:when>
								<c:otherwise>btn-dark</c:otherwise>
							</c:choose>
						</c:set>
						<c:set var="dislike_classname">
							<c:choose>
								<c:when test="${like_type.getRowsByIndex()[0][0] == 2}">btn-danger</c:when>
								<c:otherwise>btn-dark</c:otherwise>
							</c:choose>
						</c:set>
						<button id="like" type="button" class="btn ${like_classname }"> 좋아요 ${like_num}</button>
						<button id="dislike" type="button" class="btn ${dislike_classname }">싫어요 ${dislike_num}</button> 
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
			<div class="reply">
				<div class="reply-add">
					<c:choose>
						<c:when test="${sessionScope.id == ''}">
							<div class="alert alert-warning">로그인 후 사용할 수 있습니다.</div>
						</c:when>
						<c:otherwise>
							<input name="user_id" value="${sessionScope.id}" hidden="hidden">
							<input name="video_id" value="${video.video_id}" hidden="hidden">
							<textarea id="reply-content" name="content"></textarea>
							<button class="btn btn-dark" id="reply-submit">작성</button>
						</c:otherwise>
					</c:choose>
				</div>
				<div id="reply-list">
					
				</div>
				<div id="reply-gradiater"></div>
			</div>
		</div>
		<div class="next-video-container">
		    <h3>다음 동영상</h3>
	    	
		</div>
	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<!--  <script src="<c:url value="/resources/video.js?after" />"></script> -->

<script>
$(function() {
	count = 0
	$.ajax({
		url: "reply",
		data: {video_id: "${video.video_id}"},
		type: "GET",
		success: function(result){
			$("div#reply-list").html(result)
		}
	})
	loadnextvideo = _=>{
		if(count < 10) {
			$.ajax({
				url: 'nextvideo',
				data: {
					category: "키즈",
					count: count
				},
				success: function (result) {
					$("div.next-video-container").append(result)
				}
			})
		}
	}
	$(window).scroll(function() {
		if($(window).scrollTop() >= $(document).height() - $(window).height()) {
			count++;
			loadnextvideo()
		}
	})
	$(document).ready(function(){loadnextvideo()})
	$('button#like').click(function(){
		if ("${sessionScope.id}" == "") {
			alert("로그인 후 이용할 수 있습니다")
			return
		} 
		$.ajax({
			url: "like",
			data: {
				video_id:"${video.video_id}",
				user_id:"${sessionScope.id}"
			},
			success: function(result){
				switch(result) {
				case "do":
					$('button#like').attr('class', 'btn btn-primary');
					$('button#like').html("좋아요 " + (parseInt($('button#like').html().substring(4)) + 1))
					break;
				case "toggle":
					$('button#like').attr('class', 'btn btn-primary');
					$('button#like').html("좋아요 " + (parseInt($('button#like').html().substring(4)) + 1))
					$('button#dislike').attr('class', 'btn btn-dark');
					$('button#dislike').html("싫어요 " + (parseInt($('button#dislike').html().substring(4)) - 1))
					break;
				case "undo":
					$('button#like').attr('class', 'btn btn-dark');
					$('button#like').html("좋아요 " + (parseInt($('button#like').html().substring(4)) - 1))
					break;
				}
			}
		})
	})
	$('button#dislike').click(function(){
		if ("${sessionScope.id}" == "") {
			alert("로그인 후 이용할 수 있습니다")
			return
		} 
		$.ajax({
			url: "dislike",
			data: {
				video_id:"${video.video_id}",
				user_id:"${sessionScope.id}"
			},
			success: function(result){
				switch(result) {
				case "do":
					$('button#dislike').attr('class', 'btn btn-danger');
					$('button#dislike').html("싫어요 " + (parseInt($('button#dislike').html().substring(4)) + 1))
					break;
				case "toggle":
					$('button#dislike').attr('class', 'btn btn-danger');
					$('button#dislike').html("싫어요 " + (parseInt($('button#dislike').html().substring(4)) + 1))
					$('button#like').attr('class', 'btn btn-dark');
					$('button#like').html("좋아요 " + (parseInt($('button#like').html().substring(4)) - 1))
					break;
				case "undo":
					$('button#dislike').attr('class', 'btn btn-dark');
					$('button#dislike').html("싫어요 " + (parseInt($('button#dislike').html().substring(4)) - 1))
					break;
				}
			}
		})
	})
	$('button#reply-submit').click(function() {
		$.ajax({
			url: "reply",
			type: "POST",
			data: {
				user_id: "${sessionScope.id}",
				video_id: "${video.video_id}",
				content: $('#reply-content').val()
			},
			success: function(result) {
				console.log(result);
				if(result == "contentlessreply") alert("내용을 입력하세요")
				else {
					$('#reply-list').prepend(result);
					$('#reply-content').val('');
				}
			}
		})
	})
})
</script>
</html>