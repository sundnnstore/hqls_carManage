layui.use(['layer', 'form', 'laypage'], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage;
    	
    // 弹出框
    var title; // 弹出框的标题
    var active = {
        addCommodity: function(othis) {
            var type = othis.data('type'),
                text = othis.text();
            layer.open({
                type: 2,
                area: ['700px', '515px'], // 宽高
                title: title, // 标题
                id: 'HQ' + type, //防止重复弹出
                content: 'BasicInformation/CommodityManage/addCommodity/addCommodity.html', // 内容
                btn: '提交',
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) {
                    console.log(layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        }
    };
    // 触发弹框事件
    $('.commodity').on('click', '#addCommodity', function() {
        var othis = $(this),
            method = othis.data('method');
        if ($(this).text() === '新增') {
            title = '新增';
        } else if ($(this).text() === '查看') {
            title = '查看';
        } else if ($(this).text() === '编辑') {
            title = '编辑';
        }
        active[method] ? active[method].call(this, othis) : '';
    });
    // 上下架事件及结果提示
    $('.layui-table tbody').on('click', '#isUse', function() {
        if ($(this).text() === '上架') {
            /*
                上架操作
            */
            if (true) { //上架成功切换成下架按钮，同时提示用户
                layer.msg('上架成功');
                $(this).removeClass('layui-btn-normal').addClass('layui-btn-primary');
                $(this).text('下架')
            } else { //上架失败不切换按钮，同时提示用户
                layer.msg('上架失败');
            }
        } else {
            /*
                下架操作
            */
            if (true) { //下架成功切换成上架按钮，同时提示用户
                layer.msg('下架成功');
                $(this).removeClass('layui-btn-primary').addClass('layui-btn-normal');
                $(this).text('上架')
            } else { //上架失败不切换按钮，同时提示用户
                layer.msg('下架成功');
            }
        }
    });

    // 分页条
    laypage({
        cont: 'paging',
        pages: 100, //总页数
        groups: 5, //连续显示分页数
        skin: '#1E9FFF', //颜色
        skip: true, //跳转到某页
        jump: function(obj, first) { //触发分页后的回调: obj(object)包括了分页的所有配置信息,first(Boolean)检测页面是否初始加载,可避免无限刷新。
            var curr = obj.curr; //得到了当前页，用于向服务端请求对应数据
        }
    });
});