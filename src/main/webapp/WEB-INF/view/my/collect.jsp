<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的文章</title>
<script type="text/javascript">
</script>
</head>
<body>
	<c:forEach items="${collect}" var="collect">
		<div>
			<a href=""><button class="btn btn-link"><font size="5px;">${collect.text }</font></button></a><br>
			&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${collect.created }"/>
		</div>
		<hr>
	</c:forEach>
</body>
</html>