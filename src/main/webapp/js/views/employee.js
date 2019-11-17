$(function() {
    var employeeDialog, employeeDatagrid, employeeDialogForm, employeeDatagridBtn;
    employeeDialog = $("#employee_dialog");
    employeeDatagrid = $("#employee_datagrid");
    employeeDialogForm = $("#employee_dialog_form");
    employeeDatagridBtn = $("#employee_datagrid_btn a");

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
        console.debug(value);
        if (value) {
            return value.name;

        } else {
            return null;
        }
    }

    employeeDatagrid.datagrid({
        url : "employee_list",
        fit : true,
        fitColumns : true,
        singleSelect : true,
        pagination : true,
        pageList : [ 1, 5, 10, 20 ],
        toolbar : "#employee_datagrid_btn",
        onClickRow : function(rowIndex, rowData) {
            if (rowData.state) {////编辑和离职按钮显示正常
                employeeDatagridBtn.eq(1).linkbutton("enable");
                employeeDatagridBtn.eq(2).linkbutton("enable");
            } else {//编辑和离职按钮变灰
                employeeDatagridBtn.eq(1).linkbutton("disable");
                employeeDatagridBtn.eq(2).linkbutton("disable");
            }
        },
        columns : [ [ {
            field : "username",
            title : "账号",
            width : 1,
            align : "center"
        }, {
            field : "realname",
            title : "真实姓名",
            width : 1,
            align : "center"
        }, {
            field : "email",
            title : "邮箱",
            width : 1,
            align : "center"
        }, {
            field : "tel",
            title : "联系电话",
            width : 1,
            align : "center"
        }, {
            field : "inputtime",
            title : "录入时间",
            width : 1,
            align : "center"
        }, {
            field : "dept",
            title : "部门",
            width : 1,
            align : "center",
            formatter : deptFormatter
        }, {
            field : "state",
            title : "状态",
            width : 1,
            align : "center",
            formatter : stateFormatter
        },] ],

    });

    employeeDialog.dialog({
        width : 300,
        height : 300,
        buttons : "#employee_dialog_bb",
        closed : true

    });

    // 获取按钮的点击事件
    $("a").on("click", function() {
        var cmd = $(this).data("cmd");
        console.log(cmd);
        console.log(cmdObj[cmd])
        if (cmd) {
            cmdObj[cmd]();
        }
    });

    var cmdObj = {

        add : function() {
            // 打开新增对话框
            employeeDialog.dialog("open");
            // 设置标题为新增
            employeeDialog.dialog("setTitle", "新增");
            // 清除表单的缓存数据
            $("#employee_dialog_form").form("clear");

        },
        edit : function() {
            // 返回选中的行对象
            var rowData = employeeDatagrid.datagrid("getSelected");
            if (rowData) {
                // 打开编辑对话框
                employeeDialog.dialog("open");
                // 打开编辑对话框
                employeeDialog.dialog("setTitle", "编辑");
                // 清除表单的缓存数据
                employeeDialogForm.form("clear");
                $("#employee_role_combobox").combobox("clear");

                // 回显部门信息
                if (rowData.dept) {
                    rowData["dept.id"] = rowData.dept.id;
                }
                /*
                 * 回显角色列表数据
                 * 根据员工的Id,发送ajax的请求去后台查询该Id的员工对应的权限,这里要求必须是同步的,不然取到数据为null
                 * async:false:关闭异步($.get()和$.post()方式都为异步方式) 同步和异步的区别:
                 * 同步:只有发送请求并响应完毕后,才会执行下面的代码,
                 * 异步:发送请求后不必等响应就继续执行后面的代码,所以后面取到的响应数据为null
                 */
                var html = $.ajax({
                    url : "/employee_queryRoleIdByEid",
                    data : "eid=" + rowData.id,
                    async : false
                }).responseText;
                // html为字符串数组,这里要求要用数组对象,所以要用转换为json对象
                html = $.parseJSON(html);
                $("#employee_role_combobox").combobox("setValues", html);
                // 回显表单的数据
                employeeDialogForm.form("load", rowData);

            } else {
                $.messager.alert("温馨提示", "请选中要编辑数据", "info");
            }
        },

        del : function() {
            // 返回选中的行对象
            var row = employeeDatagrid.datagrid("getSelected");
            if (row) {
                $.messager.confirm("温馨提示", "你确定要删除这个员工的数据吗", function(yes) {
                    if (yes) {
                        // 发送请求去后台删除数据
                        $.get("/employee_delete?id=" + row.id, function(data) {
                            if (data.success) {
                                // 删除成功后,重新加载数据
                                employeeDatagrid.datagrid("reload");
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
        /**
         * 刷新列表
         */
        refresh : function() {
            employeeDatagrid.datagrid("reload");
        },
        /**
         * 高级查询:根据关键字,录入时间,员工状态查询
         */
        searchContent : function() {
            // 创建一个json空对象:存取要查询的条件
            var param = {};
            // 获取表单中所有的数据的数组 [Object { name="keyword", value=""}, Object {
            // name="beginDate", value=""}, Object { name="endDate", value=""}]
            var paramArr = $("#employee_searchForm").serializeArray();
            // 设置格式为 Object { keyword="", beginDate="", endDate=""}的json对象
            for (var i = 0; i < paramArr.length; i++) {
                param[paramArr[i].name] = paramArr[i].value;
                console.log(paramArr[i].name+"----"+paramArr[i].value);
            }
            // 根据查询的条件去重新查询后台,加载到前台中
            employeeDatagrid.datagrid("load", param);

        },
        /**
         * 保存/更新操作: id:null 保存 id:不为null 更新
         */
        save : function() {
            var url = null;
            // 根据id,设置发送的是保存还是更新请求的地址
            var id = $("input[name=id]").val();
            console.log("id" + id);
            if (id) {
                url = "/employee_update"
            } else {
                url = "/employee_save"
            }

            // 表单的提交操作
            employeeDialogForm.form("submit", {
                url : url,
                // 传递选中角色的Id到后台中
                onSubmit : function(param) {
                    if (!employeeDialogForm.form("validate")) {
                        return false;
                    }
                    // 获取选中的角色Id数组
                    var values = $("#employee_role_combobox").combobox(
                        "getValues");
                    for (var i = 0; i < values.length; i++) {
                        param["roles[" + i + "].id"] = values[i];
                    }
                },
                success : function(data) {
                    data = $.parseJSON(data)
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                        employeeDialog.dialog("close");//关闭对话框
                        employeeDatagrid.datagrid("reload");//刷新数据
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                }
            });

        },
        cancel : function() {
            employeeDialog.dialog("close");
        }

    };

});
