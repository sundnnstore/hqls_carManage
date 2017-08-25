/*layui.use(['jquery', 'layer','laypage'], function() {
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

})*/
layui.use(['jquery', 'laypage', 'layer'], function() {
	
	var $ = layui.jquery;
	var layer = layui.layer;
	var laypage=layui.laypage;
	var orderId;
	var pageSize = 2;
	var OrderParam = {};
	
	function generatorParam() {
		OrderParam.customerName = $("#customer_name").val();
		OrderParam.mobile = $("#mobile").val();
		OrderParam.storeId = $("#store_id").val() == 0 ? null :$("#store_id").val();
	}
	
	$("#search").click(function() {
		generatorParam();
		getFlowList(1);
	});
	
	/**
	 * 页面初始化加载所有门店
	 * @returns
	 */
	function initStore() {
		$.ajax({
			url: "/findallstore",
			type: "GET",
			async : false,
			success: function (data) {
				var html = comboSelect(data.result);
				$("#store_id").html(html);
			}
		});
	}
	initStore();
	
	function comboSelect(data) {
		var html = "";
		html += `<option value=0>全部</option>`;
		for (var i = 0; i < data.length; i++) {
			html += `<option value="${data[i].key}">${data[i].value}</option>`;
		}
		return html;
	}
	
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
			html += `<td>${data[i].storeName}</td>
					<td>${data[i].customerName}</td>
					<td>${data[i].mobile}</td>
					<td>${data[i].balance}</td>
					<td><button class="layui-btn layui-btn-normal Accountcz">充值</button></td>
					</tr>`;
		}
		
		$("#tb_store_finance").html(html);
		initPage(res.totalCount, pageIndex);
	}
	
	// 初始化页面
	getFlowList(1);
	function getFlowList(pageIndex) {
		$.ajax({
			url: "/findstorefinancelist?pageIndex=" + pageIndex + "&pageSize=" + pageSize,
			type: "GET",
			async : false,
			data: OrderParam,
			success: function (data) {
				if (data.result.length == 0) {
					$("#tb_store_finance").html("<tr><td colspan=11 style='font-size:18px;text-align:center;'>暂无记录</td></tr>");
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
})