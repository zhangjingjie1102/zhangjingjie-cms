<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>KindEditor JSP</title>
<link rel="stylesheet"
	href="/resources/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="/resources/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8"
	src="/resources/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resources/kindeditor/kindeditor-all.js"></script>  
<script charset="utf-8" src="/resources/kindeditor/lang/zh-CN.js"></script>
<script src="/resources/js/jquery-3.2.1.js"></script>
	
<script>
	KindEditor
			.ready(function(K) {
				window.editor1 = K
						.create(
								'textarea[name="content1"]',
								{
									cssPath : '/resources/kindeditor/plugins/code/prettify.css',
									uploadJson : '/resources/kindeditor/jsp/upload_json.jsp',
									fileManagerJson : '/resources/kindeditor/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.forms['example'].submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.forms['example'].submit();
										});
									}
								});
				prettyPrint();
			});
	function query() {
		alert(editor1.html())
		//alert( $("[name='content1']").attr("src"))
	}
</script>
</head>
<body>
	<form id="form1">
		<!-- 文章标题 -->
		<div class="form-group">
			<label for="title">文章标题：</label> 
			<input id="title" type="text" name="title" class="form-control form-control-sm">
		</div>
		
		<div class="form-group form-inline">
			<!-- 所属栏目 -->
			<label for="caticle">所属栏目：</label> 
			<select class="form-control form-control-sm" id="channels" name="channelId">
				<option>请选择</option>
			</select>
			<!-- 所属分类 -->
			<label for="category">所属分类：</label> 
			<select class="form-control form-control-sm" id="categorys" name="categoryId">
				<option>请选择</option>
			</select>
		</div>
		<!-- 文章标题图片 -->
		<div class="form-group">
			<label for="image">文章标题图片：</label> 
			<input type="file" name="file" id="image">
		</div>

		<textarea name="content1" cols="100" rows="8"
			style="width: 700px; height: 200px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br /> 
		
		<!-- 发布文章 -->
		<input type="button" name="button" value="发布文章" onclick="publish()" class="btn btn-success" />

	</form>
</body>
<script type="text/javascript">
	function publish() {
		
		var formData = new FormData($("#form1")[0]);
		formData.set("content",editor1.html())
		$.ajax({
			type:"post",
			url:"my/publish",
			//告诉jquery不去处理发送的数据
			processData:false,
			//告诉jquery不要去设置Content-type请求头
			contentType:false,
			data:formData,
			success: function(flag) {
				if(flag){
					alert("发布成功");
					//跳转到我的文章
					location = "/my";
				}
			}
		})
	}



	$(function() {
		//为栏目添加内容--栏目和分类二级联动
		$.get("/channel/channels", function(list) {
			for ( var i in list) {
				$("#channels").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
			}
		})
		//为栏目添加点击事件
		$("#channels").change(function() {
			//先获取当前栏目Id
			var channelId = $(this).val();
			//根据栏目Id查询其下分类
			$.get("/channel/selectsByChannelId",{channelId:channelId},function(list) {
				$("#categorys").empty();
				$("#categorys").append("<option>请选择</option>")
				for ( var i in list) {
					$("#categorys").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
				}
			})
		});
	})
</script>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>