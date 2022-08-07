function go(page){
	var limit = $('#viewcount').val();
	var data = "limit=" + limit + "&state=ajax&page=" + page;
	ajax(data);
}


function ajax(sdata) {
  console.log(sdata)
  
  $.ajax({
	type: "POST",
	data: sdata,
	url : "BoardList.bo",
	dataType: "json",
	cache: false,
	success: function(data) {
		$("#viewcount").val(data.limit);
		$("table").find("font").text("글 개수" + data.listcount);
		
	if (data.listcount > 0) { // 총 갯수가 0보다 큰 경우
		$("tbody").remove();
		var num = data.listcount - (data.page - 1) *  data.limit;
		console.log(num)
		var output = "<tbody>";
		$(data.boardlist).each(
			function(index, item) {
				output += '<tr><td>' + (num--) + '</td>'
				blank_count = item.board_re_lev * 2 + 1;
				blank = '&nbsp;';
				for (var i = 0; i < blank_count; i++) {
					blank += '&nbsp;&nbsp;';
				}
				img="";
				if(item.board_re_lev > 0){
					img="<img src='image/line.gif'>";
				}
				
				var subject = item.board_subject;
				if(subject.length>=20){
					subject=subject.substr(0,20) + "...";//0부터 20개 부분 문자열 추출
				}
				
				output += "<td><div>" + blank + img
				output += ' <a href="BoardDetailAction.bo?num=' + item.board_num + '">'
				output += subject.replace(/</g,'&lt;').replace(/</g,'&gt;') 
							+ '</a>[' + item.cnt + ']</div></td>'
				output += '<td><div>'+ item.board_name +'</div></td>'
				output += '<td><div>'+ item.board_date +'</div></td>'
				output += '<td><div>'+ item.board_readcount +'</div></td></tr>'
		 });
		output += "</tbody>"
		$('table').append(output)//table 완성
  	
   	 }//if(data.listcount)>0 end
  
   }, //success end
   error : function(){
	 console.log('에러')
   }
 })//ajax end
}//function ajax end


//ready 안에 안들어감 - 선언적 함수
$(function(){
	$("button").click(function(){
		location.href="BoardWrite.bo";
	})
	
	$("#viewcount").change(function(){
		go(1);//보여줄 페이지를 1페이지로 설정합니다.
	});
})