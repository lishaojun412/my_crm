$(function () {
    var roleDialog, roleDatagrid, roleDialogForm, roleDatagridBtn, allPermissionDatagrid, selfPermissionDatagrid;
    roleDialog = $("#role_dialog");
    roleDatagrid = $("#role_datagrid");
    roleDialogForm = $("#role_dialog_form");
    roleDatagridBtn = $("#role_datagrid_btn a");
    allPermissionDatagrid = $("#all_permission_datagrid");
    selfPermissionDatagrid = $("#self_permission_datagrid");

    // 显示员工的状态:
    function stateFormatter(value, record, index) {
        if (value) {
            return "<font color='green'>正常</font>";
        } else {
            return "<font color='red'>离职</font>";

        }
    }

    // 显示部门的名称
    function deptFormatter(value, record, index) {
        if (value) {
            return value.name;

        } else {
            return null;
        }
    }

    roleDatagrid.datagrid({
        url: "role_list",
        fit: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        pageList: [1, 5, 10, 20],
        toolbar: "#role_datagrid_btn",
        columns: [[
            {field: "sn", title: "角色编号", width: 1, align: "center"},
            {field: "name", title: "角色名称", width: 1, align: "center"},
        ]],

    });

    roleDialog.dialog({
        width: 600,
        height: 450,
        buttons: "#role_dialog_bb",
        closed: true

    });


    allPermissionDatagrid.datagrid({
        title: "所有权限",
        width: 250,
        height: 300,
        pagination: true,
        toolbar : "#role_datagrid_reload",
        url: "/permission_list",
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        //数据表头信息
        columns: [[
            {field: "name", title: "名称", width: 1, align: "center"}
        ]],
        onDblClickRow: function (rowIndex, rowData) {
            // 获取自身权限的所有对象
            var selfRows = selfPermissionDatagrid.datagrid("getRows");

            //查询要添加的权限是否在自身对象存在,如果存在,则选中要权限,没有的话,则追加权限
            var flag = false;
            var index = 0;
            for (var i = 0; i < selfRows.length; i++) {
                if (selfRows[i].id == rowData.id) {
                    flag = true;
                    index = i;
                    break;
                }
            }
            if (flag) {//已经存在权限
                selfPermissionDatagrid.datagrid("selectRow", index);
            } else {//不存在权限，追加
                selfPermissionDatagrid.datagrid("appendRow", rowData);
            }
        }
    });
    //设置所有权限的分页为简洁效果
    var pager = allPermissionDatagrid.datagrid("getPager");
    pager.pagination({
        showPageList: false,
        showRefresh: false,
        displayMsg: "",

    });

    selfPermissionDatagrid.datagrid({
        title: "自身权限",
        width: 250,
        height: 300,
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {field: "name", title: "名称", width: 1, align: "center"}
        ]],
        onDblClickRow: function (rowIndex, rowData) {
            selfPermissionDatagrid.datagrid("deleteRow", rowIndex);
        }

    });


    // 获取按钮的点击事件
    $("a").on("click", function () {

        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });


    var cmdObj = {
    		//权限加载
			reload : function() {
			$.ajax({
				type : "POST",
				url : "/permission_reload"
			});
			allPermissionDatagrid.datagrid("load");
		},	
    		
    		

        add: function add() {
            // 打开新增对话框
            roleDialog.dialog("open");
            // 设置标题为新增
            roleDialog.dialog("setTitle", "新增");
            // 清除表单的缓存数据
            $("[name=id],[name=name],[name=sn]").val("");

            /*
             清除自身权限列表的回显数据:为什么这里是rows呢?因为datagrid的行是rows
             {"total":xx,"rows":[xxxx]}
             */
            selfPermissionDatagrid.datagrid("loadData", {rows: []});
            

        },
        edit: function () {
            // 返回选中的行对象
            var rowData = roleDatagrid.datagrid("getSelected");
            if (rowData) {
                // 打开编辑对话框
                roleDialog.dialog("open");
                // 打开编辑对话框
                roleDialog.dialog("setTitle", "编辑");
                // 清除表单的缓存数据
                $("[name=id],[name=name],[name=sn]").val("");

                // 回显表单的数据
                roleDialogForm.form("load", rowData);
                // 回显自身权限的数据
                selfPermissionDatagrid.datagrid("options").url = "/permission_queryByRid";
                selfPermissionDatagrid.datagrid("load", {
                    rid: rowData.id
                })


            } else {
                $.messager.alert("温馨提示", "请选中要编辑数据", "info");
            }
        },

        del: function () {
            // 返回选中的行对象
            var row = roleDatagrid.datagrid("getSelected");
            if (row) {
                $.messager.confirm("温馨提示", "你确定要删除这个角色的数据吗", function (yes) {
                    if (yes) {
                        $.get("/role_delete?id=" + row.id, function (data) {
                            if (data.success) {
                                roleDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert("温馨提示", data.msg, "warning");
                            }
                        });
                    }
                });

            } else {
                $.messager.alert("温馨提示", "请选中要删除的行!", "warning");
            }
        },
        refresh: function () {
            roleDatagrid.datagrid("reload");
        },
        searchContent: function () {
            var param = {};
            var paramArr = $("#role_searchForm").serializeArray();
            for (var i = 0; i < paramArr.length; i++) {
                param[paramArr[i].name] = paramArr[i].value;
            }
            console.log(param);
            roleDatagrid.datagrid("load", param);

        },
        save: function () {
            var url = null;
            var id = $("input[name=id]").val();//Role角色主键ID
            console.debug(id);
            if (id) {
                url = "/role_update"
            } else {
                url = "/role_save"
            }

            roleDialogForm.form("submit", {
                url: url,
                //提交之前的回调函数
                onSubmit: function (param) {
                    var selfRows = selfPermissionDatagrid.datagrid("getRows");
                    for (var i = 0; i < selfRows.length; i++) {
                        param["permissions[" + i + "].id"] = selfRows[i].id;
                    }

                },
                success: function (data) {
                    data = $.parseJSON(data)
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                        roleDialog.dialog("close");
                        roleDatagrid.datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                }
            });

        },
        cancel: function () {
            roleDialog.dialog("close");
        }


    };

});




