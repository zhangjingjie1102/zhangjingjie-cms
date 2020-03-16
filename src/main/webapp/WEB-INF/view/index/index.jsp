<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条</title>
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/index.css" />
<script type="text/javascript" src="/resources/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resources/popper.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap.min.js"></script>
<script type="text/javascript">
	function goPage(pageNum) {
		var channelId = '${article.channelId}';
		var categoryId = '${article.categoryId}';
		var hot = '${article.hot}';
		var url = "/?channelId="+channelId+"&pageNum="+pageNum;
		if(categoryId!="")
			url=url+"&categoryId="+categoryId;
		location = url;
	}
	//注册
	function reg() {
		$("#title").append("用户注册");
		$("#passport").load("/passport/reg");
	}
	//登录
	function login() {
		$("#title").append("用户登录");
		$("#passport").load("/passport/login");
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
				
				<!-- 登陆注册 -->
				<div style="float: right;padding-right: 10px;">
					<!-- 从session获取当前有没有登录，如果用户已经登录，则不显示登录和注册 -->
					<c:if test="${null==sessionScope.user}">
						<button type="button" class="btn btn-link" onclick="reg()"
							data-toggle="modal" data-target="#staticBackdrop"><font color="white" size="2px">注册</font></button>
						<button type="button" class="btn btn-link" onclick="login()"
							data-toggle="modal" data-target="#staticBackdrop"><font color="white" size="2px">登录</font></button>
					</c:if>
					<c:if test="${null !=sessionScope.user}">


						<div class="btn-group dropleft">
							<button type="button" class="btn btn-link dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">	<font color="white" size="2px">登录信息</font></button>
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

		<div class="row">
			<!-- 左侧栏目 -->
			<div class="col-md-2" style="padding-top: 10px;">
				<ul>
					<!-- 今日头条logo -->
					<li style="margin-bottom: 10px;">
						<a class="channel" href="/">
							<img alt="" src="/pic/logo-lol.png" style="width: 130px;height:60px;">
						</a>
					</li>
					<!-- 热点文章 -->
					<li><a href="/?hot=1" class="channel-item ${article.channelId ==null?'active':'' }" id="rd">热点</a></li>
					
					<!-- 遍历所有的栏目 -->
					<c:forEach items="${channels }" var="channel">
						<li><a href="/?channelId=${channel.id }" class="channel-item ${article.channelId ==channel.id?'active':'' }">${channel.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<!-- 中间 -->
			<div class="col-md-7">
				<!-- 如果栏目I的为NUll 说明点击热点  显示轮播图-->
				<c:if test="${article.channelId==null }">
					<div style="margin: 5px 5px 5px 5px;">
					<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
					  <ol class="carousel-indicators">
					  	<c:forEach items="${slides }" var="slide" varStatus="i">
					  		<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }" class="active"></li>
					  	</c:forEach>
					  </ol>
					  <div class="carousel-inner">
					  	<c:forEach items="${slides }" var="slide" varStatus="i">
					  		<div class="carousel-item ${i.index==0?'active':'' }">
						      <img src="/pic/${slide.url }" class="d-block w-100 rounded" alt="..." style="width: 819px;height: 339px;">
						      <div class="carousel-caption d-none d-md-block">
						        <h5>${slide.title }</h5>
						      </div>
					    </div>
					  	</c:forEach>
					  </div>
					  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
					    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
					    <span class="carousel-control-next-icon" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					  </a>
				</div>
					</div>
					<hr>
				</c:if>
				
				<!-- 如果栏目Id不为null 说明点击左侧栏目 -->
				<c:if test="${article.channelId!=null }">
					<!-- 显示栏目下的子分类 -->
					<div class="subchannel">
						<ul>
							<li class="sub-item ${article.categoryId==null?'sub-selected':'' }"><a href="/?channelId=${article.channelId }">全部</a></li>
							<c:forEach items="${categorys }" var="category">
								<li class="sub-item  ${article.categoryId==category.id?'sub-selected':'' }"><a href="/?channelId=${article.channelId }&categoryId=${category.id}">${category.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<!-- 分类下的文章 -->
				<div>
					<c:forEach items="${info.list }" var="article">
						<div class="media">
						  <img src="/pic/${article.picture }" class="mr-3 rounded" alt="..." style="width: 190px; height: 124px">
						  	<div class="media-body">
						    <h5 class="mt-0"><a href="/articleDetail?id=${article.id }" target="_blank">${article.title }</a></h5>
						  	<p>作者：${article.user.username }</p>
						  	<p>点击量：${article.hits }</p>
						  	<p>发布时间：
						  		<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
						  	</p>
						  </div>
						</div>
						<hr>
					</c:forEach>
					<!-- 分页文章 -->
					<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
				</div>
			</div>
			
			<!-- 右侧 -->
			<div class="col-md-3">
				<div class="card" style="width: 18rem;margin-top: 6px;width: 420px;">
				<div class="card-header"><font color="red">最新文章</font></div>
					<div class="card-body">
				<!-- 最新10篇文章 -->
					<c:forEach items="${lastArticles.list}" var="lastArticle">
						<div class="media">
						  <img src="/pic/${lastArticle.picture }" class="mr-3 rounded" alt="..." style="width: 60px;height: 60px">
						  <div class="media-body">
						  	<p><a href="/articleDetail?id=${lastArticle.id }" target="_blank">${lastArticle.title }</a></p>
					 	 </div>
						</div>
						<hr>
					</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- 登录/注册 -->
		<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel"><span id="title"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="passport">
			       	 
      </div>
    </div>
  </div>
</div>
	</div>
</body>
</html>