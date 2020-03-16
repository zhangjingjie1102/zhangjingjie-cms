<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap.min.css" />
<script type="text/javascript" src="/resources/popper.min.js"></script>
<script type="text/javascript" src="/resources/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resources/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		//默认显示我的文章
		$("#center").load("/my/articles")
		
		
		
		$("li").click(function() {
			var url = $(this).children().attr("data");
			$("li").removeClass("active");
			$(this).addClass("list-group-item active");
			$("#center").load(url);
		})
	})
</script>
</head>
<body>
	<div class="container">
		<!-- 头 -->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #563d7c; height: 60px;">
				<a href="/"><img alt="" src="/resources/image/3.jpg"
					style="height: 57px; width: 150px; padding-top: 2px; padding-left: 10px;"></a>
				<span style="color: white">今日头条-个人中心</span>
				<div style="float: right;padding-right: 10px;">
					<!-- 从session获取当前有没有登录，如果用户已经登录，则不显示登录和注册 -->
					<c:if test="${null !=sessionScope.user}">
						<div class="btn-group dropleft">
							<button type="button" class="btn btn-link dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">	<font color="white">登录信息</font></button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">${sessionScope.user.username }</a> <a
									class="dropdown-item" href="/my">个人中心</a> <a
									class="dropdown-item" href="/passport/logout">注销</a>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<!-- 身体 -->
		<div class="row" style="padding-top: 5px;">
			<div class="col-md-2">
				<ul class="list-group">
					<li class="list-group-item active"><a href="#" data="/my/articles"><font color="red">我的文章</font></a></li>
					<li class="list-group-item"><a href="#" data="/my/publish"><font color="red">发布文章</font></a></li>
					<li class="list-group-item"><a href="#" data="/my/collect"><font color="red">我的收藏</font></a></li>
					<li class="list-group-item"><a href="#"><font color="red">我的评论</font></a></li>
					<li class="list-group-item"><a href="#"><font color="red">个人信息</font></a></li>
				</ul>

			</div>
			<div class="col-md-10" id="center">
				<!-- 先加载kindeditor的样式 -->
				<div style="display: none">
					<jsp:include page="/resources/kindeditor/jsp/demo.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
</html>