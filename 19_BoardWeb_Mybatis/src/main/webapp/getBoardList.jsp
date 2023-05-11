<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<style>
	table {
		background-color:aliceblue;
	}
</style>
<%    
	response.setHeader("Cache-Control","no-store");    
	response.setHeader("Pragma","no-cache");    
	response.setDateHeader("Expires",0);    
	if (request.getProtocol().equals("HTTP/1.1"))  
	        response.setHeader("Cache-Control", "no-cache");  
%>
<body>
<div align="center">
	<h1>게시글 목록</h1>
	<h3>${loginUser.name }(${loginUser.id })님! 환영합니다...<a href="logout.do">log-out</a></h3>
	
	<!-- 검색 파트 -->
	<form action="getBoardList.do" method="post">
	<table border="1" style="width:700;">
		<tr>
			<td align="right">
				<select name="searchCondition">
					<c:forEach items="${conditionalMap }" var="option">
						<option value="${option.value }">${option.key }</option>
					</c:forEach>
	<!-- 				<option value="TITLE">제목
					<option value="CONTENT">내용 -->
				</select>
				<input type="text" name="searchKeyword"/>
				<input type="submit" value="검색"/>
			</td>
		</tr>
	</table>
	</form>
	<!-- 검색 파트 종료 -->
	
	<!-- 게시글 목록 출력 -->
	<br>
	<table border="1" style="width:700;">
		<tr>
			<th bgcolor="yellow" width="100">번호</th>
			<th bgcolor="yellow" width="200">제목</th>
			<th bgcolor="yellow" width="150">작성자</th>
			<th bgcolor="yellow" width="150">등록일</th>
			<th bgcolor="yellow" width="100">조회수</th>
		</tr>
<!-- Java 코드를 통해 전달된 게시글 목록 데이터를 반복하여 읽어 출력 -->

	<c:forEach items="${boardList}" var="board">
	<tr>	
		<td>${board.seq}</td>
		<td><a href="getBoard.do?seq=${board.seq}">${board.title}</a></td>
		<td>${board.writer}</td>
		<td>${board.regDate}</td>
		<td>${board.cnt}</td>
	</tr>
	</c:forEach>
	</table>
	<!-- 게시글 목록 출력 종료-->
	<br>
	<a href="insertBoard.jsp">새글 등록</a>
</div>
</body>
</html>