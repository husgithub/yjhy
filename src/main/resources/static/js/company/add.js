layui.use(['form', 'laydate'], function () {
    //初始化时间选择器
    var laydate = layui.laydate;
    var layer = layui.layer;
    var form = layui.form;
    laydate.render({
        elem: '#cRegtime'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#bnStart'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#bnEnd'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#dsChecktime1'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#gsChecktime1'
        , type: 'datetime'
    });

    //自定义验证规则
    form.verify({
        cName: function (value) {
            if (!value) {
                return '请填写公司名称！';
            }
        },
        cCapital: [/^[0-9]+([.]{1}[0-9]+){0,1}$/, '注册资本必须为数字！'],
        lName: function (value) {
            if (!value) {
                return '请填写法人/股东姓名！';
            }
        },
        lAllocation: function (value) {
            var regExp = /^[0-9]+([.]{1}[0-9]+){0,1}$/;
            if (value && !regExp.test(value)) {
                return '股权请填入数字！';
            }
        },
        time: function (value) {
            if (!value) {
                return "请选择一个日期！";
            }
        }

    });

    //添加法人输入框（在layui.use内部必须使用window.funName 直接function funcName不行）
    window.legalPersonAdd = function () {
        //此处应该使用下标而不应该用tr的个数
        var num = parseInt($("#lp-tbody").find("tr:last input[name='index']").val()) + 1;
        var tr = '<tr><td align="center"><input type="hidden" value="' + num + '" name="index"/><div class="layui-input-block">' +
            '<select name="legalPersons[' + num + '].lType">'
            + '<option value="gudong">股东</option><option value="faren">法人</option></select></div></td>'
            + '<td align="center"><input type="text" class="layui-input" placeholder="请输入姓名" name="legalPersons[' + num + '].lName" lay-verify="lName"></td>'
            + '<td align="center"><input type="text" class="layui-input" placeholder="请输入身份证号" name="legalPersons[' + num + '].lIdcard"></td>'
            + '<td align="center"><input type="text" class="layui-input" placeholder="请输入股权分配" name="legalPersons[' + num + '].lAllocation" lay-verify="lAllocation"></td>'
            + '<td align="center"><input type="text" class="layui-input" placeholder="请输入职务" name="legalPersons[' + num + '].lJob"></td>'
            + '<td align="center"><button type="button" class="layui-btn layui-btn-sm layui-btn-danger" onclick="removeLP(this)"><i class="layui-icon"></i></button>'
            + '</td></tr>';
        $("#lp-tbody").append(tr);
        form.render();
    }

    //法人输入框删除方法
    window.removeLP = function (obj) {
        var len = $("#lp-tbody").find("tr").length;
        if (len <= 1) {
            layer.msg('至少填写一个公司法人/股东！', {icon: 5});
            return;
        }
        $(obj).parent("td").parent("tr").remove();
    }

    //处理入参（法人数组存在下标不对的情况）
    window.dealWithReq = function (req) {
        if (!req) {
            return [];
        }
        //获取法人真实个数
        var num = parseInt($("#lp-tbody").find("tr").length);
        var index = -1;
        var indexArr = [];      //保存已经迭代的下标
        for (var i = 0; i < req.length; i++) {
            var obj = req[i];
            if (obj.name.indexOf("legalPersons") == -1) {
                continue;
            }
            var thisIndex = parseInt(obj.name.substring(obj.name.indexOf("[") + 1, obj.name.indexOf("]")));
            if (!indexArr.contains(thisIndex)) {
                indexArr.push(thisIndex);
                index++;
            }
            obj.name = obj.name.substring(0, obj.name.indexOf("[") + 1) + index + obj.name.substring(obj.name.indexOf("]"), obj.name.length);
        }
        return req;
    }

    window.closeThisWin = function () {
        var index = parent.layer.getFrameIndex(window.name);
        if (parent.tableReload) {
            parent.tableReload();
        }
        if (parent.layer.close) {
            parent.layer.close(index);
        }
    }

    //监听保存提交
    form.on('submit(saveSubmit)', function (data) {
        var data = $("#compAddForm").serializeArray();
        $.post("/company/addSave", data, function (result) {
                if (result.code == 0) {
                    layer.alert("公司信息添加成功！", function () {
                        closeThisWin();
                    });
                }
            },
            "json");
        return false;
    });

    //监听修改提交
    form.on('submit(editSubmit)', function (data) {
        var data = $("#compAddForm").serializeArray();
        data = dealWithReq(data);
        $.post("/company/editSave", data, function (result) {
                if (result.code == 0) {
                    layer.alert("公司信息修改成功！", function () {
                        closeThisWin();
                    });
                }
            },
            "json");
        return false;
    });

});
