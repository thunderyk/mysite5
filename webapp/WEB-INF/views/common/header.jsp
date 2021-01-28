<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header">
			<h1><a href="./main">MySite</a></h1>
			<c:choose>
				<c:when test="${sessionScope.authorMember eq null}">
					<ul>
						<li><a href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/user/joinForm">회원가입</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul>
						<li>${sessionScope.authorMember.name} 님 안녕하세요^^</li>
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/user/modifyForm">회원정보수정</a></li>
					</ul>
				</c:otherwise>
			</c:choose>			
			
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="${pageContext.request.contextPath}/guest/addList">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="./board?action=list">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>