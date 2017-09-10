layui.use([ 'jquery', 'layer', 'tree' ], function() {
	var layer = layui.layer, $ = layui.jquery, title;

	// 弹框：以何种方式添加商品分类及删除提示
	function categoriesEdit(item) {
		$('#categoriesName').text(item.name); // 提示当前选中分类
		layer.open({ // 弹框询问门店添加位置
			type : 1,
			title : '提示',
			skin : 'layui-layer-lan', // 弹框主题
			shade : 0,
			area : [ '300px', '260px' ], // 宽高
			content : $('#categoriesLocate'),
			btn : [ '添加子节点', '添加同级节点', '修改', '删除' ],
			btnAlign : 'c', // 按钮居中
			yes : function(index, layero) { // 选中上方添加节点的回调
				title = '添加';
				categoriesEditCommon(item, 1);
				layer.close(index);
			},
			btn2 : function(index, layero) { // 选中添加同级节点的回调
				title = '添加';
				layer.close(index);
				categoriesEditCommon(item, 2);
			},
			btn3 : function(index, layero) { // 选中修改
				title = '编辑';
				// 点击修改按钮后，展示当前节点信息：名称、类型
				// $('#categoriesInfoEdit input').val('123');
				// $('#categoriesInfoEdit select').val('2');
				// $('#categoriesInfoEdit input').attr('disabled', 'disabled');
				// layer.close(index);
				// 展示树形结构数据
				var s = $(item).parent().parent().children("ul").html();
				viewPartType(item);
				categoriesEditCommon(item, 3);
			},
			btn4 : function(index, layero) { // 选中删除的回调
				layer.close(index);
				var temp = 'del';
				/*
				 * 删除操作。。。
				 * 
				 * 是否可以删除？ 是 temp='del'，为测试效果默认可以删除 否 temp=''
				 */
				layer.open({
					type : 1,
					title : temp == 'del' ? '删除' : '提示',
					skin : 'layui-layer-lan', // 弹框主题
					shade : 0,
					area : '400px', // 宽高
					content : temp === 'del' ? $('#categoriesDelete')
							: $('#categoriesNotDel'),
					btn : temp === 'del' ? [ '确定', '取消' ] : '确定',
					btnAlign : 'c', // 按钮居中
					yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
						// console.log(layero);
						check(item, index);
					},
					btn2 : function(index, layero) {
						// console.log(index);
						layer.close(index);
					}
				});
			},
			end : function() {
				$('#categoriesLocate').css("display", "none");
			}
		});
	}
	// 商品分类详细信息
	function categoriesEditCommon(item, operFlag) {
		layer.open({
			type : 1,
			title : title,
			skin : 'layui-layer-lan', // 弹框主题
			shade : 0,
			area : '500px', // 宽高
			content : $('#categoriesInfoEdit'),
			btn : [ '提交', '取消' ],
			yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
				// layer.close(index); //如果设定了yes回调，需进行手工关闭
				/**
				 * 1.添加子节点 2. 添加同级节点 3.修改 4.删除
				 */
				var typeName = $("#partsTypeName").val(); // 输入框的名称
				switch (operFlag) {
				case 1:
					addChildren(item.id, typeName, index);
					break;
				case 2:
					addSameLevelNode(item.id, typeName, index);
					break;
				case 3:
					// 修改节点类型
					updatePartType(item, index);
					break;
				default:
					layer.msg("default");
					break;
				}
			},
			end : function() { // 弹出框销毁时清空表单
				$('#categoriesInfoEdit').css("display", "none");
				inputReset(); // 清空表单
			}
		});
	}
	// 重置表单输入框
	function inputReset() {
		$('#categoriesInfoEdit>form')[0].reset();
		$('#categoriesInfoEdit input').removeAttr('disabled');
	}

	/**
	 * 节点树
	 */
	loadAddNodes(0, 3);

	/**
	 * 加载展示
	 * 
	 * @param level
	 *            1
	 * @param flag
	 *            1:新增 2:编辑 3.查看
	 * @returns
	 */
	function loadAddNodes(level, flag) {
		$.ajax({
			url : "/partstreeofadded", // 这个是新增显示的数据
			type : "get",
			async : false,
			data : {
				"pid" : level,
				"operflag" : 3
			},
			success : function(res) {
				viewTree(res); // 显示新增数据
			},
			error : function(res) {
				layer.msg('查询树形菜单失败');
			}
		});
	}

	/**
	 * 查看树
	 * 
	 * @param res
	 * @returns
	 */
	function viewTree(data) {
		layui.tree({
			elem : '#viewItemTree', // '#storeTree',//;'#categoriesTreeBox':新增,
									// categoriesTreeView：编辑 //指定元素
			click : function(item) { // 点击节点回调
				categoriesEdit(item);
			},
			nodes : data
		});
	}

	/**
	 * 添加子节点
	 * 
	 * @param selectNodeId
	 *            当前选中节点
	 * @param typeName
	 *            类型名称
	 * @param index
	 *            弹出层
	 * @returns
	 */

	function addChildren(selectNodeId, typeName, layerIndex) {
		/**
		 * 检查新增的名称
		 */
		var result = addCheck(typeName, layerIndex);
		if (result == true) {
			layer.open({
				type : 1,
				title : '提示',
				skin : 'layui-layer-lan', // 弹框主题
				shade : 0,
				area : '400px', // 宽高
				content : $('#categoriesAdd'),
				btn : '确定',
				yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
					layer.close(index);
				},
				end : function() { // 弹出框销毁时清空表单
					$('#categoriesAdd').css("display", "none");
					inputReset(); // 清空表单
				}
			});
			return;
		}

		var data = {
			"partsTypeId" : selectNodeId,
			"typeName" : typeName,
			"partsType" : $("#partsType").val()
		};
		data = JSON.stringify(data);
		$.ajax({
			url : "/addpartstype",
			type : "post",
			async : false,
			contentType : "application/json; charset=utf-8",
			data : data,
			success : function(data) {
				layer.msg("子节点添加成功");
				$("#viewItemTree").html("");
				loadAddNodes(0, 3);
				// 关闭弹框
				layer.close(layerIndex);
			},
			error : function(data) {
				layer.msg("子节点添加失败");
			}
		});
	}

	/**
	 * 添加相同节点
	 * 
	 * @returns
	 */
	function addSameLevelNode(selectNodeId, typeName, layerIndex) {
		/**
		 * 检查新增的名称
		 */
		var result = addCheck(typeName, layerIndex);
		if (result == true) {
			layer.open({
				type : 1,
				title : '提示',
				skin : 'layui-layer-lan', // 弹框主题
				shade : 0,
				area : '400px', // 宽高
				content : $('#categoriesAdd'),
				btn : '确定',
				yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
					layer.close(index);
				},
				end : function() { // 弹出框销毁时清空表单
					$('#categoriesAdd').css("display", "none");
					inputReset(); // 清空表单
				}
			});
			return;
		}

		var data = {
			"partsTypeId" : selectNodeId,
			"typeName" : typeName,
			"partsType" : $("#partsType").val()
		};
		data = JSON.stringify(data);
		$.ajax({
			url : "/addsamelevelpartstype",
			type : "post",
			async : false,
			contentType : "application/json; charset=utf-8",
			data : data,
			success : function(data) {
				layer.msg("添加同级节点成功");
				$("#viewItemTree").html("");
				loadAddNodes(0, 3);
				// 关闭弹框
				layer.close(layerIndex);
			},
			error : function(data) {
				layer.msg("添加同级节点失败");
			}
		});
	}

	// 新增检查名称是否唯一
	function addCheck(typeName, index) {
		var result;
		$.ajax({
			url : "/checkadd",
			type : "get",
			async : false,
			data : {
				"typeName" : typeName
			},
			success : function(data) {
				result = data.result
			},
			error : function(data) {
				layer.msg("检查节点失败");
			}
		});
		return result;
	}

	/**
	 * 删除节点
	 * 
	 * @returns
	 */
	function del(selectItem, index) {
		var selectNodeId = selectItem.id;
		var data = {
			"partsTypeId" : selectNodeId
		};
		data = JSON.stringify(data);
		$.ajax({
			url : "/deletepartstype/?partTypeId=" + selectNodeId,
			type : "DELETE",
			async : false,
			success : function(data) {
				layer.msg("删除" + selectItem.name + "成功");
				$("#viewItemTree").html("");
				loadAddNodes(0, 3);
				layer.close(index);
			},
			error : function(data) {
				layer.msg("删除失败");
			}
		});
	}

	/**
	 * 删除检查
	 * 
	 * @param selectNodeId
	 *            选中id
	 * @param index
	 *            弹出框索引
	 * @param addAndEditAndViewFlag
	 *            新增删除操作标记
	 * @param level
	 *            编辑的时候,查找等级
	 * @returns
	 */
	function check(selectItem, index) {
		var selectId = selectItem.id;
		$.ajax({
			url : "/checkdel",
			type : "get",
			async : false,
			data : {
				"partTypeId" : selectId
			},
			success : function(data) {
				if (data.result == true) {
					layer.msg("存在子集不可删除");
				} else {
					del(selectItem, index);
				}
			},
			error : function(data) {
				layer.msg("检查节点失败");
			}
		});
	}

	/**
	 * 修改前得查看 修改配件类型 1.易损件 2. 车型件
	 * 
	 * @returns
	 */
	function viewPartType(item) {
		var selectNodeId = item.id;
		$.ajax({
			url : "/findparttypeobj",
			type : "get",
			async : false,
			data : {
				"partTypeId" : selectNodeId
			},
			success : function(data) {
				$('#categoriesInfoEdit input').val(item.name); // 配件类型名称
				// $('#categoriesInfoEdit input').attr('disabled', 'disabled');
				$('#categoriesInfoEdit select').val(data.result.partsType);
				$('#categoriesInfoEdit select').attr('disabled', 'disabled');
			},
			error : function(data) {
				layer.msg("检查节点失败");
			}
		});
	}

	/**
	 * 修改配件名称
	 * 
	 * @returns
	 */
	function updatePartType(item, index) {
		var selectNodeId = item.id;
		var typeName = $("#partsTypeName").val();
		if (typeName == null || typeName == "") {
			layer.msg("修改的名称不能为空");
			return;
		}
		var data = {
			"partsTypeId" : selectNodeId,
			"typeName" : typeName
		};
		data = JSON.stringify(data);
		$.ajax({
			url : "/update",
			type : "post",
			async : false,
			contentType : "application/json; charset=utf-8",
			data : data,
			success : function(data) {
				layer.msg("修改成功");
				$("#viewItemTree").html("");
				loadAddNodes(0, 3);
				// 关闭弹框
				layer.close(index);
			},
			error : function(data) {
				layer.msg("修改失败");
			}
		});
	}

	/**
	 * 除了一级节点外,其他的配件类型都隐藏
	 * 
	 * @returns
	 */
	function forbiddenPartsType() {
		$("#viewItemTree li:first ul:first li").each(function() {
			alert($(this).html());
		});
	}

});
