<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/14
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
    <%@ include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/role.js"></script>
</head>
<body>
<table id="role_datagrid"></table>
	<!-- 权限加载按钮 -->
	<div id="role_datagrid_reload">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
			data-cmd="reload">加载权限</a>
	</div>
	<div id="role_datagrid_btn">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
    </div>

</div>
<div id="role_dialog">
    <form id="role_dialog_form" method="post">
        <input type="hidden" name="id" />
        <table align="center">
            <tr>
                <td>角色名称:<input name="name"/></td>
                <td>角色编号:<input name="sn"/></td>
            </tr>
        <tr>
            <td><table id="self_permission_datagrid" ></table></td>
            <td><table id="all_permission_datagrid" ></table></td>
        </tr>
        </table>



    </form>
    <div id="role_dialog_bb">
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
    </div>
</div>


</body>
</html>
