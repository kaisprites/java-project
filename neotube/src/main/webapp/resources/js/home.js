/**
 * 
 */
	$(function() {
		count = 0;
		load = _=>{ 
			$.ajax({
				url: 'home',
				data: {
					category: "키즈",
					count: count
				},
				success: function (result) {
					$("div#item-list").append(result)
				}
			})
		}
		$(window).scroll(function() {
			if($(window).scrollTop() >= $(document).height() - $(window).height()) {
				count++
				load()
			}
		})
		$(document).ready(function(){load()})
	})