<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSM-CRUD</title>
<!-- Web路径：
a、不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题
b、以/开始的相对路径，找资源，以服务器的路径为基准（http://localhost:8080/ssm_crud）
 -->
<!-- 引入jQuery -->
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.12.2.min.js"></script>
<!-- 引入bootstrap -->
<!-- Bootstrap -->
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 操作 -->
		<div class="row">
			<div class="col-md-2 col-md-offset-10">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list}" var="emp">
						<tr>
							<th>${emp.empId}</th>
							<th>${emp.empName}</th>
							<th>${emp.gender=="M"?"男":"女"}</th>
							<th>${emp.email}</th>
							<th>${emp.department.deptName}</th>
							<th>
								<button class="btn btn-success btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</button>
								<button class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button>
							</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<div class="col-md-6">
				第${pageInfo.pageNum}页,共${pageInfo.pages }页,共${pageInfo.total }条记录
			</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				  	<li><a href="${APP_PATH}/emps?pn=1">首页</a></li>
				  	<c:if test="${pageInfo.hasPreviousPage}">
					    <li>
					      <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:if>
				    <c:forEach items="${pageInfo.navigatepageNums }" var="currentPage">
				    	<c:if test="${pageInfo.pageNum == currentPage }">
				    		<li class="active"><a href="#">${currentPage }</a></li>
				    	</c:if>
				    	<c:if test="${pageInfo.pageNum != currentPage }">
				    		<li><a href="${APP_PATH}/emps?pn=${currentPage}">${currentPage }</a></li>
				    	</c:if>
				    </c:forEach>
				    <li>
				      <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				    <c:if test="${pageInfo.hasNextPage}">
				    	<li><a href="${APP_PATH}/emps?pn=${pageInfo.pages}">末页</a></li>
				    </c:if>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>