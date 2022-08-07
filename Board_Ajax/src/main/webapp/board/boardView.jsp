<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>MVC 게시판 - view</title>
<jsp:include page="header.jsp" />
<script src="js/view.js"></script>
<link rel="stylesheet" href="css/view.css">
</head>
<body>
   <input type="hidden" id="loginid" value="${id}" name="loginid">
   <div class="container">
     <table class="table">
     	<tr>
     	  <th colspan="2">MVC 게시판-view페이지</th>
     	</tr>
     	<tr>
     	  <td><div>글쓴이</div></td>
     	  <td><div>${boarddata.board_name}</div></td>
     	</tr>
     	<tr>
     	  <td><div>제목</div></td>
     	  <td><c:out value="${boarddata.board_subject}" /></td>
     	</tr>
     	<tr>
     	 <td><div>내용</div></td>
		 <td style="padding-right: 0px">
		 <textarea class="form-control"
		           rows="5" readOnly>${boarddata.board_content}</textarea></td>
	    </tr>

		<c:if test="${boarddata.board_re_lev==0}">
		<%-- 원문글인 경우에만 첨부파일을 추가 할 수 있습니다. --%>
		<tr>
		<td><div>첨부파일</div></td>
		<c:if test="${!empty boarddata.board_file}">
		<%-- 파일첨부한 경우 --%>
		<td><img src="image/down.png" width="10px">
			<a href="BoardFileDown.bo?filename=${boarddata.board_file}">
				${boarddata.board_file} </a></td>
		</c:if>
		<c:if test="${empty boarddata.board_file}">
				<%-- 파일첨부하지 않은 경우 --%>
				<td></td>
		</c:if>
	</tr>
</c:if>

  <tr>
	<td colspan="2" class="center">
		<c:if test="${boarddata.board_name == id || id == 'admin' }">
			<a href="BoardModifyView.bo?num=${boarddata.board_num}">
			<button class="btn btn-info">수정</button>
			</a>
			<%-- href의 주소를 #으로 설정합니다. --%>
			<a href="#">
			<button class="btn btn-danger" data-toggle="modal"
				data-target="#myModal">삭제</button>
			</a>
		</c:if>
			<a href="BoardList.bo">
			  <button class="btn btn-warning">목록</button>
			</a>
			<a href="BoardReplyView.bo?num=${boarddata.board_num}">
			   <button class="btn btn-success">답변</button>
			</a>
      </td>
  </tr>
 </table>
 <%--게시판 view end --%>

  <%-- Modal 시작 --%>
<div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      <%-- Modal body --%>
	<div class="modal-body">
    <form name="deleteForm" action="BoardDeleteAction.bo" method="post">
      <%--http://localhost:8088/Board/Board DetailAction.bo?num=22
						주소를 보면 num을 파라미터로 넘기고 있습니다.
						이 값을 가져와서 ${param.num}를 사용
						또는 ${boarddata.board_num}
	  --%>
	<input type="hidden" name="num" value="${param.num}" id="comment_board_num">
	<div class="form-group">
		<label for="pwd">비밀번호</label>
		<input type="password"
			   class="form-control" placeholder="Enter password"
			   name="board_pass" id="board_pass">
	</div>
	<button type="submit" class="btn btn-primary">전송</button>
	<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
	</form>
   </div>
  </div>
 </div>
</div>
</div>
</body>
</html>