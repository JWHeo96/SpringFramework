<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ezen.biz.dto.BoardVO" %>
<%@ page import="com.ezen.biz.dao.BoardDAO" %>

<%
	BoardVO board = (BoardVO)session.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h1>게시글 상세</h1>
	<a href="logout.do">log-out</a>
	<hr>
	<form action="updateBoard_proc.do" method="post">
		<input name="seq" type="hidden" value="<%= board.getSeq() %>"/>
		<table border="1">
			<tr>
				<th bgcolor="orange" width="150">제목</th>
				<td><input type="text" name="title" value="<%= board.getTitle() %>"/></td>
			</tr>
			<tr>
				<th bgcolor="orange" width="150">작성자</th>
				<td><input type="text" name="writer" value="<%= board.getWriter() %>" readonly="readonly"/></td>
			</tr>
			<tr>
				<th bgcolor="orange" width="150">내용</th>
				<td>
					<textarea name="content" cols="40" rows="10"><%= board.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<th bgcolor="orange" width="150">등록일</th>
				<td><%= board.getRegDate() %></td>
			</tr>
			<tr>
				<th bgcolor="orange" width="150">조회수</th>
				<td><%= board.getCnt() %></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="글 수정"/>
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="insertBoard.jsp">글등록</a>&nbsp;&nbsp;
	<a href="deleteBoard_proc.do?seq=<%= board.getSeq() %>">글 삭제</a>&nbsp;&nbsp;
	<a href="getBoardList.do">글 목록</a>
</div>
</body>
</html>















