<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<div id="root">
	<header>
		<%@include file="include/header.jsp" %>		
	</header>
	<nav>
		<%@include file="include/nav.jsp" %>		
	</nav>
	<section id="container">
		<form role="form" method="post" autocomplete="off">
			<p>
				<label for="bno">글 번호</label>
				<input type="text" id="bno" name="bno" value="${modify.bno}" readonly="readonly"/>
			</p>
			<p>
				<label for="title">글 제목</label>
				<input type="text" id="title" name="title" value="${modify.title}"/>
			</p>
			<p>
				<label for="content">글 내용</label>
				<textarea id="content" name="content">${modify.content}</textarea>
			</p>
			<p>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" value="${modify.writer}" readonly="readonly"/>
				<span><fmt:formatDate value="${modify.regDate}" pattern="yyyy-MM-dd" /></span>
			</p>
			<p>
				<button id="modify_btn"type="submit">수정</button>
				<button type="button" id="cancel_btn">취소 </button>
				<script type="text/javascript">
						
				
				var bno = document.getElementById('title').value;
				var content =  document.getElementById('content').value;
				var writer =  document.getElementById('writer').value;
				
				console.log(bno);
				console.log(content);
				console.log(writer);
				
					// 폼을 변수에 저장  form > role="form" 인 값을 담음 
					var formObj = $("form[role='form']");
					
					// 취소 버튼 클릭
					$("#cancel_btn").click(function(){
						window.location.href="/board/read?bno=${modify.bno}"
											+"&page=${scri.page}"
											+"&perPageNum=${scri.perPageNum}"
											+"&searchType=${scri.searchType}"
											+"&keyword=${scri.keyword}";
					});
					
					
					
				</script>
			</p>
		</form>
	</section>
	<footer>
		<%@include file="include/footer.jsp" %>
	</footer>
</div>
</body>
</html>