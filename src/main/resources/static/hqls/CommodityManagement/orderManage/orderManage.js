layui.use(['layer', 'jquery', 'laypage'], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	var  laypage=layui.laypage;
	var orderId;

	//定义搜索参数
	var OrderParam = {};
	var pageSize = 10;
	//订单详情
	$('body').on('click', '.detail', function() {
		orderId = $(this).val();
		comboGoodsTable(orderId);
		AlertDiaog('订单详情',$('#orderdetailPage'),'800px')
	})
	//发货点击事件
	$('body').on('click', '.ship', function() {
		event.stopPropagation();
		//查询所有物流公司
		allLogisticsCompany();
		orderId = $(this).val();
		//清空弹出页
		$('input[name=logistics_no]').val('');
		$('#remark').val('');
		AlertDiaog('发货信息',$('#shipPage'),'800px')
	})
	
	//添加物流备注信息
	$('body').on('click', '.logisticsRemark', function() {
		event.stopPropagation();
		orderId = $(this).val();
		$('#remark_only').val('');
		AlertDiaog('备注信息',$('#logisticsRemark'),'800px')
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
					reqOrderList(obj.curr);
				}
			}
		})
	}
	
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
				$('select[name=store_id]').html(html);
			}
		});
	}
	initStore();
	
	function comboSelect(data) {
		var html = "";
		html += `<option value="0">全部</option>`;
		for (var i = 0; i < data.length; i++) {
			html += `<option value="${data[i].key}">${data[i].value}</option>`;
		}
		return html;
	}
	
	/**
	 * 查看订单中的商品详情
	 * @returns
	 */
	function comboGoodsTable(id) {
		console.log(id);
		$.ajax({
			url: '/getpartsbyorderid?orderId=' + id,
			type: 'GET',
			async: false,
			success: function (data) {
				var html = "";
				var res = data.result;
				for (var i = 0; i < res.length; i++) {
					html += `<tr>
							<td>${res[i].partsName}</td>
							<td>${res[i].partsModel}</td>
							<td>${res[i].partsSpec}</td>
							<td>${res[i].purchaseNum}</td>
							<td>${res[i].buyPrice}</td>
							<td>${res[i].curPrice}</td>
							</tr>`;
				}
				$('.part_detail tbody').html(html);
			}
		});
	}
	
	/**
	 * 订单搜索
	 */
	$("#searchOrder").click(function() {
		generatorParam();
		reqOrderList(1);
	});
	
	// 初始化页面
	reqOrderList(1);
	function reqOrderList(pageIndex) {
		$.ajax({
			url: "/findorderlistbycondition?pageIndex=" + pageIndex + "&pageSize=" + pageSize,
			type: "GET",
			async : false,
			data: OrderParam,
			success: function (data) {
				if (data.result.length == 0) {
					$(".order_list tbody").html("<h4>暂无订单</h4>");
				} else {
					comboTable(data, pageIndex);
				}
			},
			error: function () {
				console.log("发生异常");
			}
			
		});
	}
	
	function comboTable(res, pageIndex) {
		var data = res.result;
		var html = "";
		for (var i = 0; i < data.length; i++) {
			html += `<tr>`;
			html += `<td>${data[i].orderNo}</td>
					<td>${data[i].orderStatusName}</td>
					<td>${data[i].createTime}</td>
					<td>${data[i].address}</td>
					<td>${data[i].userName}</td>
					<td>${data[i].mobile}</td>
					<td>${data[i].storeName}</td>
					<td>${data[i].totalFee}</td>
					<td>`;
			if (data[i].orderStatus == 2) {
				html += `<button value="${data[i].purchaseOrderId}" class="layui-btn cancel">取消</button>`;
				html += `<button value="${data[i].purchaseOrderId}" class="layui-btn layui-btn-normal ship">发货</button>`;
			}
			else if (data[i].orderStatus == 3) {
				html += `<button value="${data[i].purchaseOrderId}" class="layui-btn layui-btn-normal logisticsRemark">备注</button>`;
				html += `<button value="${data[i].purchaseOrderId}" class="layui-btn layui-btn-normal logisticsInfo">物流</button>`;
			}
			html += `<button value="${data[i].purchaseOrderId}" class="layui-btn detail">详情</button>`;
			html += `</td></tr>`;
		}
		
		$("#order_tb").html(html);
		initPage(res.totalCount, pageIndex);
	}

	/**
	 * 生成请求参数
	 * @returns
	 */
	function generatorParam() {
		OrderParam.orderStatus = $("select[name=order_status]").val() == 0 ? null : $("select[name=order_status]").val();
		OrderParam.storeId = $("select[name=store_id]").val() == 0 ? null : $("select[name=store_id]").val();
		OrderParam.userName = $("input[name=contact]").val();
		OrderParam.mobile = $("input[name=mobile]").val();
	}
	
	
	
	/**
	 * 发货操作
	 */
	$('.confirm_ship').click(function() {
		var url = "/shipoperation?orderId=" + orderId;
		var logisticsId = $('select[name=company]').val();
		var logisticsNo = $('input[name=logistics_no]').val();
		var remark = $('#remark').val();
		url += "&logisticsId=" + logisticsId;
		url += "&logisticsNo=" + logisticsNo;
		url += "&remark=" + remark;
		$.ajax({
			url: url,
			type: "GET",
			async: false,
			success: function(data) {
				if (data.errcode == 0) {
					layer.msg("发货完成！");
					layer.closeAll();
					reqOrderList(1);
				}
			},
			error: function() {
				layer.msg("发货操作异常");
			}
		});
	});
	
	//查看物流信息
	$('body').on('click','.logisticsInfo', function() {
//		event.stopPropagation();
		orderId = $(this).val();
		queryLogisticsInfo(orderId);
		AlertDiaog('物流信息',$('#logisticsInfoPage'),['800px','400px'])
	});

	/**
	 * 查找所有物流公司
	 * @returns
	 */
	function allLogisticsCompany() {
		$.ajax({
			url: "/findalllogisticscompany",
			type: "GET",
			async : false,
			success: function (data) {
				var html = "";
				var res = data.result;
				html += `<option value="">请选择</option>`;
				for (var i = 0; i < res.length; i++) {
					html += `<option value="${res[i].key}">${res[i].value}</option>`;
				}
				$('select[name=company]').html(html);
			}
		});
	}

	/**
	 * 查询物流信息
	 * @returns
	 */
	function queryLogisticsInfo(orderId) {
		$.ajax({
			url: '/getlogisticsinfo?orderId=' + orderId,
			type: 'GET',
			async: false,
			success: function (data) {
				comboLogisticsInfo(data.result);
			}
		});
	}

	/**
	 * 组装页面物流信息
	 * @param res
	 * @returns
	 */
	function comboLogisticsInfo(res) {
		var html = "";
		try {
			var data = eval('(' + res + ')');
			console.log(data.Traces);
			html += `<div class="jclogistics"><div><span>运单号码：</span><span>${data.LogisticCode}</span>
				<br/><span>物流公司：</span><span>${data.company}</span></div>
				<div><span>收货地址：</span><span>${data.shipAddress}</span></div></div>
				<div style='border:none;border-top:1px dashed #333 ;margin: 10px auto;width: 97%;'></div>`;
			if (data.Traces.length == 0) {
				html += `<div class="logisList"><div class="logisList-header"><span>暂无物流信息</span></div></div>`;
			} else {
				traces = data.Traces;
				html += `<div class="logisList"><div class="logisList-content">`;
				for (var i = traces.length - 1; i > -1; i--) {
					html += `<div><span>${traces[i].AcceptTime}</span>`;
					html += `<span>${traces[i].AcceptStation}</span></div>`;
				}
				html += `</div></div>`;
			}
		} catch (Exception) {
			if (res.length == 0) {
				html += `<div class="logisList"><div class="logisList-header"><span>暂无物流信息</span></div></div>`;
			} else {
				html += `<div class="logisList"><div class="logisList-content">`;
				for (var i = 0; i < res.length; i++) {
					html += `<div><span>${res[i].createTime}</span>`;
					html += `<span>${res[i].remark}</span></div>`;
				}
				html += `</div></div>`;
			}
		}
		$('#logisticsInfoPage').html(html);
	}
	
	//取消订单
	$('body').on('click','.cancel', function(event) {
		event.stopPropagation();
		orderId = $(this).val();
		$.ajax({
			url: '/cancelorder?orderId=' + orderId,
			type: 'GET',
			async: false,
			headers: {
				'Authorization': localStorage.token
			},
			success: function (data) {
				if (data.errcode == 0) {
					layer.msg("订单取消成功！");
					reqOrderList(1);
				} else {
					layer.msg("订单取消失败！");
				}
			},
			error: function () {
				layer.msg("订单取消失败！");
			}
		});
	});
	
	//添加物流备注信息
	$('#confirm_logistics_remark').on('click', function(event) {
		event.stopPropagation();
		var remark = $('#remark_only').val();
		if (!remark || remark == "") {
			layer.msg('请先输入要添加的内容');
			return;
		}
		$.ajax({
			url: '/addlogisticsremark',
			type: 'GET',
			async: false,
			data: {'orderId': orderId, 'remark': remark},
			success: function (data) {
				if (data.errcode == 0) {
					layer.msg("添加成功！");
					setTimeout(() => {
						layer.closeAll();
					}, 500);
				} else {
					layer.msg("添加失败！");
				}
			},
			error: function () {
				layer.msg("添加失败！");
			}
		});
	});
	
})