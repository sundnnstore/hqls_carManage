layui.use(['layer', 'jquery', 'laypage'], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	var laypage=layui.laypage;
	//订单详情
	$('tr').on('click', function() {
		AlertDiaog('订单详情',$('#orderdetailPage'),'800px')
	})
	//发货点击事件
	$('.Ship').on('click', function() {
		event.stopPropagation();
		allLogisticsCompany();
		OrderParam.orderId = $(this).val();
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
					requestOrderList(obj.curr);
				}
			}
		})
	}
	
	
	//定义搜索参数
	var OrderParam = {};
	var pageSize = 10;

	/**
	 * 页面初始化加载所有门店
	 * @returns
	 */
	function initStore() {
		$.ajax({
			url: "http://127.0.0.1:8881/findallstore",
			type: "GET",
			async : false,
			success: function (data) {
				var html = comboSelect(data.result);
				$('select[name=store_id]').append(html);
			}
		});
	}
	initStore();
	
	function comboSelect(data) {
		var html = "";
		for (var i = 0; i < data.length; i++) {
			html += `<option value="` + data[i].key + `">` + data[i].value + `</option>`;
		}
		return html;
	}
	
	/**
	 * 订单搜索
	 */
	$("#searchOrder").click(function() {
		generatorParam();
		requestOrderList(1);
	});
	
	function requestOrderList(pageIndex) {
		$.ajax({
			url: "http://localhost:8881/findpurchorder?pageIndex=" + pageIndex + "&pageSize=" + pageSize,
			type: "GET",
			async : false,
			data: OrderParam,
			success: function (data) {
				if (data.result.length == 0) {
					$(".order_list tbody").append("<h4>暂无订单</h4>");
				} else {
					comboTable(data, OrderParam.orderStatus, pageIndex);
				}
			},
			error: function () {
				console.log("发生异常");
			}
			
		});
	}
	
	function comboTable(res, orderStatus, pageIndex) {
		var data = res.result;
		var html = "";
		for (var i = 0; i < data.length; i++) {
			html += `<tr>`;
			html += `<td>订单编号:</td><td>` + data[i].orderNo + `</td>
					<td>订单状态:</td><td>` + data[i].orderStatusName + `</td>
					<td>订单时间:</td><td>` + data[i].createTime + `</td>
					<td>收货地址:</td><td>` + data[i].address + `</td>
					<td>联系人:</td><td>` + data[i].userName + `</td>
					<td>联系电话:</td><td>` + data[i].mobile + `</td>
					<td>门店::</td><td>` + data[i].storeName + `</td>
					<td>金额总计:</td><td>` + data[i].totalFee + `</td>`;
			if (orderStatus == 2) {
				html += `<td><button value="`+ data[i].purchaseOrderId +`" class="layui-btn Ship">发货</button></td>`;
			}
			else if (orderStatus == 3) {
				html += `<td><button value="`+ data[i].purchaseOrderId +`" class="layui-btn layui-btn-normal logisticsInfo">查看物流</button></td>`;
			}
			html += `</tr>`;
		}
		
		$(".order_list tbody").append(html);
		initPage(res.totalCount, pageIndex);
	}

	/**
	 * 生成请求参数
	 * @returns
	 */
	function generatorParam() {
		OrderParam.orderStatus = $("select[name=order_status]").val();
		OrderParam.storeId = $("select[name=store_id]").val();
		OrderParam.userName = $("input[name=contact]").val();
		OrderParam.mobile = $("input[name=mobile]").val();
	}
	
	/**
	 * 查找所有物流公司
	 * @returns
	 */
	function allLogisticsCompany() {
		$.ajax({
			url: "http://127.0.0.1:8881/findalllogisticscompany",
			type: "GET",
			async : false,
			success: function (data) {
				var html = "";
				var res = data.result;
				for (var i = 0; i < res.length; i++) {
					html += `<option value="` + res[i].key + `">` + res[i].value + `</option>`;
				}
				$('select[name=company]').append(html);
			}
		});
	}
	
	$('.confirm_ship').click(function() {
		var url = "http://localhost:8881/shipoperation?orderId=" + OrderParam.orderId;
		OrderParam.logisticsId = $('select[name=company]').val();
		OrderParam.logisticsNo = $('input[name=logistics_no]').val();
		OrderParam.remark = $('#remark').val();
		if (OrderParam.logisticsId != undefind) {
			url += "&logisticsId=" + OrderParam.logisticsId;
		}
		if (OrderParam.logisticsNo != undefind) {
			url += "&logisticsNo=" + OrderParam.logisticsNo;
		}
		if (OrderParam.remark != undefind) {
			url += "&remark=" + OrderParam.remark;
		}
		$.ajax({
			url: url,
			type: "GET",
			async: false,
			success: function(data) {
				if (data.errcode == 0) {
					alert("发货完成");
				}
			},
			error: function() {
				alert("发货操作异常");
			}
		});
	});
	
})