
layui.use([ 'jquery', 'layer', 'tree' ], function() {
	var layer = layui.layer, $ = layui.jquery, title;
	var selectItem;
	/**
	 * 商品分类具体操作
	 * 
	 * @param item
	 * @param operFlag
	 * @returns
	 */
	function categoriesEditCommon(item, operFlag,firstIndex) {
		if((item.level==0&&operFlag==2)||(item.level==0&&operFlag==3)){
			$("#partsType-div").show();
		}else{
			$("#partsType-div").hide();
		}
		layer.open({
			type : 1,
			title : title,
			skin : 'layui-layer-lan', // 弹框主题
			shade : 0,
			area : '500px', // 宽高
			content : $('#categoriesInfoEdit'),
			btn : [ '提交', '取消' ],
			yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
				/**
				 * 1.添加子节点 2. 添加同级节点 3.修改 4.删除
				 */
				var typeName = $("#partsTypeName").val(); // 输入框的名称
				switch (operFlag) {
				case 1:
					addChildren(item.id, typeName, index);
					layer.close(firstIndex);
					break;
				case 2:
					addSameLevelNode(item.id, typeName, index);
					layer.close(firstIndex);
					break;
				case 3:
					// 修改节点类型
					updatePartType(item, index);
					layer.close(firstIndex);
					break;
				case 4:
					layer.close(firstIndex);
					break;
				default:
					layer.msg("default");
					break;
				}
			},
			end : function() { // 弹出框销毁时清空表单
				$("button").attr("disabled",false);
				$('#categoriesInfoEdit').css("display", "none");
				$('#categoriesDelete').css("display", "none");
				$('#partsTypeName').val("");
// inputReset(); // 清空表单
			}
		});
	}
	
	
	/**
	 * 弹框
	 * 
	 * @param obj
	 * @returns
	 */
	$('.parts_type').on('click', 'button', function() {
		var item ={};
		item.id = $(this).parent().parent().find(".childId").val();
		item.parentId =$(this).parent().parent().find(".parentId").val();
		item.name=$(this).parent().parent().find(":first").text();
		item.level=$(this).parent().parent().find(".level").val();
		$("button").attr("disabled",true);
		$("#categoriesName").text(item.name);
		if(item.level==1){
			layer.open({ 
				type : 1,
				title : '提示',
				skin : 'layui-layer-lan', 
				shade : 0,
				area : [ '300px', '260px' ], 
				content : $('#categoriesLocate'),
				btn : [ '添加同级节点', '修改','删除'],
				btnAlign : 'c', 
				yes : function(index, layero) { 
					title = '添加';
					appendimg();
					categoriesEditCommon(item, 2,index);
					return false;
				},
				btn2 : function(index, layero) { // 修改
					title = '编辑';
					// 清空
					$("#commodityImgUrl").attr("src","");
					
					// 修改前的显示
					viewPartType(item);
					// 修改
					categoriesEditCommon(item, 3,index);
					return false;
				},
				btn3 : function(firstIndex, layero) { // 选中删除的回调
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
							check(item, index,firstIndex);
						},
						btn2 : function(index, layero) {
							layer.close(index);
						},
						end : function() {
							$('#categoriesDelete').css("display", "none");
							$('#categoriesNotDel').css("display", "none");
						}
					});
					return false;
				},
				end : function() {
					$("button").attr("disabled",false);
					$('#categoriesLocate').css("display","none");
				}
			});
		}else{
			layer.open({
				type : 1,
				title : '提示',
				skin : 'layui-layer-lan', // 弹框主题
				shade : 0,
				area : [ '300px', '260px' ], // 宽高
				content : $('#categoriesLocate'),
				btn : [ '添加子节点', '添加同级节点', '修改', '删除' ],
				btnAlign : 'c', // 按钮居中
				yes : function(index, layero) { // 添加子节点
					title = '添加';
					appendimg();
					categoriesEditCommon(item, 1,index);
				},
				btn2 : function(index, layero) { // 添加同级节点
					title = '添加';
					appendimg();
					categoriesEditCommon(item, 2,index);
					return false;
				},
				btn3 : function(index, layero) { // 修改
					title = '编辑';
					viewPartType(item);
					categoriesEditCommon(item, 3,index);
					return false;
				},
				btn4 : function(firtsIndex, layero) { // 选中删除的回调
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
							check(item, index,firtsIndex);
						},
						btn2 : function(index, layero) {
							layer.close(index);
							
						},
						end : function() {
							$('#categoriesDelete').css("display", "none");
							$('#categoriesNotDel').css("display", "none");
						}
					});
					return false;
				},
				end : function() {
					$("button").attr("disabled",false);
					$('#categoriesLocate').css("display", "none");
				}
			});
		}
		
	 });
	
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
		var imgsrc = $("#commodityImgUrl").attr("src");
		if(imgsrc.length<0||imgsrc==undefined||imgsrc==''){
			layer.msg("请上传图片");
			return;
		}
		var data = {
			"partsTypeId" : selectNodeId,
			"typeName" : typeName,
			"partsType" : $("#partsType").val(),
			"icon":imgsrc
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
				
				//清空table
				initTable();
				
				//重载树
				initZtree();
				
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
				skin : 'layui-layer-lan', 
				shade : 0,
				area : '400px', // 宽高
				content : $('#categoriesAdd'),
				btn : '确定',
				yes : function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
					layer.close(index);
				},
				end : function() { 
					$('#categoriesAdd').css("display", "none");
					inputReset(); // 清空表单
				}
			});
			return;
		}
		var imgsrc = $("#commodityImgUrl").attr("src");
		if(imgsrc.length<0||imgsrc==undefined||imgsrc==''){
			layer.msg("请上传图片");
			return;
		}
		var data = {
			"partsTypeId" : selectNodeId,
			"typeName" : typeName,
			"partsType" : $("#partsType").val(),
			"icon":imgsrc
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
				
				//清空table
				initTable();
				
				//重载树
				initZtree();
				
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
	function del(selectItem, index,firstIndex) {
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
				
				//清空table
				initTable();
				
				//重载树
				initZtree();
				
				layer.close(index);
				layer.close(firstIndex);

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
	function check(selectItem, index,firtsIndex) {
		var selectId = selectItem.id;
		$.ajax({
			url : "/checkdel",
			type : "get",
			async : false,
			data : {
				"partTypeId" : selectId
			},
			success : function(data) {
				if (data.result.key == 1) {
					layer.msg("存在子集或者分类下面存在商品不可删除");
				} else {
					// 删除图片
					if(data.result.value!=null){
						delFile(data.result.value); 
					}
					del(selectItem, index,firtsIndex);
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
				$('#partsTypeName').val(item.name); // 配件类型名称
				$('#categoriesInfoEdit select').val(data.result.partsType);
				if(data.result.icon!=null){
					//填充
					$("#commodityImgUrl").attr("src",data.result.icon);
					//禁用上传按钮
					$(".commodityImg").attr("disabled", true);
				}
				
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
		var imgSrc = $("#commodityImgUrl").attr("src");
		if(imgSrc==undefined||imgSrc.length<0||imgSrc==""){
			layer.msg("请上传图片");
		}
		var data = {
			"partsTypeId" : selectNodeId,
			"typeName" : typeName,
			"partsType" : $("#partsType").val(),
			"icon":imgSrc
		};
		data = JSON.stringify(data);
		$.ajax({
			url : "/update",
			type : "post",
			async : false,
			contentType : "application/json; charset=utf-8",
			data : data,
			success : function(data) {
				
				//清空table
				initTable();
				
				//重载树
				initZtree();

				layer.msg("修改成功");
				layer.close(index);
 			},
			error : function(data) {
				layer.msg("修改失败");
			}
		});
	}
	
	
	function initTable(){
		//清空原来的
		$("#viewItemTree").html("");
		
		//清空table
		$(".parts_type").html("");
		
		//清空输入框
		$('#cName').val("");
		$('#cName').attr("placeholder","请选择分类");
	}
	
	/**
	 * zTree input
	 * 
	 * @param obj
	 * @returns
	 */

	var treeTemp;
	/**
	 * 配件树数据源
	 * 
	 * @returns
	 */
	function zNodes() {
		var node;
		$.ajax({
			url : "/findpartstype",
			type : "get",
			async : false,
			success : function(data) {
				node = data;
			},
			error : function(data) {
				layer.msg("页面查询失败");
			}
		});
		return node;
	}

	var zNodes = zNodes();

	// 配件树初始化
	$(document).ready(function() {
		$.fn.zTree.init($("#cTree"), setting, zNodes);
	});

	/**
	 * ztree 绑定到input元素中
	 */
	function beforeClick(treeId, treeNode) {
		selectItem = treeNode;
		showTreeNodeInfo(selectItem);
	}
	
	// 显示在table
	function showTreeNodeInfo(treeNode){
		var parent = treeNode.getParentNode();
		var pname = "无";
		var pid = -1;
		var child = treeNode.children;
		var parentTrs = "";
		var childTrs = "";
		if (treeNode != undefined) {
			if (parent != null && parent != undefined) {
				pname = treeNode.getParentNode().name;
				pid = treeNode.getParentNode().id;
			}

			parentTrs += '<tr>'
				+ '<td>'
				+ treeNode.name
				+ '<input type="hidden" value='+treeNode.id+' class="childId"/>'
				+ '<input type="hidden" value='+treeNode.level+' class="level"/>'
				+ '</td>'
				+ '<td>'
				+ pname
				+ '<input type="hidden" value='+pid+' class="parentId"/>'
				+ '</td>'
				+ '<td>'
				+ '<button id="edit" data-method="del" class="layui-btn layui-btn-normal edit_c">操作节点</button>'
				+ '</td></tr>';
			$(".parts_type").html(parentTrs);
		}

		// 子类
		if (child != null) {
			for (var i = 0; i < child.length; i++) {
				childTrs += '<tr>'
						+ '<td>'
						+ treeNode.children[i].name
						+ '<input type="hidden" value='+treeNode.children[i].id+' class="childId"/>'
						+ '<input type="hidden" value='+treeNode.children[i].level+' class="level"/>'
						+ '</td>'
						+ '<td>'
						+ treeNode.children[i].getParentNode().name
						+ '<input type="hidden" value='+treeNode.children[i].getParentNode().id+' class="parentId"/>'
						+ '</td>'
						+ '<td>'
						+ '<button id="edit" data-method="del" class="layui-btn layui-btn-normal edit_c">操作节点</button>'
						+ '</td></tr>';
			}
			$(".parts_type").append(childTrs);
		}
	}
	
	/**
	 * 
	 * @param e
	 *            当前元素
	 * @param treeId
	 *            id="cTree"
	 * @param treeNode
	 *            当前节点
	 * @returns
	 */
	function onClick(e, treeId, treeNode) {
		var display = $("#modelContent").css("display");
		if(display=="block"){
			$("#modelContent").hide();
		}
		$(e.target).parents('li').siblings('li').find('.curSelectedNode')
				.removeClass('curSelectedNode');
		// 保存id
		selectTreeId = treeNode.id;
		$("#cName").val(treeNode.name);

		
	}

	// 配件树设置
	var setting = {
		view : {
			dblClickExpand : false,
			autoCancelSelected : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick : beforeClick, // 检查
			onClick : onClick
		}
	};
	
	// 初始化树
	function initZtree(){
		$.ajax({
			url : "/findpartstype",
			type : "get",
			async : false,
			success : function(data) {
				node = data;
				$.fn.zTree.init($("#cTree"), setting, node);
			},
			error : function(data) {
				layer.msg("页面查询失败");
			}
		});
	}
	
});


// 配件树展示与收缩
function showMenu(obj) {
	$("#modelContent").css({
		left : "110px",
		top : "37px"
	}).slideToggle("fast");
}

/**
 * 图片上传
 * 
 * @param obj
 *            图片对象
 * @returns
 */
function change(obj) {
	 var form = $("#form");
	 var imgUrl = uploadImg(form, obj);
	 if (imgUrl != "") { // 如果上传文件成功
	        $(obj).parent().parent().find("img").attr("src", imgUrl);
	        // 禁用上传按钮
	        $(obj).attr("disabled", true);
	 }

}

/**
 * 删除图片
 * 
 * @param obj
 *            删除按钮
 * @returns
 */
function delImg(obj) {
    var url = $(obj).parent().find("img").attr("src");
    if(url!=undefined&&url.length>0&&url!=''&&url!=null){
    	delFile(url);
    	appendimg();
    	$(".commodityImg").attr("disabled",false);
    }else{
    	layer.msg("无可删除的图片",{time:500});
    }
    
    
    
    
}

function appendimg(){
	$(".siteUpload").html("");
	var img = `
		<img id="commodityImgUrl" src>
		<div class="layui-box layui-upload-button">
			<input type="file" name="file" class="commodityImg" class="layui-upload-file" onchange="change(this)" accept="image/*"> 
			<span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>上传图片</span>
		</div>
		<span class="closeBtn" onclick="delImg(this)"><i class="layui-icon">&#x1006;</i></span>`;
    $(".siteUpload").html(img);
}


