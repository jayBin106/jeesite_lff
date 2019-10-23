<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>查询管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#inputForm").validate({
                rules: {
                    mcname: {remote: "${ctx}/sys/lffCommercial/checkName?oldMcName=" + encodeURIComponent('${lffCommercial.mcname}')}
                },
                messages: {
                    mcname: {remote: "商户名已存在"}
                },
                submitHandler: function (form) {
                    var name = $("#facilityidName").val();
                    $("#hideName").val(name);
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
    <script type="text/javascript">
        window.init = function () {
            var map = new AMap.Map('container', {
                resizeEnable: true,
                center: [119.480983, 39.989628],
                zoom: 11
            });
            if (location.href.indexOf('guide=1') !== -1) {
                map.setStatus({scrollWheel: false});
                map.plugin(["AMap.ToolBar"], function () {
                    map.addControl(new AMap.ToolBar({liteStyle: true}))
                });
            }
            var $ = function (elementId) {
                return document.getElementById(elementId);
            };
            var lnglatInput = $('lnglat');
            map.on('click', function (e) {
                lnglatInput.value = e.lnglat.toString();
            });
        }
    </script>
    <script src="https://webapi.amap.com/maps?v=1.4.15&key=您申请的key值&callback=init"></script>
    <style type="text/css">
        body, html {
            width: 100%;
            height: 100%;
            margin: 0px
        }

        #container {
            width: 400px;
            height: 200px;
            margin-top: -35px;
            margin-left: 500px;
            float: left;
        }
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/sys/lffCommercial/">查询列表</a></li>
    <li class="active"><a href="${ctx}/sys/lffCommercial/form?id=${lffCommercial.id}">查询<shiro:hasPermission
            name="sys:lffCommercial:edit">${not empty lffCommercial.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="sys:lffCommercial:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="lffCommercial" action="${ctx}/sys/lffCommercial/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <input id="hideName" style="display: none" value="11" name="facilitynames"/>

    <div class="control-group">
        <label class="control-label">商户名称：</label>
        <div class="controls">
            <form:input path="mcname" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">商户类型：</label>
        <div class="controls">
            <form:select path="mctepy" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('mctepy')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
            <%--<label class="control-label">所属区域：</label>--%>
            <%--<div class="controls">--%>
            <%--<sys:treeselect id="area" name="area" value="${area.parent.id}" labelName="parent.name"--%>
            <%--labelValue="${area.parent.name}"--%>
            <%--title="区域" url="/sys/area/treeData" extId="${area.id}" cssClass="" allowClear="true"/>--%>
            <%--</div>--%>
        <label class="control-label">归属区域：</label>
        <div class="controls">
            <sys:treeselect id="area" name="area.id" value="${lffCommercial.area.id}" labelName="area.name"
                            labelValue="${lffCommercial.area.name}"
                            title="区域" url="/sys/area/treeData" cssClass="" allowClear="true"
                            notAllowSelectParent="true"/>
        </div>

    </div>
    <div class="control-group">
        <label class="control-label">拥有设备：</label>
        <div class="controls">
            <sys:treeselect id="facilityid" name="facilityid" value="${lffCommercial.facilityid}" labelName=""
                            labelValue="${lffCommercial.facilitynames}"
                            title="设备" url="/sys/lffCommercial/treeData" cssClass=""
                            allowClear="true" checked="true"/>

        </div>
    </div>
    <div class="control-group">
        <label class="control-label">具体地址：</label>
        <div class="controls">
            <form:input path="site" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">联系人：</label>
        <div class="controls">
            <form:input path="contacts" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">联系人手机：</label>
        <div class="controls">
            <form:input path="contactphone" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">经纬度：</label>
        <div class="controls">
            <form:input id="lnglat" path="longitudelatitude" htmlEscape="false" maxlength="100" class="input-xlarge "/>
        </div>
        <div id="container" tabindex="0"></div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="sys:lffCommercial:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                                  value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>