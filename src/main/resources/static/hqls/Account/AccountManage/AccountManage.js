layui.use(['jquery', 'layer','laypage'], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	var laypage=layui.laypage;
	$('.Accountcz').on('click', function() {

		layer.open({
			type: 1, //添加一个模板
			title: '充值',
			content: $('#AccountczPage'), //弹出框的内容
			skin: 'layui-layer-lan', //弹框主题
			area: '500px', //宽高
			cancel: function() {
				//右上角关闭的回调
			},
			shade: 0
		})
	})
	$('.okAccountCz').on('click', function() {
		//close中的参数是type的类型
		layer.close(1);
		layer.open({
			type: 1, //添加一个模板
			title: '成功',
			content: $('#czSucces'), //弹出框的内容
			skin: 'layui-layer-lan', //弹框主题
			area: '300px', //宽高
			cancel: function() {
				//右上角关闭的回调
			},
			shade: 0
		})
	})

	laypage({
		cont: 'pages',
		pages: 10, //总的分页数
		groups: 5, //展示的页数
		first: false,
		last: false,
		skip: true,
		jump: function(obj, first) {
			if(!first) {
				layer.msg('第 ' + obj.curr + ' 页');
			}
		}
	})

})