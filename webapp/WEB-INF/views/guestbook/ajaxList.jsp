<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
		
		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li><a href="${pageContext.request.contextPath}/guestbook/addList">일반방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook/ajaxList">ajax방명록</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>일반방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">일반방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
<%-- 				<form action="${pageContext.request.contextPath}/api/guestbook/write" method="post"> --%>
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></th>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass"type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id="btnRegister" type="submit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- //guestWrite -->
					
				<!-- </form>	 -->
				
				<div id="guestbookListArea">
				
				</div>
				
			</div>

		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">


$("document").ready(function(){
	$.ajax({
		url : "${pageContext.request.contextPath}/api/guestbook/list",
		type : "post",
		dataType : "json",
		success : function(guestbookList){
			
			for(var i=0;i<guestbookList.length;i++){
				render(guestbookList[i]);
			}
			
		},
		error : function(XHR, status, error){
			console.error(status + " : " + error);
		}
	});
});

//방명록 등록버튼 클릭
$("#btnRegister").on("click",function(){
	
	var name = $("#input-uname").val();
	var pass = $("#input-pass").val();
	var content = $("[name = 'content']").val();
	
	$.ajax({
		url : "${pageContext.request.contextPath}/api/guestbook/write",
		type : "post",
		dataType : "json",
		data : {name : name,
			 	password : pass,
			 	content : content},
		success : function(guestbookVo){
			console.log(guestbookVo);
			render(guestbookVo);
		},
		error : function(XHR, status, error){
			console.error(status + " : " + error);
		}
		
		
	});

});

function render(guestbookVo){
	var str ='';
	
	str+='<table class="guestRead">';
	str+='	<colgroup>';
	str+='		<col style="width: 10%;">';
	str+='		<col style="width: 40%;">';
	str+='		<col style="width: 40%;">';
	str+='		<col style="width: 10%;">';
	str+='	</colgroup>';
	str+='	<tr>';
	str+='		<td>'+ guestbookVo.no+'</td>';
	str+='		<td>'+ guestbookVo.name+'</td>';
	str+='		<td>'+ guestbookVo.reg_date +'</td>';
	str+='		<td><a href="${pageContext.request.contextPath}/guest/deleteForm?no='+ guestbookVo.no +'">[삭제]</a></td>';
	str+='	</tr>';
	str+='	<tr>';
	str+='		<td colspan=4 class="text-left">'+ guestbookVo.content +'</td>';
	str+='	</tr>';
	str+='</table>';	
	
	$("#guestbookListArea").prepend(str);
}


</script>

</html>