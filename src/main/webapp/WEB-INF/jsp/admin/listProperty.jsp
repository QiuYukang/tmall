<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<%--空数据校验--%>
<script>
    $(function(){

        $("#addForm").submit(function(){
            if(!checkEmpty("name","属性名称"))
                return false;
            return true;
        });
    });
</script>

<%--把get请求变成post请求--%>
<%--<script>--%>
    <%--$(function () {--%>
        <%--$('.del').click(function () {--%>
            <%--var href = $(this).attr('href');--%>
            <%--$('#delForm').attr('action', href).submit();--%>
            <%--return false;--%>
        <%--});--%>
    <%--})--%>
<%--</script>--%>

<title>分类管理</title>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list/${category.id}">${category.name}</a></li>
        <li class="active">属性管理</li>
    </ol>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>属性名称</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${properties}" var="property">

                <tr>
                    <td>${property.id}</td>
                    <td>${property.name}</td>
                    <td><a href="admin_property_edit/${property.cid}?id=${property.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <%--<td><a deleteLink="true" class="del" href="admin_property_list/${property.cid}?id=${property.id}"><span class="glyphicon glyphicon-trash"></span></a></td>--%>
                    <td><a deleteLink="true"  href="admin_property_list/${property.cid}?id=${property.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <%--用于转换post请求为DELETE请求的表格--%>
        <form action="" method="post" id="delForm">
            <input type="hidden" name="_method" value="DELETE">
        </form>
    </div>


    <%--分页显示区--%>
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <%--新增属性信息--%>
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增属性</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_property_list/${category.id}">
                <input type="hidden" name="cid" value="${category.id}">
                <table class="addTable">
                    <tr>
                        <td>属性名称</td>
                        <td><input  id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</div>

<%@include file="../include/admin/adminFooter.jsp"%>