<%--
  Created by IntelliJ IDEA.
  User: 邱玉康
  Date: 2018/9/10
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<html>
<head>
    <script>
        $(function(){

            $("#editForm").submit(function(){
                if(!checkEmpty("name","属性名称"))
                    return false;
                return true;
            });
        });
    </script>
</head>

<body>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list/${category.id}">${category.name}</a></li>
        <li class="active">编辑属性</li>
    </ol>

    <%--修改属性信息--%>
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">编辑属性</div>
        <div class="panel-body">
            <form:form method="post" id="editForm" action="admin_property_list/${category.id}"
            modelAttribute="property">

                <table class="addTable">
                    <tr>
                            <%--Rest风格的请求，把post请求变成put请求--%>
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="id" value="${property.id}">
                    </tr>

                    <tr>
                        <td>属性名称</td>
                        <td><form:input  id="name" path="name" type="text" class="form-control"/></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
