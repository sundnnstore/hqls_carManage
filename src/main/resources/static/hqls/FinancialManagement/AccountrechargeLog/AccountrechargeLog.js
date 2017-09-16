layui.use(['jquery', 'laypage', 'layer'], function() {
	
	var $ = layui.jquery;
	var layer = layui.layer;
	var laypage=layui.laypage;
	var orderId;
	var pageSize = 10;
	var OrderParam = {};
	
	function generatorParam() {
		OrderParam.customerName = $("#customer_name").val();
		OrderParam.mobile = $("#mobile").val();
		OrderParam.storeId = $("#store_id").val() == 0 ? null :$("#store_id").val();
		OrderParam.flowStatus = $("#flow_status").val() == 0 ? null :$("#flow_status").val();
		OrderParam.createTime = $("#createTime").val();
	}
	
	$("#searchFlow").click(function() {
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
					<td>${data[i].transactionNo}</td>
					<td>${data[i].changeMoney}</td>
					<td>${data[i].flowStatusDesc}</td>
					<td>${data[i].createTime}</td></tr>`;
			
			/*<td>${data[i].bank}</td>
			<td>${data[i].account}</td>
			<td>${data[i].openBank}</td>*/
		}
		
		$("#log_tb").html(html);
		initPage(res.totalCount, pageIndex);
	}
	
	// 初始化页面
	getFlowList(1);
	function getFlowList(pageIndex) {
		$.ajax({
			url: "/findflowlistbycontidion?pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&changeType=" + 1,
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
})