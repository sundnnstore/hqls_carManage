layui.use(['form', 'layer'], function() {
    var form = layui.form(),
        layer = layui.layer,
        $ = layui.jquery;
    var active = {
        view: function(othis) {
            var type = othis.data('type'),
                text = othis.text();
            layer.open({
                type: 2,
                area: ['450px', '500px'], // 宽高
                title: '查看', // 标题
                id: 'HQ' + type, //防止重复弹出
                content: 'BasicInformation/StoreManage/storeModel/storeModel.html', // 内容
                btn: '确定',
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                    console.log(index, layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        },
        edit: function(othis) {
            var type = othis.data('type'),
                text = othis.text();
            layer.open({
                type: 2,
                area: ['450px', '500px'], // 宽高
                title: '编辑', // 标题
                id: 'HQ' + type, //防止重复弹出
                content: "BasicInformation/StoreManage/storeModel/storeModel.html?options='edit'", // 内容
                btn: '确定',
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                    console.log(layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        }
    }
    $('.layui-table').on('click', 'a', function() {
        var othis = $(this),
            method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});