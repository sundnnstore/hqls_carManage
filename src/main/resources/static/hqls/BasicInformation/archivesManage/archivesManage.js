layui.use(['laypage', 'layer'], function() {
    var $ = layui.jquery,
        laypage = layui.laypage,
        layer = layui.layer;

    // 新增/编辑档案信息弹框
    $('#myContent').on('click', 'button', function() {
        var othis = $(this), // 当前按钮元素
            method = othis.data('method'),
            title = '',
            content = '';
        layer.closeAll();
        if (method == 'addArchives') {
            title = '新增';
            content = '#archivesBox';
        } else if (method == 'edit') {
            title = '编辑';
            content = '#archivesBox';
        } else if (method == 'isUse') {
            title = '提示'; // 启/禁用
            content = '#archivesState';
            $('#chooseState').text($(this).text());
        } else if (method == 'reset') {
            title = '重置密码';
            content = '#archivesReset';
        }
        title && layer.open({
            type: 1,
            title: title, // 标题
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '350px', // 宽高
            content: $(content),
            btn: title == '提示' ? ['确认', '取消'] : ['提交', '取消'],
            yes: function(index, layero) { // 提交或确认操作, othis代表当前点击的按钮元素
                /*
                    此处回调就有四种操作：新增，编辑，启/禁用，重置密码，可根据title值区分（也可自行寻找方法来处理）
                */
                //  根据启/禁用操作后的结果来决定是否切换按钮以及提示
                if (title == '提示') {
                    if (othis.text() === '启用') {
                        if (true) { //启用成功
                            othis.removeClass('layui-btn-primary').addClass('layui-btn-warm');
                            othis.text('禁用');
                        } else { //启用失败
                            layer.msg('启用失败');
                        }
                    } else {
                        if (true) { //禁用成功
                            othis.removeClass('layui-btn-warm').addClass('layui-btn-primary');
                            othis.text('启用');
                        } else { //禁用失败
                            layer.msg('禁用失败');
                        }
                    }
                }
                layer.close(index);
            },
            btn2: function(index, layero) { // 取消操作
                layer.close(index);
            }
        });
    });

    // 分页条
    laypage({
        cont: 'paging',
        pages: 100, //总页数
        groups: 5, //连续显示分页数
        skip: true, //跳转到某页
        jump: function(obj, first) { //触发分页后的回调: obj(object)包括了分页的所有配置信息,first(Boolean)检测页面是否初始加载,可避免无限刷新。
            var curr = obj.curr; //得到了当前页，用于向服务端请求对应数据
        }
    });
});