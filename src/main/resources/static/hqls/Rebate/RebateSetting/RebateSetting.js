layui.use(['layer', 'laydate', 'jquery'], function() {
	var laydate = layui.laydate;
	var $ = layui.jquery;
	var layer = layui.layer;
	//搜索事件
	$('#searchRebate').click(function() {

	})
	//添加事件
	$('#addRebate').click(function() {

		//			layer.msg("132")
		layer.open({
			type: 1, //添加一个模板
			title: '添加返利规则',
			content: $('#addRebatepage'), //弹出框的内容
			skin: 'layui-layer-lan', //弹框主题
			area: '800px', //宽高
			btn: ['确定', '取消'],
			yes: function(index, layero) {
				//确定的回调
			
				layer.close(index);//关闭弹框
			},
			btn2: function() {
				//取消的回调
//				console.log('取消')
			},
			cancel: function() {
				//右上角关闭的回调
			},
			shade:0
		})
	})
	$('.layui-select').on('change',function(){
		
		console.log($(this).children('option:selected').val());
	})
})