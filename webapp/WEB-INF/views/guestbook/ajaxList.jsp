<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>


<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
		
		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li><a href="${pageContext.request.contextPath}/guest/addList">일반방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/guest/ajaxList">ajax방명록</a></li>
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
	
	<!-- Modal 영역-->
	<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">삭제하시겠습니까?</h4>
	      </div>
	      <div class="modal-body">

	        <label>비밀번호</label>
	        <input type="password" id = "modalPassword" value=""><br>
			<input type="hidden" id = "modalNo" value="">
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="modalBtn-delete" class="btn btn-primary">삭제</button>
	        <button type="button" id="modalBtn-close" class="btn btn-default" data-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>

</body>

<script type="text/javascript">


$("document").ready(function(){
	
	//리스트 출력
	fetchList();
	
	
	
});

$("#guestbookListArea").on("click", "a", function(){
	
	event.preventDefault();
	$("#delModal").modal();
	$("#modalNo").val($(this).data("no"));
	
});

$("#modalBtn-delete").on("click",function(){	
	var no = $("#modalNo").val();
	
	var guestVo = {
		no : $("#modalNo").val(),		
		password : $("#modalPassword").val()
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/api/guestbook/delete",
		type : "post",
		dataType : "json",
		data : guestVo,
		success : function(count){
			
			$("#delModal").modal("hide");
			$("#modalPassword").val("");
			
			if(count == 1){
				$("#guestTable"+no).remove();
			}else{
				alert("비밀번호가 틀렸습니다.");
			}
			$("#modalNo").val("");
		},
		error : function(XHR, status, error){
			console.error(status + " : " + error);
		}
	});
});



//방명록 등록버튼 클릭
$("#btnRegister").on("click",function(){
	
	var guestBookVo = {
		name : $("#input-uname").val(),
		password : $("#input-pass").val(),
		content : $("[name = 'content']").val()
	}
	
	/*
	$.ajax({
		url : "${pageContext.request.contextPath}/api/guestbook/write",
		type : "post",
		dataType : "json",
		data : guestBookVo,
		success : function(guestbookVo){
			console.log(guestbookVo);
			render(guestbookVo,"up");
			
			$("#input-uname").val("");
			$("#input-pass").val("");
			$("[name = 'content']").val("");
		},
		error : function(XHR, status, error){
			console.error(status + " : " + error);
		}
	});
	*/
	
	$.ajax({
		url : "${pageContext.request.contextPath}/api/guestbook/write2",
		type : "post",
		dataType : "json",
		contentType: "application/json",
		data : JSON.stringify(guestBookVo),
		success : function(guestbookVo){
			console.log(guestbookVo);
			render(guestbookVo,"up");
			
			$("#input-uname").val("");
			$("#input-pass").val("");
			$("[name = 'content']").val("");
		},
		error : function(XHR, status, error){
			console.error(status + " : " + error);
		}
	});

});

function render(guestbookVo,updown){
	var str ='';
	
	str+='<table class="guestRead" id=guestTable'+guestbookVo.no+'>';
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
	str+='		<td><a class = btnDel href="" data-no="'+ guestbookVo.no +'">[삭제]</a></td>';
	str+='	</tr>';
	str+='	<tr>';
	str+='		<td colspan=4 class="text-left">'+ guestbookVo.content +'</td>';
	str+='	</tr>';
	str+='</table>';	
	
	
	
	if(updown == "down"){
		$("#guestbookListArea").append(str);	
	}else if(updown = "up"){
		$("#guestbookListArea").prepend(str);
	}
}

//전체 리스트 출력
function fetchList(){
	$.ajax({
		url : "${pageContext.request.contextPath}/api/guestbook/list",
		type : "post",
		dataType : "json",
		success : function(guestbookList){
			
			for(var i=0;i<guestbookList.length;i++){
				render(guestbookList[i],"down");
			}
			
		},
		error : function(XHR, status, error){
			console.error(status + " : " + error);
		}
	});
}

</script>

</html>