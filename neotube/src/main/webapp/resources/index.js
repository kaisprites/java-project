	$(function() {
		$.ajax({
			url: 'list',
			data: {
				category: "키즈"
			},
			success: function (result) {
				$("div#item-list").html(result)
			}
		})
	})