<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

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
		
		<!-- //aside -->

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

			<!-- 			
			<c:set var="pageCount" value="${requestScope.pageCount}"></c:set>
			<c:set var="currentPage" value="${requestScope.currentPage}"></c:set>
			<c:set var="searchData" value="${requestScope.searchData}"></c:set>
			<c:set var="searchWay" value="${requestScope.searchWay}"></c:set>
			-->
			 
			<!-- 검색을 타이틀, 내용, 타이틀or내용, 글쓴이로 각각 4가지 방법으로 검색 -->
			<!-- if문으로 searchWay(검색 방법)이 null혹은 board_title이면 board_title(게시판 제목 항목이)이 선택되게-->
			<!-- board_content이면 내용이 선택되게 -->
			<!-- board_titleContent이면 제목+내용이 선택되게 -->
			<!-- board_writer면 글쓴이가 선택됨 -->
			<!-- 검색 방법이 null이 아니면 취소 버튼이 생김(무언가를 검색하면 취소 버튼이 생김) -->
			<!-- 취소 버튼은 아무 것도 검색 안된 상태의 list 맨 처음 페이지로 이동 -->
			<div id="board">
				<div id="list">
					<!-- 
					<form action="${pageContext.request.contextPath}/board/list" method="post">
						<div class="form-group text-right">
					    <select name="searchWay">
                            <option value="board_title" <c:if test="${searchWay eq null || searchWay eq 'board_title'}">selected</c:if>>제목</option>
                            <option value="board_content" <c:if test="${searchWay eq 'board_content'}">selected</c:if> >내용</option>
                            <option value="board_titleContent" <c:if test="${searchWay eq 'board_titleContent'}">selected</c:if>>제목+내용</option>
                            <option value="board_writer" <c:if test="${searchWay eq 'board_writer'}">selected</c:if>>글쓴이</option>
                        </select>
		
							<input type="text" name="searchData" value="${searchData}">
							<button type="submit" id="btn_search">검색</button>
							<c:if test="${searchWay != null}">
								<button type="button" id="btn_searchCancel" onClick="location.href='${pageContext.request.contextPath}/board/list'" id=btn_search_cancel>취소</button>
							</c:if>
						</div>
					</form>
					-->
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>group_no</th>
								<th>order_no</th>
								<th>depth</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
						<!-- 들여쓰기 4칸일 때 -->
						<c:set var="indent" value="&emsp;"></c:set>
						
							<!-- for each로 depth만큼 들여쓰기함  -->
							<c:forEach items="${requestScope.pageBoardList}" var="vo">
								<tr>
									<td>${vo.no}</td>
									<td class="text-left">
										<a href="${pageContext.request.contextPath}/rboard/read?readNum=${vo.no}">
											<c:forEach begin="1" end="${vo.depth}">
												${indent}
											</c:forEach>
											${vo.title}
										</a>
									</td>
									
									<td>${vo.name}</td>
									<td>${vo.hit}</td>
									<td>${vo.group_no}</td>
									<td>${vo.order_no}</td>
									<td>${vo.depth}</td>
									<td>${vo.reg_date}</td>
									
									<td>
										<c:if test="${sessionScope.authorMember.no == vo.user_no }">
											<a href="${pageContext.request.contextPath}/rboard/delete?deleteNum=${vo.no}">[삭제]</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					
					<!-- 페이징 -->
					<!-- 
					<div id="paging">
						<ul>
							<c:choose>
								<c:when test="${currentPage == 1}">
									<li>◀</li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${searchWay != null}">
											<li><a href="${pageContext.request.contextPath}/board/list?currentPage=${currentPage-1}&searchData=${searchData}&searchWay=${searchWay}">◀</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath}/board/list?currentPage=${currentPage-1}">◀</a></li>	
										</c:otherwise>
									</c:choose>
									
								</c:otherwise>
							</c:choose>
							
							
							<c:forEach var="i" begin="1" end="${pageCount}"> 
								<c:choose>
									<c:when test="${currentPage == i}">
										<c:choose>
											<c:when test="${searchWay != null}">
												<li><a href="${pageContext.request.contextPath}/board/list?currentPage=${i}&searchData=${searchData}&searchWay=${searchWay}" class="active">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="${pageContext.request.contextPath}/board/list?currentPage=${i}" class="active">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${searchWay != null}">
												<li><a href="${pageContext.request.contextPath}/board/list?currentPage=${i}&searchData=${searchData}&searchWay=${searchWay}">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="${pageContext.request.contextPath}/board/list?currentPage=${i}">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
							<c:choose> 
								<c:when test="${currentPage == pageCount}"> 
									<li>▶</li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${searchWay != null}">
											<li><a href="${pageContext.request.contextPath}/board/list?currentPage=${currentPage+1}&searchData=${searchData}&searchWay=${searchWay}">▶</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath}/board/list?currentPage=${currentPage+1}">▶</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</ul>		
						<div class="clear"></div>
					</div>
					-->
					
					<c:if test="${sessionScope.authorMember != null}">
						<a id="btn_write" href="${pageContext.request.contextPath}/rboard/writeForm">글쓰기</a>
					</c:if>
				</div>
				<!-- //list -->
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
