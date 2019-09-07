layui.use(['table', 'laydate'], function () {
    //初始化时间选择器
    var laydate = layui.laydate;
    laydate.render({
        elem: '#cRegtimeStart'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#cRegtimeEnd'
        , type: 'datetime'
    });

    //初始化表格
    var table = layui.table;

    table.render({
        id: 'compReload',
        elem: '#company'
        , url: '/company/getLists'
        , method: 'post'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , toolbar: '#toolbarComp'
        , title: '用户数据表'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {
                field: 'cName', title: '公司名称', width: 240, templet: function (res) {
                    return '<a href="javascript:openWin(\'/company/toEdit?id=' + res.id + '\')" class="layui-table-link" target="_blank">' + res.cName + '</a>'
                }
            }
            , {field: 'cAddress', title: '地址'}
            , {field: 'cContacts', title: '联系人'}
            , {field: 'cEmail', title: '邮箱'}
            , {
                field: 'cRegtime', title: '注册日期', templet: function (res) {
                    return DateFormat.format(new Date(res.cRegtime));
                }
            }
            , {field: 'cRegcode', title: '注册号'}
            , {field: 'totalMoney', title: '收益（元）'}
            , {
                field: 'cModification', title: '最后修改时间', templet: function (res) {
                    return DateFormat.format(new Date(res.cModification));
                }
            }
            , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
        ]]
        , page: true
    });

    //头工具栏事件
    table.on('toolbar(company)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'addCompany':
                openWin('/company/toAdd');
                break;
            case 'export':
                //layer.msg(checkStatus.isAll ? '全选' : '未全选');
                var data = checkStatus.data;
                var ids = getSelectedIds(data);
                if (!ids) {
                    return;
                }
                window.location.href = "/company/export.do?ids=" + ids;
                break;
            case 'exportAll':
                layer.confirm('确定导出所有数据吗？', function (index) {
                    window.location.href = "/company/export.do";
                    layer.close(index);
                });
                break;
        }
    });

    //监听行工具事件
    table.on('tool(company)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除“' + data.cName + '”相关信息吗？', function (index) {
                $.post("/company/delCompany", {id: data.id}, function (result) {
                        if (result.code != 0) {
                            return;
                        }
                        obj.del();
                        layer.close(index);
                    },
                    "json");
            });
        } else if (obj.event === 'edit') {
            openWin('/company/toEdit?id=' + data.id);
        }
    });
    //表格刷新方法
    window.tableReload = function () {
        var cRegtimeStart = $("#cRegtimeStart").val();
        var cRegtimeEnd = $("#cRegtimeEnd").val();
        if (cRegtimeStart && cRegtimeEnd) {
            var startDate = DateFormat.formatToDate(cRegtimeStart);
            var endDate = DateFormat.formatToDate(cRegtimeEnd);
            if (startDate.getTime() > endDate.getTime()) {
                layer.msg('开始时间不能大于结束时间！', {icon: 5});
                return;
            }
        }
        table.reload('compReload', {
            where: { //设定异步数据接口的额外参数，任意设
                cName: $("#cName").val(),
                cContacts: $("#cContacts").val(),
                cRegcode: $("#cRegcode").val(),
                cRegtimeStart: cRegtimeStart,
                cRegtimeEnd: cRegtimeEnd
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    }
    window.openWin = function (url) {
        //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        layer.open({
            type: 2,
            area: ['100%', '100%'],
            scrollbar: true,
            content: [url],
            //关闭窗口回调
            cancel: function (index, layero) {
                //调用父页面刷新列表方法
                tableReload();
            }
        });
    }

    window.getSelectedIds = function (data) {
        var ids = "";
        if (!data || data.length <= 0) {
            layer.msg('请至少选择一条导出的记录！', {icon: 5});
            return ids;
        }
        $(data).each(function (i, obj) {
            ids += obj.id + ",";
        });
        if (ids.indexOf(",") != -1) {
            ids = ids.substring(0, ids.length - 1);
        }
        return ids;
    }

    $("#search").click(function () {
        tableReload()
    });
});