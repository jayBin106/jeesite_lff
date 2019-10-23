<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查询管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/lffFacility/">查询列表</a></li>
		<shiro:hasPermission name="sys:lffFacility:edit"><li><a href="${ctx}/sys/lffFacility/form">查询添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lffFacility" action="${ctx}/sys/lffFacility/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>序列号：</label>
				<form:input path="serial" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>设备mac：</label>
				<form:input path="facilityMac" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>操作系统：</label>
				<form:select path="os" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('os')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>出厂时间：</label>
				<%-- <input name="pdcDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${lffFacility.pdcDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> --%>
					
					
					<input id="beginDate"  name="beginDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${lffFacility.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
			        <input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${lffFacility.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			<li><label>设备组：</label>
				<form:input path="facilityS" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序列号</th>
				<th>设备mac</th>
				<th>设备名称</th>
				<th>操作系统</th>
				<th>出厂时间</th>
				<th>设备组</th>
				<shiro:hasPermission name="sys:lffFacility:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lffFacility">
			<tr>
				<td><a href="${ctx}/sys/lffFacility/form?id=${lffFacility.id}">
					${lffFacility.serial}
				</a></td>
				<td>
					${lffFacility.facilityMac}
				</td>
				<td>
					${lffFacility.name}
				</td>
				<td>
					${fns:getDictLabel(lffFacility.os, 'os', '')}
				</td>
				<td>
					<fmt:formatDate value="${lffFacility.pdcDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lffFacility.facilityS}
				</td>
				<shiro:hasPermission name="sys:lffFacility:edit"><td>
    				<a href="${ctx}/sys/lffFacility/form?id=${lffFacility.id}">修改</a>
					<a href="${ctx}/sys/lffFacility/delete?id=${lffFacility.id}" onclick="return confirmx('确认要删除该查询吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>