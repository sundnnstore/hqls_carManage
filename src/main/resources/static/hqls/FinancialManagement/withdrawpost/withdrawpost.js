layui.use(['layer', 'jquery'], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	$('.operating-btn').on('click', function() {
		if($(this).text() == '拒绝') {
			layer.open({
				type: 1, //添加一个模板
				title: '拒绝理由',
				content: `
				<div class="layui-form-item layui-form-text mylayui-item ">
		<label class="layui-form-label">拒绝理由</label>
		<div class="layui-input-block">
			<textarea placeholder="请输入内容" class="layui-textarea"></textarea>
		</div>
	</div>
	<div class="layui-form-item layui-form-text shipbox">
		<button class="layui-btn">拒绝</button>
	</div>
			`, //弹出框的内容
				skin: 'layui-layer-lan', //弹框主题
				area: '800px', //宽高
				cancel: function() {
					//右上角关闭的回调
				},
				shade: 0
			})
		}else{
			layer.msg('同意')
		}
	})
})