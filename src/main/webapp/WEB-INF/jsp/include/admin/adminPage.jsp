<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 模仿天猫整站ssm 教程 为how2j.cn 版权所有-->
<!-- 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关-->
<!-- 供购买者学习，请勿私自传播，否则自行承担相关法律责任-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	
<script>
$(function(){
	$("ul.pagination li.disabled a").click(function(){
		return false;
	});
});

</script>

<%--分页页码显示--%>
<div class="pageDiv">
    <nav>
        <ul class="pagination">
            <%--首页--%>
            <li>
                <a href="${url}?start=0" aria-label="Previous">
                    <span aria-hidden="true">首页</span>
                </a>
            </li>

            <%--上一页--%>
            <li <c:if test="${!page.hasPreviousPage}">class="disabled"</c:if>>
                <a href="${url}?start=${page.prePage}" aria-label="Previous">
                    <span aria-hidden="true">&lt;上一页</span>
                </a>
            </li>

            <%--中间页--%>
            <c:forEach items="${page.navigatepageNums}" var="navigatepageNum">
                <li>
                    <a href="${url}?start=${navigatepageNum}" class="current">
                            ${navigatepageNum}
                    </a>
                </li>
            </c:forEach>

            <%--下一页--%>
            <li <c:if test="${!page.hasNextPage}">class="disabled"</c:if>>
                <a href="${url}?start=${page.nextPage}" aria-label="Next">
                    <span aria-hidden="true">下一页&gt;</span>
                </a>
            </li>

            <%--尾页--%>
            <li>
                <a href="${url}?start=${page.pages}" aria-label="Next">
                    <span aria-hidden="true">尾页</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
