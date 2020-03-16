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
	function goPage(pageNum) {
		//在中间区域加载
		$("#center").load("/admin/users?pageNum="+pageNum);
	}
	
	function query() {
		var username = $("[name=username]").val();
		var locked = $("[name=locked]").val();
		alert(username);
		alert(locked);
		$("#center").load("/admin/users?locked="+locked+"&username="+username);
	}
	
	//改变热门文章
	function update(id,obj) {
		var locked = $(obj).text()=="正常"?1:0;
		$.post("/admin/updateUser",{id:id,locked:locked}, function(flag) {
			if(flag){
				$(obj).text($(obj).text()=="正常"?"禁用":"正常");
				$(obj).attr("class",locked==0?"btn btn-info btn-sm":"btn btn-danger btn-sm");
			}
		});
	}
</script>
</head>
<body>
	<div class="from-group form-inline" style="padding-bottom: 10px;">
		用户名：
		<input type="text" name="username" class="form-control form-control-sm" value="${user.username }">&nbsp;&nbsp;&nbsp;&nbsp;
		用户状态：
		<select name="locked" class="form-control form-control-sm col-md-1">
			<option value="0" ${user.locked=="0"?'selected':'' }>正常</option>
			<option value="1" ${user.locked=="1"?'selected':'' }>禁用</option>
		</select>
		&nbsp;&nbsp;
		<button type="button" onclick="query()" class="btn btn-warning btn-sm">查询</button>
	</div>
	
	
		<table class="table table-bordered table-hover table table-sm" style="text-align: center" >
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>昵称</th>
				<th>性别</th>
				<th>生日</th>
				<th>注册时间</th>
				<th>用户状态</th>
			</tr>
		<c:forEach items="${info.list }" var="user" varStatus="i">
				<tr>
					<th>${i.count }</th>
					<td>${user.username }</td>
					<td>${user.nickname}</td>
					<td>${user.gender==0?"女":"男"}</td>
					<td>${user.birthday }</td>
					<td>
						<fmt:formatDate value="${user.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<!-- 不是热门文章 -->
						<c:if test="${user.locked==0}">
							<button type="button" class="btn btn-info btn-sm" onclick="update(${user.id},this)">正常</button>
						</c:if>
						<!-- 是热门文章 -->
						<c:if test="${user.locked==1}">
							<button type="button" class="btn btn-danger btn-sm" onclick="update(${user.id},this)">禁用</button>
						</c:if>
					</td>
				</tr>
		</c:forEach>
	</table>
		<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
</body>
</html>