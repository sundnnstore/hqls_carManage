layui.use(['layer', 'form'], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form;
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
                btn: ['提交', ''],
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) {
                    console.log(layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        }
    }
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
    $('.layui-table tbody').on('click', '#isUse', function() {
        // 调上下架接口

        // 上架和下架按钮切换
        if ($(this).text() === '上架') {
            $(this).removeClass('layui-btn-normal').addClass('layui-btn-primary');
            $(this).text('下架')
        } else {
            $(this).removeClass('layui-btn-primary').addClass('layui-btn-normal');
            $(this).text('上架')
        }
    });
});