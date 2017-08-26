/*layui.use(['layer', 'laydate', 'jquery'], function() {
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
})*/

layui.use(['jquery', 'laypage', 'layer'], function() {
	
	var $ = layui.jquery;
	var layer = layui.layer;
	var laypage=layui.laypage;
	var orderId;
	var pageSize = 5;
	var OrderParam = {};
	
	function generatorParam() {
// OrderParam.storeId = $("select[name=store_id]").val() == 0 ? null :
// $("select[name=store_id]").val();
		OrderParam.operateUserName = $("#customer_name").val();
		OrderParam.returnType = $("#return_type").val() == 0 ? null :$("#return_type").val();
		OrderParam.operateUserName = $("#operateUserName").val();
		OrderParam.createTime = $("#createTime").val();
		
	}
	
	$("#searchRebate").click(function() {
		generatorParam();
		getFlowList(1);
	});
	
	function initPage(totalCount,pageIndex){
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
					getFlowList(obj.curr);
				}
			}
		})
	}
	
	function comboTable(res, pageIndex) {
		console.log(res);
		var data = res.result;
		var html = "";
		for (var i = 0; i < data.length; i++) {
			html += `<tr>`;
			html += `<td>${data[i].returnTypeDesc}</td>
					<td>${data[i].content}</td>
					<td>${data[i].returnType}</td>
					<td>${data[i].createTime}</td>
					</tr>`;
		}
		
		$("#log_tb").html(html);
		initPage(res.totalCount, pageIndex);
	}
	
	// 初始化页面
	getFlowList(1);
	function getFlowList(pageIndex) {
		$.ajax({
			url: "/findcashbacklist?pageIndex=" + pageIndex + "&pageSize=" + pageSize,
			type: "GET",
			async : false,
			data: OrderParam,
			success: function (data) {
				if (data.result.length == 0) {
					$("#log_tb").html("<tr><td colspan=11 style='font-size:18px;text-align:center;'>暂无记录</td></tr>");
					$(".layui-laypage").hide();
				} else {
					comboTable(data, pageIndex);
				}
			},
			error: function () {
				console.log("发生异常");
			}
		});
	}
	
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
				layer.msg("调用添加");
				layer.close(index);//关闭弹框
				addRebate();
			},
			btn2: function() {
				layer.msg("btn2");
				//取消的回调
//				console.log('取消')
			},
			cancel: function() {
				layer.msg("cancel");
				//右上角关闭的回调
			},
			shade:0
		})
	})
	
	
	function addRebate(){
    	var	Param = {};
    	Param.returnType = $("#returnType").val();
    	Param.money = $('input[name=money]').val();
    	Param.returnMoney = $('input[name=returnMoney]').val();
    	var cashBack = JSON.stringify(Param);
    	layer.msg(cashBack);
    	$.ajax({
    		url: "http://127.0.0.1:8881/addcashback",
    		type: 'POST',
    		contentType: "application/json; charset=utf-8",
    		data: cashBack,
    		async: false,
    		success: function(data) {
    			if (data.errmsg == 'success') {
    				layer.msg(data.result);
    			} else {
    				layer.msg('添加失败');
    			}
    			/*search(1);
    			$('input[name=add_mobile]').attr('readonly', false);// 修改完成将input的只读状态改回可编辑
*/    		}
    	});
		
		
	}
})