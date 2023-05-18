<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nonage Admin</title>
<link rel="stylesheet" href="admin/css/admin.css">
<script src="https://code.jquery.com/jquery-3.6.2.min.js" integrity="sha256-2krYZKh//PcchRtd+H+VyyQoZ/e3EcrkxhM8ycwASPA=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="admin/product/product.js"></script>
</head>
<style type="text/css">* {cursor: url(https://cur.cursors-4u.net/anime/ani-1/ani184.cur), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2008/11/07/ani184.html" target="_blank" title="Cute Hello Kitty 9"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Cute Hello Kitty 9" style="position:absolute; top: 0px; right: 0px;" /></a>

<body onload="go_ab()">  <!-- 페이지 로드시에 제품의 순매출 계산 -->
	<div id="wrap">
		<header>			
			<div id="logo">
				<a href="admin_login_form"> 
					<img style="width:800px" src="admin/images/bar_01.gif">
					<img src="admin/images/text.gif">
				</a>
			</div>
			<font color="red"><b>${sessionScope.admin.name}(${sessionScope.admin.id})</b></font>	 님 환영합니다!
			<input class="btn" type="button"  value="logout"  style="float: right;"
			   onClick="location.href='admin_logout'">			
		</header>
		<div class="clear"></div>