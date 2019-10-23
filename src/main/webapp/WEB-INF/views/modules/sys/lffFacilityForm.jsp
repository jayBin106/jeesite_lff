<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查询管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//$("#name").focus();
			$("#inputForm").validate({
				 rules: {
	                  facilityMac:{facilityMac:true},
	                  serial:{serial:true}
	              },
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		 /* 对mac格式的约束*/
        jQuery.validator.addMethod("facilityMac",function(value, element) {
            return this.optional(element) || /(([a-f0-9]{2}:)|([a-f0-9]{2}-)){5}[a-f0-9]{2}/gi.test(value);;
        },"填写输入合法mac格式格式！"); 
		 
        /* 对序列号格式的约束*/
        jQuery.validator.addMethod("serial",function(value, element) {
            return this.optional(element) || /^[0-9a-zA-Z]{6}$/.test(value);;
        },"填写输入正确序列号！"); 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/lffFacility/">查询列表</a></li>
		<li class="active"><a href="${ctx}/sys/lffFacility/form?id=${lffFacility.id}">查询<shiro:hasPermission name="sys:lffFacility:edit">${not empty lffFacility.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:lffFacility:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lffFacility" action="${ctx}/sys/lffFacility/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">序列号：</label>
			<div class="controls">
				<form:input path="serial" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备mac：</label>
			<div class="controls">
				<form:input path="facilityMac" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作系统：</label>
			<div class="controls">
				<form:select path="os" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('os')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出厂时间：</label>
			<div class="controls">
				<input name="pdcDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${lffFacility.pdcDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备组：</label>
			<div class="controls">
				<form:input path="facilityS" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:lffFacility:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>