/**
 * 
 */
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