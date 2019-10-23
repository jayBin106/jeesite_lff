<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>查询管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/sys/lffCommercial/">查询列表</a></li>
    <shiro:hasPermission name="sys:lffCommercial:edit">
        <li><a href="${ctx}/sys/lffCommercial/form">查询添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="lffCommercial" action="${ctx}/sys/lffCommercial/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>商户名称：</label>
            <form:input path="mcname" htmlEscape="false" maxlength="20" class="input-medium"/>
        </li>
        <li><label>商户类型：</label>
            <form:select path="mctepy" class="input-medium">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('mctepy')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>所属区域：</label>
            <form:input path="area" htmlEscape="false" maxlength="20" class="input-medium"/>
        </li>
        <li><label>具体地址：</label>
            <form:input path="site" htmlEscape="false" maxlength="20" class="input-medium"/>
        </li>
        <li><label>联系人：</label>
            <form:input path="contacts" htmlEscape="false" maxlength="20" class="input-medium"/>
        </li>
        <li><label>联系人手机：</label>
            <form:input path="contactphone" htmlEscape="false" maxlength="20" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>商户名称</th>
        <th>商户类型</th>
        <th>所属区域</th>
        <th>具体地址</th>
        <th>联系人</th>
        <th>联系人手机</th>
        <th>数量</th>
        <th>拥有设备</th>
        <shiro:hasPermission name="sys:lffCommercial:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="lffCommercial">
        <tr>
            <td><a href="${ctx}/sys/lffCommercial/form?id=${lffCommercial.id}">
                    ${lffCommercial.mcname}
            </a></td>
            <td>
                    ${fns:getDictLabel(lffCommercial.mctepy, 'mctepy', '')}
            </td>
            <td>
                    ${lffCommercial.area.name}
            </td>
            <td>
                    ${lffCommercial.site}
            </td>
            <td>
                    ${lffCommercial.contacts}
            </td>
            <td>
                    ${lffCommercial.contactphone}
            </td>
            <td>
                    ${lffCommercial.number}
            </td>
            <td>
                    ${lffCommercial.facilitynames}
            </td>
            <shiro:hasPermission name="sys:lffCommercial:edit">
                <td>
                    <a href="${ctx}/sys/lffCommercial/form?id=${lffCommercial.id}">修改</a>
                    <a href="${ctx}/sys/lffCommercial/delete?id=${lffCommercial.id}"
                       onclick="return confirmx('确认要删除该查询吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>