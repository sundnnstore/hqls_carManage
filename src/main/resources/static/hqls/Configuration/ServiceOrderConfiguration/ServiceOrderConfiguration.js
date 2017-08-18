layui.use(['jquery', 'layer','laypage'], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	var  laypage=layui.laypage;
	var pageSize = 10;
	$('#addService').on('click', function() {
		layer.open({
			type: 1, // 添加一个模板
			title: '添加服务订单配置',
			content: $('#addServicePage'), // 弹出框的内容
			skin: 'layui-layer-lan', // 弹框主题
			area: '800px', // 宽高
			cancel: function() {
				// 右上角关闭的回调
			},
			shade: 0
		})
	})
	$('.toView').on('click', function() {
		layer.open({
			type: 1, // 添加一个模板
			title: '图片查看',
			content: `
				<div style='background-color:red'>
					<img src="images/logo.png"/>
				</div>
			`, // 弹出框的内容
			skin: 'layui-layer-lan', // 弹框主题
			area: '800px', // 宽高
			cancel: function() {
				// 右上角关闭的回调
			},
			shade: 0
		})
	})
	// 点击上架还是下架
	$('.uORd').on('click', function() {
		console.log('zq');
		layer.msg($(this).text() + '成功')
	})
	getData(1);
	$("#searchService").click(function(){
		getData(1);
	});
	
	function getData(pageIndex){
		$.ajax({
			url : "http://localhost:8881/findservicetypesbytypename",
			type : "get",
			async : false,
			data : {"typeName":$("#service_title").val(),"pageSize":pageSize,"pageIndex":pageIndex},
			success : function(data){
				comboTable(data,pageIndex);
			}
		});
	}
	
	function initPage(totalCount,pageIndex){
		//		page
		laypage({
			cont: 'pages',
			pages: Math.ceil(totalCount/pageSize), // 总的分页数
			groups: 5, // 展示的页数
			first: 1,
			last: Math.ceil(totalCount/pageSize),
			skip: true,
			curr:pageIndex,
			jump: function(obj, first) {
				if(!first) {
					getData(obj.curr);
				}
			}
		})
	}
	
	function comboTable(res,pageIndex){
		var data = res.result;
		var trs = "";
		for(i=0;i<data.length;i++){
			var tr = data[i];
			trs += '<tr>'+
					'<td>'+tr.serviceTypeName+'</td>'+
					'<td>'+tr.serviceTypeContent+'</td>'+
					'<td>'+tr.serviceAmount+'</td>'+
					'<td><button class="layui-btn toView" >查看</button></td>'+
					'<td>';
			if(tr.is_usable){
				trs += '<button  class="layui-btn  layui-btn-primary uORd">下架</button>';
			}else{
				trs += '<button class="layui-btn layui-btn-normal uORd" >上架</button>';
			}
			trs +='</td></tr>'
		}
		$("#service_tb").html(trs);
		initPage(res.totalCount,pageIndex);
	}

})