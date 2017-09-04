layui.use(['jquery', 'layer','tree'], function() {
var layer = layui.layer, $ = layui.jquery, title;

	// 弹框：以何种方式添加商品分类及删除提示
	function categoriesEdit(item) {
		$('#categoriesName').text(item.name); // 提示当前选中分类
		layer.open({ // 弹框询问门店添加位置
			type : 1,
			title : '提示',
			skin : 'layui-layer-lan', //弹框主题
			shade : 0,
			area : [ '300px', '260px' ], //宽高
			content : $('#categoriesLocate'),
			btn : [ '添加子节点', '添加同级节点', '修改', '删除' ],
			btnAlign : 'c', //按钮居中
			yes : function(index, layero) { // 选中上方添加节点的回调
				title = '添加';
				categoriesEditCommon(item);
				layer.close(index);
			},
			btn2 : function(index, layero) { // 选中添加同级节点的回调
				title = '添加';
				layer.close(index);
				categoriesEditCommon();
			},
			btn3 : function(index, layero) { // 选中修改的回调
				title = '编辑';
				// 点击修改按钮后，展示当前节点信息：名称、类型
				$('#categoriesInfoEdit input').val('123');
				$('#categoriesInfoEdit select').val('2');
				$('#categoriesInfoEdit input').attr('disabled', 'disabled');
				layer.close(index);
				categoriesEditCommon();
			},
			btn4 : function(index, layero) { // 选中删除的回调
				layer.close(index);
				var temp = 'del';
				/*
				    删除操作。。。
				    
				    是否可以删除？
				    是   temp='del'，为测试效果默认可以删除
				    否   temp=''                    
				 */
				layer.open({
					type : 1,
					title : temp == 'del' ? '删除' : '提示',
					skin : 'layui-layer-lan', //弹框主题
					shade : 0,
					area : '400px', //宽高
					content : temp === 'del' ? $('#categoriesDelete')
							: $('#categoriesNotDel'),
					btn : temp === 'del' ? [ '确定', '取消' ] : '确定',
					btnAlign : 'c', //按钮居中
					yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
						// console.log(layero);
						layer.close(index); //如果设定了yes回调，需进行手工关闭
					},
					btn2 : function(index, layero) {
						// console.log(index);
						layer.close(index);
					}
				});
			}
		});
	}
	// 商品分类详细信息
	function categoriesEditCommon() {
		layer.open({
			type : 1,
			title : title,
			skin : 'layui-layer-lan', //弹框主题
			shade : 0,
			area : '500px', //宽高
			content : $('#categoriesInfoEdit'),
			btn : [ '提交', '取消' ],
			yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
				$('#storeTree').html('');
				// layer.close(index); //如果设定了yes回调，需进行手工关闭

				if (true) { // 在新增的情况下，如果要添加的的内容已存在，则不可添加且提示用户该分类已存在
					layer.open({
						type : 1,
						title : '提示',
						skin : 'layui-layer-lan', //弹框主题
						shade : 0,
						area : '400px', //宽高
						content : $('#categoriesAdd'),
						btn : '确定',
						yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
							layer.close(index);
						},
						end : function() { // 弹出框销毁时清空表单
							inputReset(); // 清空表单
						}
					});
				}
			},
			end : function() { // 弹出框销毁时清空表单
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
	loadAddNodes(0,3);

	/**
     * 加载展示
     * @param level 1
     * @param flag 1:新增  2:编辑 3.查看
     * @returns
     */
    function loadAddNodes(level, flag) {
        $.ajax({
            url: "/partstreeofadded", //这个是新增显示的数据
            type: "get",
            async: false,
            data: { "pid": level, "operflag": 3 },
            success: function(res) {
            	viewTree(res); //显示新增数据		
            },
            error: function(res) {
                alert('查询树形菜单失败');
            }
        });
    }
	
    /**
     * 查看树
     * @param res
     * @returns
     */
	function viewTree(data){
		 layui.tree({
	            elem: '#viewItemTree', //'#storeTree',//;'#categoriesTreeBox':新增, categoriesTreeView：编辑 //指定元素
	            click: function(item) { //点击节点回调
	                categoriesEdit(item);
	            },
	            nodes: data
	        });
	}
	
	/**
	 * 添加子节点
	 * @param selectNodeId 当前选中节点
	 * @param typeName 类型名称
	 * @param index 弹出层
	 * @returns
	 */

	function addChildren(selectNodeId, typeName, index, addAndEditAndViewFlag) {
		//把他的id当做 pid
		//新增一个配件信息	
		/**
		 * 加载节点树
		 * @param level 1
		 * @param flag 1:新增  2:编辑 3.查看
		 * @returns
		 */
		//function loadNodes(level,flag)
		var data = {
			"partsTypeId" : selectNodeId,
			"typeName" : typeName
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
				//layer.close(index);
				//loadNodes(0,0);
				if (addAndEditAndViewFlag == 1) { //1 新增
					$('#addItemTree').html("");
					loadNodes(0, 1);
				} else if (addAndEditAndViewFlag == 2) { //2 编辑
					$('#editItemTree').html("");
					loadNodes(selectNodeId, 2); //根据选中的节点重新找到父级,重新加载
				}

			},
			error : function(data) {
				layer.msg("子节点添加失败");
			}
		});
	}

	/**
	 * 添加相同节点
	 * @returns
	 */
	function addSameLevelNode(selectNodeId, typeName, index,addAndEditAndViewFlag) {
		var data = {
			"partsTypeId" : selectNodeId,
			"typeName" : typeName
		};
		data = JSON.stringify(data);
		$.ajax({
			url : "/addsamelevelpartstype",
			type : "post",
			async : false,
			contentType : "application/json; charset=utf-8",
			data : data,
			success : function(data) {
				layer.msg("添加同级几点成功");
				if (addAndEditAndViewFlag == 1) { //1 新增
					$('#addItemTree').html("");
					loadNodes(0, 1);
				} else if (addAndEditAndViewFlag == 2) { //2 编辑
					$('#editItemTree').html("");
					loadNodes(selectNodeId, 2); //根据选中的节点重新找到父级,重新加载
				}
				//layer.close(index);
			},
			error : function(data) {
				layer.msg("添加同级几点失败");
			}
		});
	}

	/**
	 * 删除节点
	 * @returns
	 */
	function del(selectNodeId, addAndEditAndViewFlag, level) {
		//alert("addAndEditAndViewFlag:" + addAndEditAndViewFlag);
		var data = {
			"partsTypeId" : selectNodeId
		};
		data = JSON.stringify(data);
		$.ajax({
			url : "/deletepartstype/?partTypeId=" + selectNodeId,
			type : "DELETE",
			async : false,
			//contentType: "application/json; charset=utf-8",
			//data : data,
			success : function(data) {
				layer.msg("删除成功" + addAndEditAndViewFlag);
				if (addAndEditAndViewFlag == 1) { //1 新增
					$('#addItemTree').html("");
					loadNodes(0, 1);
				} else if (addAndEditAndViewFlag == 2) { //2 编辑
					$('#editItemTree').html("");
					loadNodes(level, 2); //根据选中的节点重新找到父级,重新加载
				}
				//layer.close(index);
			},
			error : function(data) {
				layer.msg("删除失败");
			}
		});
	}

	/**
	 * 删除检查
	 * @param selectNodeId 选中id
	 * @param index 弹出框索引
	 * @param addAndEditAndViewFlag 新增删除操作标记
	 * @param level 编辑的时候,查找等级
	 * @returns
	 */
	function check(selectNodeId, index, addAndEditAndViewFlag, level) {
		//    	var data = {"partsTypeId":selectNodeId};
		//    	data = JSON.stringify(data);
		$.ajax({
			url : "/checkdel",
			type : "get",
			async : false,
			//contentType: "application/json; charset=utf-8",
			data : {
				"partTypeId" : selectNodeId
			},
			success : function(data) {
				if (data.result == true) {
					layer.msg("存在子集不可删除");
					//					layer.close(index);
				} else {
					del(selectNodeId, addAndEditAndViewFlag, level);
				}
			},
			error : function(data) {
				layer.msg("检查节点失败");
			}
		});
	}
	
	
});
	