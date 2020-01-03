<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- <%@ page session="true" %> --%>
<html>
<head>
	<title>king 게시판</title>
<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 합쳐지고 최소화된 최신  css -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css" />
<!--합쳐지고 최소화된 최신 자바스크립트   -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js" />
<!-- 참고 사이트 : https://araikuma.tistory.com/56 -->
</head>
<body>
<header>
		<%@include file="board/include/header.jsp" %>	
</header>
<ul class="nav nav-pills">
<li class="list"><a href="/board/write">글 작성</a></li>
<li class="list"><a href="/board/list">글 목록</a></li>
<li class="list"><a href="/board/listPage">글목록 + 페이지</a></li>
<li class="list"><a href="/board/listSearch">글 목록 + 페이지 + 검색목록</a></li> 
</ul>
<section id="container">
<!-- 내용추가 -->
<form role="form" method="post" autocomplete="off" action="/member/login">
<div class="form-group">
	<p>
		<label for="userId" class="col-sm-2 control-label">아이디</label>
		<input  type="text" id="userId" name="userId" />
	</p>
</div>	
<div class="form-group">
	<p>
		<label for="userPass" class="col-sm-2 control-label">패스워드</label>
		<input  type="password" id="userPass" name="userPass" />
	</p>
</div>
	<p><button type="submit">로그인</button></p>
	<p><a href="/member/register">회원가입</a></p>
</form>
</section>
<c:if test="${msg == false }">
	<p style="color:#f00;"> 로그인에 실패 했습니다. 아이디 또는 패스워드를 다시 입력해주십시오.</p>
</c:if>

<c:if test="${member != null }">
	<p>${member.userName}님 환영합니다.</p>
	
	<a href="member/modify">회원정보 수정</a>&nbsp; &nbsp; 
	<a href="member/withdrawal">회원탈퇴</a>&nbsp; &nbsp; 
	<a href="member/logout">로그아웃</a>
</c:if>
</body>
</html>
