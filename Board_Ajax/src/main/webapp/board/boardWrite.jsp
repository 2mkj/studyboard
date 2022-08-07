<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<jsp:include page="header.jsp" />
<script src="js/writeform.js"></script>
<style>
  h1{font-size:1.5rem; text-align: center; color: #1a92b9}
  .container {width:60%}
  label{font-weight:bold}
  #upfile{display:none}
  img{width:20px;}
</style>
</head>
<body>
<div class="container">
 <form action="BoardAddAction.bo" method="post" enctype="multipart/form-data" name="boardform">
  <h1>MVC 게시판-write 페이지</h1>
  <div class="form-group">
   <label for="board_name">글쓴이</label>
   <input name="board_name" id="board_name" value="${id}" readOnly
		  type="text"       class="form-control"
		  placeholder="Enter board_name">
  </div>
  <div class="form-group">
    <label for="board_pass">비밀번호</label>
    <input name="board_pass" id="board_pass" type="password" maxlength="30"
		   class="form-control" placeholder="Enter board_pass">
  </div>
  <div class="form-group">
	<label for="board_subject">제목</label>
	<input name="board_subject" id="board_subject" type="text" maxlength="100"
		   class="form-control" placeholder="Enter board_subject">
  </div>
  <div class="form-group">
	<label for="board_content">내용</label>
	<textarea name="board_content" id="board_content"
			  rows="10" class="form-control"></textarea>
  </div>
  <div class="form-group">
 	<label for="board_file">파일 첨부</label>
	<label for="upfile">
	<img src="image/attach.png" alt="파일첨부">
	</label>
	<input type="file" id="upfile" name="board_file">
	<span id="filevalue"></span>
  </div>
  <div class="form-group">
  	<button type="submit" class="btn btn-primary">등록</button>
  	<button type="reset" class="btn btn-danger">취소</button>
  </div>
  </form>
 </div>
</body>
</html>