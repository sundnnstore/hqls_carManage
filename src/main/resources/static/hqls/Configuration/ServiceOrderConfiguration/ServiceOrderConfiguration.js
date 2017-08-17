layui.use(['jquery', 'layer'], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	$('#addService').on('click', function() {
		layer.open({
			type: 1, //添加一个模板
			title: '添加服务订单配置',
			content: $('#addServicePage'), //弹出框的内容
			skin: 'layui-layer-lan', //弹框主题
			area: '800px', //宽高
			cancel: function() {
				//右上角关闭的回调
			},
			shade: 0
		})
	})
	$('.toView').on('click', function() {
		layer.open({
			type: 1, //添加一个模板
			title: '图片查看',
			content: `
				<div style='background-color:red'>
					<img src="images/logo.png"/>
				</div>
			`, //弹出框的内容
			skin: 'layui-layer-lan', //弹框主题
			area: '800px', //宽高
			cancel: function() {
				//右上角关闭的回调
			},
			shade: 0
		})
	})
	//点击上架还是下架
	$('.uORd').on('click',function(){
		console.log('zq');
		layer.msg($(this).text()+'成功')
	})
})