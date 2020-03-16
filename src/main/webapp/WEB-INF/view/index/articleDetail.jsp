<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${article.title }</title>
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/index.css" />
<script type="text/javascript" src="/resources/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resources/bootstrap.min.js"></script>
<script type="text/javascript">
	//增加评论
	function addComment() {
		var articleId = '${article.id}';
		var content = $("[name='content']").val();
		$.post("/addComment",{articleId:articleId,content:content},function(flag){
			if(flag){
				alert("评论成功！！！！");
				window.location.reload();
			}else{
				alert("请先登录再进行评论！！！！");
			}
		})
	}
	
	//收藏
	function collect() {
		//文章标题
		var title = '${article.title}';
		//文章地址
		var url = window.location.href;
		$.post("/collect",{text:title,url:url},function(flag){
			if(flag){
				alert("收藏成功！！！！");
				window.location.reload();
			}else{
				alert("收藏失败请先登录再进行收藏！！！！");
			}
		})
	}
	//取消收藏
	function deleteCollect() {
		id = '${collect.id}';
		$.post("/deleteCollect",{id:id}, function(flag) {
			if(flag){
				alert("取消收藏成功！！！！");
				window.location.reload();
			}else{
				alert("取消收藏失败！！！！");
			}
		});
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<!-- 头 -->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 34px;">
				<font color="white"
					style="font-size: 18px; margin-left: 5px; line-height: 2;">下载APP</font>
			</div>
		</div>
		
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-1"></div>
			<div class="col-md-7">
				<h2>${article.title }</h2>
				<p>${article.user.username }
					<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</p>
				<c:if test="${null!=collect }">
					<button type="button" onclick="deleteCollect()" class="btn btn-link"><font color="red">★取消收藏</font></button>
				</c:if>
				<c:if test="${null==collect }">
					<button type="button" onclick="collect()" class="btn btn-link">☆未收藏</button>
				</c:if>
				<hr>
				${article.content }
				<hr>
				<!-- 文章评论 -->
				<c:if test="${null!=sessionScope.user }">
					<div>
						<h4>文章评论：</h4>
						<textarea rows="3" cols="20" style="width: 753px;" name="content"></textarea><br>
						<button type="button" onclick="addComment()" class="btn btn-info" style="margin-left: 665px;">提交评论</button>
					</div>
				</c:if>
				
				<div>
					<!-- 评论内容 -->
					<c:forEach items="${info.list }" var="comment">
						<h5>${comment.user.username } <fmt:formatDate value="${comment.created }" pattern="yyyy-MM-dd HH:mm:ss"/></h5>
						
						${comment.content }
						<hr>
					</c:forEach>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="card" style="width: 18rem;margin-top: 6px;width: 420px;">
				<div class="card-header"><font color="red">最新评论</font></div>
					<div class="card-body">
				<!-- 最新10篇文章 -->
					<c:forEach items="${info2.list}" var="article" varStatus="i">
						<p>${i.count} ${article.title }</p>
						<hr>
					</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>