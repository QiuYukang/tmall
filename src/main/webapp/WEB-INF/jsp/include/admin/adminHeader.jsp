<!-- 模仿天猫整站ssm 教程 为how2j.cn 版权所有-->
<!-- 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关-->
<!-- 供购买者学习，请勿私自传播，否则自行承担相关法律责任-->

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>


<%--解决相对路径会出现问题，在每个相对路径前面能自动加上basepath--%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
//	System.out.println("request.getRequestURI:"+request.getRequestURI());
//	System.out.println("request.getContextPath:"+request.getContextPath());
//	System.out.println("request.getRequestURL:"+request.getRequestURL());
//	System.out.println("request.getServletPath:"+request.getServletPath());
//	System.out.println("request.getScheme:"+request.getScheme());
//	System.out.println("request.getServerName:"+request.getServerName());
//	System.out.println("request.getServerPort:"+request.getServerPort());
%>

<!-- basePath -->
<base href="<%=basePath%>">


<html>

<head>
	<script src="js/jquery/2.0.0/jquery.min.js"></script>
	<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
	<link href="css/back/style.css" rel="stylesheet">
	
<script>
function checkEmpty(id, name){
	var value = $("#"+id).val();
	if(value.length==0){
		alert(name+ "不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	return true;
}
function checkNumber(id, name){
	var value = $("#"+id).val();
	if(value.length==0){
		alert(name+ "不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	if(isNaN(value)){
		alert(name+ "必须是数字");
		$("#"+id)[0].focus();
		return false;
	}
	
	return true;
}
function checkInt(id, name){
	var value = $("#"+id).val();
	if(value.length==0){
		alert(name+ "不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	if(parseInt(value)!=value){
		alert(name+ "必须是整数");
		$("#"+id)[0].focus();
		return false;
	}
	
	return true;
}


$(function(){
	$("a").click(function(){
		var deleteLink = $(this).attr("deleteLink");
		console.log(deleteLink);
		if("true"==deleteLink){
			var confirmDelete = confirm("确认要删除");
            console.log(confirmDelete);
			if(confirmDelete){
                var href = $(this).attr('href');
                $('#delForm').attr('action', href).submit();
                return false;
			}
			return false;
		}
	});
})
</script>	
</head>

<%--用于转换post请求为DELETE请求的表格--%>
<form action="" method="post" id="delForm">
	<input type="hidden" name="_method" value="DELETE">
</form>

<body>

