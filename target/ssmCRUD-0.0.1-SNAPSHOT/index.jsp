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

	<!-- 员工修改Modal -->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="#1">员工修改</h4>
	      </div>
	      <div class="modal-body">
	      <!-- 员工添加表单 -->
	        <form class="form-horizontal">
			  <div class="form-group">
			    <label for="emp_update_input" class="col-sm-2 control-label">empName</label>
			    <div class="col-sm-10">
			       <p class="form-control-static" id="empName_update_static"></p>
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="email_update_input" class="col-sm-2 control-label">email</label>
			    <div class="col-sm-10">
			      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@sina.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">gender</label>
			    <div class="col-sm-10">
			      <label class="radio-inline">
					  <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
					</label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">deptName</label>
			    <div class="col-sm-4">
			      <select name="dId" class="form-control">

					</select>
			    </div>
			  </div>
			 
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="emp_update_btn">修改</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- 员工添加Modal -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
	      </div>
	      <div class="modal-body">
	      <!-- 员工添加表单 -->
	        <form class="form-horizontal">
			  <div class="form-group">
			    <label for="emp_add_input" class="col-sm-2 control-label">empName</label>
			    <div class="col-sm-10">
			      <input type="text" name="empName" class="form-control" id="emp_add_input" placeholder="empName">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="email_add_input" class="col-sm-2 control-label">email</label>
			    <div class="col-sm-10">
			      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email@sina.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">gender</label>
			    <div class="col-sm-10">
			      <label class="radio-inline">
					  <input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
					</label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">deptName</label>
			    <div class="col-sm-4">
			      <select name="dId" class="form-control">

					</select>
			    </div>
			  </div>
			 
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
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
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger" id="delete_all_btn">删除</button>
			</div>
		</div>
		<!-- 表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<div class="col-md-6" id="page_info_area">
				
			</div>
			<div class="col-md-6" id="page_nav_area">
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//总记录数
		var totalRecord,currentPage;
		//1、页面加载完成之后，直接发送ajax请求获取分页数据
		$(function(){
			toPage(1);
		});
		function toPage(pn){
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pn=" + pn,
				type:"GET",
				success:function(result){
					//console.log(result);
					//1、解析并显示员工信息
					build_emps_table(result);
					//2、解析并显示分页信息
					build_page_info(result);
					//3、显示分页条
					build_page_nav(result);
				}
			});
		}
		function build_emps_table(result){
			//清空table
			$("#emps_table tbody").empty();
			var emps = result.extend.pageInfo.list;
			$.each(emps,function(index,item){
				var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(item.gender=="M"?"男":"女");
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>").append(item.department.deptName);
				//<button class="btn btn-success btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</button>
				//<button class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button>
				var editBtn = $("<button></button>").addClass("btn btn-success btn-sm edit_btn")
									.append("<span></span>").addClass("glyphicon glyphicon-pencil")
									.append("编辑");
				//为编辑按钮添加自定义的属性，用于保存员工ID
				editBtn.attr("edit_id",item.empId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
									.append("<span></span>").addClass("glyphicon glyphicon-trash")
									.append("删除");
				//为删除按钮添加自定义的属性，用于保存员工ID
				delBtn.attr("delete_id",item.empId);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				//append方法返回的还是原来的元素，所以可以链式操作
				$("<tr></tr>").append(checkBoxTd)
						.append(empIdTd).append(empNameTd)
						.append(genderTd).append(emailTd)
						.append(deptNameTd).append(btnTd)
						.appendTo("#emps_table tbody");
			});
		}
		//显示分页信息
		function build_page_info(result){
			//清空page_info_area
			$("#page_info_area").empty();
			$("#page_info_area").append("第 "+result.extend.pageInfo.pageNum +" 页,共 " +result.extend.pageInfo.pages +" 页,共 "+result.extend.pageInfo.total +" 条记录");
			totalRecord = result.extend.pageInfo.total;
			currentPage = result.extend.pageInfo.pageNum;
		}
		//显示分页条page_nav_area,点击要能跳转
		function build_page_nav(result){
			//清空page_nav_area
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			//构建
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			if(result.extend.pageInfo.hasPreviousPage == false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}else{
				//如果不被禁用则绑定点击事件
				firstPageLi.click(function(){
						toPage(1);
				});
				prePageLi.click(function(){
					toPage(result.extend.pageInfo.pageNum - 1 );
				});
			}
			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
			if(result.extend.pageInfo.hasNextPage == false){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}else{
				//如果不被禁用则绑定点击事件
				nextPageLi.click(function(){
					toPage(result.extend.pageInfo.pageNum + 1);
				});
				lastPageLi.click(function(){
					toPage(result.extend.pageInfo.pages);
				});
			}
			//将首页和上一页添加到ul
			ul.append(firstPageLi).append(prePageLi);
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if(result.extend.pageInfo.pageNum == item){
					numLi.addClass("active");
				}
				//绑定单击事件
				numLi.click(function(){
					toPage(item);
				});
				//向ul中添加遍历后的页码1，2，3
				ul.append(numLi);
			});
			//将末页和下一页添加到ul
			ul.append(nextPageLi).append(lastPageLi);
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
		}
		//重置方法
		function reset_form(ele){
			//清空表单
			$(ele)[0].reset();
			//清除样式
			$(ele).find("*").removeClass("has-success has-error");//清楚所有
			$(ele).find(".help-block").text("");
		}
		//查询所有部门并显示在指定的元素中
		function getDepts(ele){
			//清空下拉列表
			$(ele).empty();
			$.ajax({
				url:"${APP_PATH}/depts",
				type:"GET",
				success:function(result){
					//console.log(result);
					//{"code":100,"msg":"操作成功！","extend":{"deptsList":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"},{"deptId":3,"deptName":"市场部"}]}}
					//$("#empAddModal select").append();
					$.each(result.extend.deptsList,function(){
						var optionEle = $("<option></option>").append(this.deptName).attr("value",this.deptId);
						optionEle.appendTo(ele);
					});
				}
			});
		}
		$("#emp_add_modal_btn").click(function(){
			//弹出模态框之前清空表单数据（表单重置）
			//$("#empAddModal form")[0].reset();//reset不是jQuery方法，因此要使用原生JavaScript语法获取DOM对象
			reset_form("#empAddModal form");
			//弹出模态框之前通过ajax请求查询部门信息
			getDepts("#empAddModal select");
			$('#empAddModal').modal({
				backdrop:"static"
			});
		});
		//显示验证和的提示信息——前端校验
		function show_validate_msg(ele,status,msg){
			//先移除添加的样式
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			if("success" == status){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			}else if("error" == status){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}
		function validate_add_form(){
			//验证用户名，正则表达式查看jQuery文档
			var empName = $("#emp_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{4,16}$)|(^[\u2E80-\u9FFF]{2,6})/;
			if(!regName.test(empName)){
				//alert("用户名为2-6位中文或6-16位字母、数字及非特殊字符");
				show_validate_msg("#emp_add_input","error","用户名为2-6位中文或4-16位字母、数字及非特殊字符");
				return false;
			}else{
				show_validate_msg("#emp_add_input","success","");
			}
			//验证邮箱
			var empEmail = $("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(empEmail)){
				//alert("邮箱格式不正确！");
				show_validate_msg("#email_add_input","error","邮箱格式不正确!");
				return false;
			}else{
				show_validate_msg("#email_add_input","success","");
			}
			return true;
		}
		//验证用户名是否存在——后端校验
		$("#emp_add_input").change(function(){
			var empName = this.value;
			$.ajax({
				url:"${APP_PATH}/checkUser",
				data:"empName=" + empName,
				type:"POST",
				success:function(result){
					if(result.code == 100){
						show_validate_msg("#emp_add_input","success","用户名可用");
						$("#emp_save_btn").attr("back_valid","success");
					}else{
						show_validate_msg("#emp_add_input","error",result.extend.valid_msg);
						$("#emp_save_btn").attr("back_valid","error");
					}
				}
			});
		});
		$("#emp_save_btn").click(function(){
			//保存数据之前先进行前端校验（前端校验容易绕过）
			if(!validate_add_form()){
				return false;
			}
			//如果后端验证用户名存在，则不能进行保存
			if($(this).attr("back_valid")=="error"){
				return false;
			}
			//将模态框中填写的信息保存至数据库中
			//发送ajax请求保存员工信息POST
			//alert($("#empAddModal form").serialize());//将form表单数据序列化为字符串，用于 Ajax 请求。
			$.ajax({
				url:"${APP_PATH}/emp",
				type:"POST",
				data:$("#empAddModal form").serialize(),
				success:function(result){
					//alert(result.msg);
					if(result.code==100){
						//保存员工后
						//模态框关闭
						$('#empAddModal').modal("hide");
						//页面跳转到最后一页
						//根据分页插件参数合理化，只需要跳转到比总页数大的即可，总记录数一定大于总页数
						toPage(totalRecord);
					}else{
						//显示错误信息
						//console.log(result);
						//判断是哪一个字段错误
						if(undefined != result.extend.errorFields.empName){
							//显示用户名错误信息
							show_validate_msg("#emp_add_input","error",result.extend.errorFields.empName);
						}else if(undefined != result.extend.errorFields.email){
							//显示邮箱错误信息
							show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
						}
					}
				}
			});
		});
		
		//因为在按钮创建之前就绑定了click事件，所以绑定不上（ajax请求）
		//1）可以在创建按钮的时候绑定，但是耦合了 2）绑定点击：live（）
		//jQuery新版本不在使用live（）方法，使用on代替
		$(document).on("click",".edit_btn",function(){
			//alert("edit");
			//查出并显示部门信息
			getDepts("#empUpdateModal select");
			//查出并显示员工信息
			getEmp($(this).attr("edit_id"));
			
			//将员工的id传递给更新按钮
			$("#emp_update_btn").attr("edit_id",$(this).attr("edit_id"));
			//弹出模态框
			$('#empUpdateModal').modal({
				backdrop:"static"
			});
		});
		//查询员工方法
		function getEmp(id){
			$.ajax({
				url:"${APP_PATH}/emp/" + id,
				type:"GET",
				success:function(result){
					//console.log(result);
					var empData = result.extend.emp;
					$("#empName_update_static").text(empData.empName);
					$("#email_update_input").val(empData.email);
					$("#empUpdateModal input[name='gender']").val([empData.gender]);
					$("#empUpdateModal select").val([empData.dId]);
				}
			});
		}
		
		//点击更新，保存员工信息
		$("#emp_update_btn").click(function(){
			//验证邮箱
			var empEmail = $("#email_update_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(empEmail)){
				show_validate_msg("#email_update_input","error","邮箱格式不正确!");
				return false;
			}else{
				show_validate_msg("#email_update_input","success","");
			}
			
			//发送ajax请求保存更新后的员工信息
			$.ajax({
				url:"${APP_PATH}/emp/" + $(this).attr("edit_id"),
				type:"PUT",
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
					//alert(result.msg);
					//关闭模态框
					$("#empUpdateModal").modal("hide");
					//跳转到当前页
					toPage(currentPage);
				}
			});
			
		});
		
		//单个员工删除
		$(document).on("click",".delete_btn",function(){
			var empName = $(this).parents("tr").find("td:eq(2)").text();
			//alert($(this).parents("tr").find("td:eq(2)").text());
			var empId = $(this).attr("delete_id");
			//弹出是否删除对话框
			if(confirm("确认删除【"+ empName +"】吗？")){
				//发送ajax请求删除数据
				$.ajax({
					url:"${APP_PATH}/emp/" + empId,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						toPage(currentPage);
					}
				});
			}
		});
		
		//全选/全不选
		$("#check_all").click(function(){
			//自定义对象属性——$(this).attr("edit_btn")
			//原生的DOM对象属性获取——$(this).prop("checked")
			$(".check_item").prop("checked",$(this).prop("checked"));
		});
		
		//check_item
		$(document).on("click",".check_item",function(){
			//判断当前是否全部选中
			var flag = $(".check_item:checked").length == $(".check_item").length;
			$("#check_all").prop("checked",flag);
		});
		
		//批量删除选中行
		$("#delete_all_btn").click(function(){
			var empNames = "";
			var del_idstr = "";
			$.each($(".check_item:checked"),function(){
				empNames += $(this).parents("tr").find("td:eq(2)").text() + ",";
				//组成员工id的字符串
				del_idstr += $(this).parents("tr").find("td:eq(1)").text() + "-"
			});
			//去除empNames后面多余的逗号
			empNames = empNames.substring(0,empNames.length-1);
			//去除del_idstr后面多余的-
			del_idstr = del_idstr.substring(0,del_idstr.length-1);
			if(0 == del_idstr.length){
				alert("未选中需要删除的数据！");
			}else{
				if(confirm("确认删除【"+empNames+"】")){
					//发送ajax请求进行删除操作
					$.ajax({
						url:"${APP_PATH}/emp/" + del_idstr,
						type:"DELETE",
						success:function(result){
							alert(result.msg);
							//回到当前页面
							toPage(currentPage);
							//这句如果不加，删除后全部选中框依然是勾选状态
							$("#check_all").prop("checked",false);
						}
					});
				}
			}
		});
	</script>
</body>
</html>