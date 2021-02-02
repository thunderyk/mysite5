<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/rboard.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/common/header.jsp"></c:import>

		<c:import url="/WEB-INF/views/common/board/aside.jsp"></c:import>

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="read">
					<form action="#" method="get">
						<!-- 작성자 -->
						<div class="form-group">
							<span class="form-text">작성자</span>
							<span class="form-value">${requestScope.rBoardVo.name}</span>
						</div>
						
						<!-- 조회수 -->
						<div class="form-group">
							<span class="form-text">조회수</span>
							<span class="form-value">${requestScope.rBoardVo.hit}</span>
						</div>
						
						<!-- 작성일 -->
						<div class="form-group">
							<span class="form-text">작성일</span>
							<span class="form-value">${requestScope.rBoardVo.reg_date}</span>
						</div>
						
						<!-- 제목 -->
						<div class="form-group">
							<span class="form-text">제 목</span>
							<span class="form-value">${requestScope.rBoardVo.title}</span>
						</div>
					
						<!-- 내용 -->
						<div id="txt-content">
							<pre class="form-value" >${requestScope.rBoardVo.content}</pre>
						</div>
						<c:if test="${sessionScope.authorMember!=null}">
							<a id="btn_modify" href="${pageContext.request.contextPath}/rboard/writeForm?no=${requestScope.rBoardVo.no}&group_no=${requestScope.rBoardVo.group_no}&order_no=${requestScope.rBoardVo.order_no}&depth=${requestScope.rBoardVo.depth}">댓글쓰기</a>
						</c:if>
						<c:if test="${sessionScope.authorMember.no == requestScope.rBoardVo.user_no }">
							<a id="btn_modify" href="${pageContext.request.contextPath}/rboard/mForm?modifyNum=${requestScope.rBoardVo.no}">수정</a>
						</c:if>
						<a id="btn_modify" href="${pageContext.request.contextPath}/rboard/list">목록</a>
						
					</form>
	                <!-- //form -->
				</div>
				<!-- //read -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->

</body>

</html>
