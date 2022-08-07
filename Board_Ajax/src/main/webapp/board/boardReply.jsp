<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>MVC 게시판 </title>
	<jsp:include page="header.jsp" />
	<script src="js/reply.js"></script>
	<style>
	h1{font-size:1.5rem; text-align:center; color: #1a92b9}
	label{font-weight:bold}
	.container {width:60%}
	</style>
</head>
<body>
<div class="container">
  <form action="BoardReplyAction.bo" method="post" name="boardform">
	<input type="hidden" name="board_re_ref" value="${boarddata.board_re_ref}">
	<input type="hidden" name="board_re_lev" value="${boarddata.board_re_lev}">
	<input type="hidden" name="board_re_seq" value="${boarddata.board_re_seq}">
	<h1>MVC 게시판-Reply</h1>
	<div class="form-group">
	   <label for="board_name">글쓴이</label>
	   <input name="board_name" id="board_name"
			  type="text"       value="${id}"    class="form-control" readOnly>
    </div>
    
	<div class="form-group">
		<label for="board_subject">제목</label>
		<textarea name="board_subject" id="board_subject" rows="1"
		class="form-control" maxlength="100" >Re:${boarddata.board_subject}</textarea>
	</div>
	<div class="form-group">
		<label for="board_content">내용</label>
		<textarea name="board_content" id="board_content" rows="10"
				  class="form-control"></textarea>
	</div>
	<div class="form-group">
		<label for="board_pass">비밀번호</label>
		<input name="board_pass" id="board_pass"
			   type="password" class="form-control">
	</div>
	
	<div class="form-group">
		<input type=submit class="btn btn-primary" value="등록">
		<input type=button class="btn btn-danger" value="취소"
		 	   onClick="history.go(-1)">
	</div>
  </form>
</div>


</body>
</html>