<%@page import="com.mega.p3.kids.KidsVO"%><%@page import="java.util.List"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><c:forEach items="${bag}" var="vo">	
	<div class="item" onclick="location.href='video?id=${ vo.video_id }'">
		<input name="" value="" hidden="hidden">
		<div class="item-thumbnail">
			<img class="thumbnail" src="${ vo.thumbnail }">
			<div class="item-video-leng-wrapper">
				<div class="item-video-leng">
					<c:set var="hour"><fmt:formatNumber type="number" value="${vo.video_leng div 3600}" pattern="00" /></c:set>
					<c:set var="minute"><fmt:formatNumber type="number" value="${(vo.video_leng mod 3600) div 60}" pattern="00" /></c:set>
					<c:set var="second"><fmt:formatNumber type="number" value="${vo.video_leng mod 60}" pattern="00" /></c:set>
					<c:if test='${hour ne "00"}'>${hour}:</c:if>${minute}:${second}
				</div>
			</div>
		</div>
		<div class="item-description">
			<p><span class="item-desc-title">${ vo.video_title }</span></p>
			<p>
				<span class="item-desc-channel-title one-line-ellipsis">${ vo.channel_title }</span><br>
				조회수: <fmt:formatNumber value="${ vo.play_num }"/> /
				일시: <fmt:formatDate value="${ vo.video_date }"/> 
			</p>
		</div>
		<div class="item-mouseon-highlighter"></div>
	</div>
</c:forEach>

<script> 
	$(function(){
		$('div.item-mouseon-highlighter')
			.mouseenter(function() {
				$(this).css("background","#cccccc80")
			})
			.mouseleave(function() {
				$(this).css("background","#cccccc00")
			})
	})
</script>