layui.use(['layer', 'jquery'], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	//订单详情
	$('tr').on('click', function() {
		AlertDiaog('订单详情',$('#orderdetailPage'),'800px')
	})
	//发货点击事件
	$('.Ship').on('click', function() {
		event.stopPropagation();
		AlertDiaog('发货信息',$('#shipPage'),'800px')
	})
	//查看物流信息
	$('.logisticsInfo').on('click', function() {
		event.stopPropagation();
		AlertDiaog('物流信息',$('#logisticsInfoPage'),['800px','400px'])
	})

	function AlertDiaog(title, id, area) {
		layer.open({
			type: 1, //添加一个模板
			title: title,
			content:id, //弹出框的内容
			skin: 'layui-layer-lan', //弹框主题
			area: area, //宽高
			cancel: function() {
				//右上角关闭的回调
			},
			shade: 0
		})
	}
})