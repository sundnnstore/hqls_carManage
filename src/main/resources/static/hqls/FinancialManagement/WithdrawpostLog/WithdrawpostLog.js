layui.use(['laypage', 'layer'], function() {
    var $ = layui.jquery;
    var laypage = layui.laypage;
    var layer = layui.layer;
    laypage({
        cont: 'pages',
        pages: 10, //总的分页数
        groups: 5, //展示的页数
        first: false,
        last: false,
        skip: true,
        jump: function(obj, first) {
            if (!first) {
                layer.msg('第 ' + obj.curr + ' 页');
            }
        }
    })
})