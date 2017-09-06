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
		OrderParam.createTime = $("#createTime").val();
		OrderParam.checkStatus = $("#checkStatus").val() == 0 ? null :$("#checkStatus").val();
	}
	
	/**
	 * 页面初始化加载所有门店
	 * 
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
	
	$("#searchFlow").click(function() {
		generatorParam();
		getFlowList(1);
	});
	
	function refuseBtnClick(id,remark){
		$.ajax({
			url: "/updatecheckstatus",
			type: "POST",
			async : false,
			data:{
				"financeFlowId":id,
				"changeType":1,
				"checkStatus":3,
				"remark":remark
			},
			success: function (data) {
				console.log(data.result);
				generatorParam();
				getFlowList(1);
				layer.closeAll();
				layer.msg("审核不通过");
			}
		});
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
		});
		
		$('.operating-btn').on('click', function() {
			var id = $(this).attr("id");
			if($(this).text() == '拒绝') {
				layer.msg("拒绝: "+$(this).attr("id"));
				layer.open({
					type: 1, //添加一个模板
					title: '拒绝理由',
					content: `
							<div class="layui-form-item layui-form-text mylayui-item " style="margin:25px;">
								<label class="layui-form-label">拒绝理由</label>
								<div class="layui-input-block">
									<textarea placeholder="请输入内容" class="layui-textarea" id="remarkTextArea"></textarea>
								</div>
							</div>
							<div class="layui-form-item layui-form-text shipbox">
								<button class="layui-btn" align="center" id="refuseBtn">拒绝</button>
							</div>
				`, //弹出框的内容
					skin: 'layui-layer-lan', //弹框主题
					area: '800px', //宽高
					cancel: function() {
						//右上角关闭的回调
					},
					shade: 0
				});
				$("#refuseBtn").on("click",function(){
					refuseBtnClick(id,$("#remarkTextArea").val());
				});
			}else{
				layer.confirm('是否确定该笔线下充值审核通过？', {
					btn: ['是','否'] // 按钮
				}, function(){
					$.ajax({
						url: "/updatecheckstatus",
						type: "POST",
						async : false,
						data:{
							"financeFlowId":id,
							"changeType":1,
							"checkStatus":2,
							"remark":"审核通过"
						},
						success: function (data) {
							console.log(data.result);
							generatorParam();
							getFlowList(1);
							layer.msg("审核通过！");
						}
					});
				}, function(){
					//layer.msg("拒绝");
				});
			}
		});
		
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
					<td>${data[i].changeMoney}</td>
					<td>${data[i].transactionNo}</td>
					<td>${data[i].checkStatusDesc}</td>
					<td class="operating">`
			if(data[i].checkStatus == 1) {
				html+= `	<button class="layui-btn layui-btn-normal operating-btn" id="${data[i].financeFlowId}">确认</button>
							<button class="layui-btn layui-btn-danger operating-btn" id="${data[i].financeFlowId}">拒绝</button>`
			} else {
				html+= `已审核`
			}
			html += `</td></tr>`;
		}
		
		$("#log_tb").html(html);
		initPage(res.totalCount, pageIndex);
	}
	
	// 初始化页面
	getFlowList(1);
	function getFlowList(pageIndex) {
		$.ajax({
			url: "/findflowlistbycontidion?pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&changeType=1" + "&payType=3",
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