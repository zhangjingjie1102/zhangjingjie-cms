<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章审核</title>
<script type="text/javascript">
	var articleId;
	//审核文章
	function updateStatus(status) {
		$.post("/admin/update",{id:articleId,status:status}, function (flag) {
			if(flag){
				$("#msg").empty();
				$("#msg").append("操作成功");
			}else{
				$("#msg").empty();
				$("#msg").append("操作失败");
			}
		})
	}
	

	function goPage(pageNum) {
		//在中间区域加载
		$("#center").load("/admin/articles?pageNum="+pageNum);
	}
	
	function query() {
		var status = $("[name=status]").val();
		var title = $("[name=title]").val();
		$("#center").load("/admin/articles?status="+status+"&title="+title);
	}
	
	//改变热门文章
	function update(id,obj) {
		var hot = $(obj).text()=="是"?0:1;
		$.post("/admin/update",{id:id,hot:hot}, function(flag) {
			if(flag){
				$(obj).text($(obj).text()=="是"?"否":"是");
				$(obj).attr("class",$(obj).text()=="否"?"btn btn-info btn-sm":"btn btn-danger btn-sm");
			}
		});
	}
	
	//文章详情
	function articleDetail(id) {
		articleId = id;
		$.get("/admin/articleDetail",{id:id}, function(article) {
			$("#title").empty();
			$("#content").empty();
			$("#title").append(article.title);
			$("#content").append(article.content);
		});
	}
</script>
</head>
<body>
	<div class="from-group form-inline" style="padding-bottom: 10px;">
		文章标题：
		<input type="text" name="title" class="form-control form-control-sm" value="${article.title }">&nbsp;&nbsp;&nbsp;&nbsp;
		审核状态：
		<select name="status" class="form-control form-control-sm col-md-1">
			<option value="0" ${article.status==0?'selected':'' }>待审</option>
			<option value="1" ${article.status==1?'selected':'' }>通过</option>
			<option value="-1" ${article.status==-1?'selected':'' }>驳回</option>
		</select>
		&nbsp;&nbsp;
		<button type="button" onclick="query()" class="btn btn-warning btn-sm">查询</button>
	</div>
	
	
		<table class="table table-bordered table-hover table table-sm" style="text-align: center" >
			<tr>
				<th>序号</th>
				<th>标题</th>
				<th>作者</th>
				<th>发布时间</th>
				<th>所属栏目</th>
				<th>所属分类</th>
				<th>是否热门</th>
				<th>审核状态</th>
				<th>点击量</th>
				<th>其他</th>
			</tr>
		<c:forEach items="${info.list }" var="article" varStatus="i">
				<tr>
					<th>${i.count }</th>
					<td>${article.title }</td>
					<td>${article.user.username}</td>
					<td>
						<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>${article.channel.name }</td>
					<td>${article.category.name }</td>
					<td>
						<!-- 不是热门文章 -->
						<c:if test="${article.hot==0}">
							<button type="button" class="btn btn-info btn-sm" onclick="update(${article.id},this)">否</button>
						</c:if>
						<!-- 是热门文章 -->
						<c:if test="${article.hot==1}">
							<button type="button" class="btn btn-danger btn-sm" onclick="update(${article.id},this)">是</button>
						</c:if>
					</td>
					<td>
						${article.status==0?"待审":article.status==1?"通过":"驳回" }
					</td>
					<td>${article.hits }</td>
					<td>
						<button type="button" onclick="articleDetail(${article.id })" class="btn btn-link" data-toggle="modal" data-target="#exampleModal">
					 	 详情
						</button>
					</td>
				</tr>
		</c:forEach>
	</table>
		<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
		<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"><span id="title"></span></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="content">
	        
	      </div>
	      <div class="modal-footer">
	      	<span id="msg"></span>
	     	<button type="button" class="btn btn-primary" onclick="updateStatus('1')">通过</button>
	        <button type="button" class="btn btn-danger" onclick="updateStatus('-1')">驳回</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>