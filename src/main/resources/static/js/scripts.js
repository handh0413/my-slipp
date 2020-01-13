String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	
	var queryString = $(".answer-write").serialize();
	
	var url = $(".answer-write").attr("action");
	console.log("url : " + url);
	
	$.ajax({
		type: 'post',
		url: url,
		data: queryString,
		error: onError,		// 오류 시 호출
		success: onSuccess	// 성공 시 호출
	});
}

function onError() {
	
}

function onSuccess(data, status) {
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.question.id, data.id);
	
	$(".qna-comment-slipp-articles").append(template);
	
	$(".answer-write textarea").val('');
}

// $("a.link-delete-reply").click(deleteAnswer);
// 답변 달고 바로 그 답글을 삭제할 때 이벤트가 처리 안되시는 분은 아래와 같은 처리 하시면 됩니다.
$(document).on('click', 'a.link-delete-reply', deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	
	var deleteBtn = $(this);
	var url = deleteBtn.attr("href");
	console.log(url);
	
	$.ajax({
		type: 'delete',
		url: url,
		dataType: 'json',
		error: function(xhr, status) {
			
		},
		success: function(data, status) {
			console.log(data);
			if (data.valid) {
				deleteBtn.closest("article").remove();
			} else {
				alert(data.errorMessage);
			}
		}
	});
}