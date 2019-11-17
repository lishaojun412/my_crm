<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="myFn" uri="http://lishaojun.top/java/crm" %>
<html>
<head>
    <title>员工管理</title>
    <%@ include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/employee.js"></script>
</head>
<body>
<table id="employee_datagrid"></table>

<div id="employee_datagrid_btn">
    <c:if test="${myFn:checkPermission('top.lishaojun.crm.web.controller.EmployeeController:save')}">
        <a class="easyui-linkbutton"   iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    </c:if>
    <c:if test="${myFn:checkPermission('top.lishaojun.crm.web.controller.EmployeeController:update')}">
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </c:if>
    <c:if test="${myFn:checkPermission('top.lishaojun.crm.web.controller.EmployeeController:delete')}">
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">离职</a>
    </c:if>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>

    <div>
        <form id="employee_searchForm">
            关键字:<input type="text" name="keyword" placeholder="编号和名称">
            日期:<input class="easyui-datebox" name="beginDate">
            -
            <input class="easyui-datebox" name="endDate">
            状态:
            <select name="state">
                <option value="">全部</option>
                <option value="1">正常</option>
                <option value="0">离职</option>
            </select>
            <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="searchContent">查询</a>
        </form>
    </div>
</div>



<div id="employee_dialog">
    <form id="employee_dialog_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center">
            <tr>
                <td>用户名</td>
                <td><input class="easyui-validatebox" name="username"
                           data-options="required:true,validType:'length[3,10]'"/></td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td><input class="easyui-validatebox" name="realname" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input class="easyui-validatebox" data-options="required:true,validType:'email'" name="email"/></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input name="tel"/></td>
            </tr>
            <tr>
                <td>部门</td>
                <td><input class="easyui-combobox easyui-validatebox" name="dept.id"
                           data-options=" url: '/department_queryForEmp',valueField:'id',textField:'name'"></td>
            </tr>
            <tr>
                <td>角色</td>
                <td><input class="easyui-combobox" id="employee_role_combobox"
                           data-options=" url:'/role_queryRoleByEmp', multiple:true, valueField:'id',textField:'name'">
                </td>
            </tr>

        </table>
    </form>
    <div id="employee_dialog_bb">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
    </div>
</div>

</body>
</html>
