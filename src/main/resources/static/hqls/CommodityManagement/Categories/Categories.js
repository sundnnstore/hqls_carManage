layui.use(['layer', 'tree', 'form', 'laypage'], function() {
    var layer = layui.layer,
        form = layui.form(),
        laypage = layui.laypage,
        $ = layui.jquery,
        pageSize=10,
        first_title,
        second_title = '';

    // 商品分类节点弹框触发事件
    $('.categories').on('click', 'button', function() {
        var othis = $(this),
            method = othis.data('method');
        var level =  othis.parent().parent().find("td:first").find("input").val();
        first_title = second_title = '';
        if (method == 'addCategories') {
            first_title = '新增';
            second_title = method;
            /**
             * 1 是节点等级
             * 1 是新增  2 编辑的
             */
            loadNodes(0,1);
            buttongForbidden();
        } else if (method == 'view') {
            first_title = '查看';
            loadNodes(level,3);
            buttongForbidden();
        } else if (method == 'edit') {
            first_title = '编辑';
            /**
             * 第一个  2 等级
             * 第二个  2 操作方式
             */
            loadNodes(level,2);//加载节点树
            buttongForbidden();
        }
        first_title&&layer.open({
	      type: 1,
	      title: first_title, // 标题
	      skin: 'layui-layer-lan', //弹框主题
	      shade: 0,
	      area: '450px', // 宽高
	      content:method=='addCategories'?$('#addTree'):method=='view'?$('#viewTree'):$('#editTree'), //弹框内容
	      btn: method == 'addCategories' ? null : '确定',
	      yes:function(index, layero) {
	    	  layer.close(index);
	      },
	      end:function(index, layero){
	    	  $('#viewItemTree').html("");
	    	  $('#editItemTree').html("");
	    	  $('#addItemTree').html("");
	    	  clearButtonForbidden();
	    	  //强制隐藏
	    	  layer.close(index);
	      }
      });

    });

    /**
     * 加载节点树
     * @param level 1
     * @param flag 1:新增  2:编辑 3.查看
     * @returns
     */
    function loadNodes(level,flag){
    	$.ajax({
			url : "/findpartstree",
			type : "get",
			async : false,
			data : {"pid":level,"operflag":flag},
			success : function(res){
				switch (flag) {
					case 1:
						addTree(res);
						break;
					case 2:
						editTree(res);
						break;
					case 3:
						view(res);
						//查看
						break;
				default:
					break;
				}						
			},
			error :function(res){
				alert('查询树形菜单失败');
			}
		});
    }
    
    
    /**
     * 显示页面
     * @param data
     * @returns
     */
    function addTree(data){
    	 // 构建商品分类节点树
        layui.tree({
            elem:'#addItemTree', //'#storeTree',//;'#categoriesTreeBox':新增, categoriesTreeView：编辑 //指定元素
            click: function(item) { //点击节点回调
                categoriesEdit(item);
            },
            nodes: data
        });
    }
    function editTree(data){
    	//alert("编辑操作");
    	 console.log(data);
    	 layui.tree({
             elem: '#editItemTree',//;'#categoriesTreeBox':新增, categoriesTreeView：编辑 //指定元素
             click: function(item) { //点击节点回调
                 categoriesEdit(item);
             },
             nodes: data
         });
    }
    
    function view(data){
      layui.tree({
      elem: '#viewItemTree', //指定元素
      click: function(item) {
          categoriesEdit(item);
      },
      nodes: data
      });
    }

   
    var createTree = function(node, start) {
        node = node || function() {
            var arr = [];
            arr.push({
                name: '1'.toString().replace(/(\d)/, '$1$1$1$1$1$1$1$1$1'),
                spread: true,
            });
            return arr;
        }();
        start = start || 1;
        layui.each(node, function(index, item) {
            if (start < 10 && index < 9) {
                var child = [{
                    name: (1 + index + start).toString().replace(/(\d)/, '$1$1$1$1$1$1$1$1$1')
                }];
                node[index].children = child;
                node[index].spread = true;
                createTree(child, index + start + 1);
            }
        });
        return node;
    };
    
    // 弹框：以何种方式添加商品分类及删除提示
    function categoriesEdit(item) {
        $('#categoriesName').text(item.name);
        layer.open({ // 弹框询问门店添加位置
            type: 1,
            title: '提示',
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: ['300px', '260px'], //宽高
            content: $('#categoriesLocate'),
            btn: ['添加子节点', '添加同级节点','删除'],
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) { // 添加子节点
            	console.log("index:",index,"\n当前节点id",item.id,"\n当前节点name",item.name);
            	categoriesEditCommon(item.id,1);
                layer.close(index);
            },
            btn2: function(index, layero) { //添加同级节点
            	categoriesEditCommon(item.id,2); 
                layer.close(index);
            },
            btn3: function(index, layero) { // 选中删除的回调
            	//categoriesEditCommon(item.id,3); 
                layer.close(index);
                var temp = 'del';
                /*
                    删除操作。。。
                    
                    是否可以删除？
                    是   temp='del'，为测试效果默认可以删除
                    否   temp=''                    
                */
                layer.open({
                    type: 1,
                    title: temp == 'del' ? '删除' : '提示',
                    skin: 'layui-layer-lan', //弹框主题
                    shade: 0,
                    area: '400px', //宽高
                    content: temp === 'del' ? $('#categoriesDelete') : $('#categoriesNotDel'),
                    btn: temp === 'del' ? ['确定', '取消'] : '确定',
                    btnAlign: 'c', //按钮居中
                    yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                        // console.log(layero);
                    	check(item.id);
                        layer.close(index); //如果设定了yes回调，需进行手工关闭
                    },
                    btn2: function(index, layero) {
                        // console.log(index);
                        layer.close(index);
                    }
                });
            },
        });
    }
    
    /**
     * 
     * @param selectNodeId 当前选中节点
     * @param typeName 新增类型名称
     * @param operFlag 操作标志  1 增加子节点 2.增加同节点 3. 删除
     * @returns
     */
    function categoriesEditCommon(selectNodeId,operFlag) {
        layer.open({
            type: 1,
            title: second_title == 'edit' ? '编辑' : '新增',
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '500px', //宽高
            content: second_title == 'view' ? $('#storeInfoView') : $('#categoriesInfoEdit'),
            btn: second_title == 'view' ? '确定' : '提交',
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
            	var typeName = $("#partsTypeName").val(); //输入框的名称
            	switch (operFlag){
	            	case 1: 
	            		addChildren(selectNodeId,typeName);
	            		break;
	            	case 2:
	            		addSameLevelNode(selectNodeId,typeName);
	            		break;
	            	case 3:
	            		check(selectNodeId); //检查删除
	            		break;
            	}
            	
            	
            	layer.close(index); //如果设定了yes回调，需进行手工关闭
                inputReset(); // 清空表单
                // 在新增的情况下，如果要添加的的内容已存在，则提示用户已存在
//                if (second_title != 'view' && false) { // 第一参数表明是否新增，第二参数表明是否已存在
//                    layer.open({
//                        type: 1,
//                        title: '提示',
//                        skin: 'layui-layer-lan', //弹框主题
//                        shade: 0,
//                        area: '400px', //宽高
//                        content: $('#categoriesAdd'),
//                        btn: '确定',
//                        btnAlign: 'c', //按钮居中
//                        yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
//                            layer.close(index);
//                        }
//                    });
//                }
                
                
            }
        });
    }
    // 重置表单输入框
    function inputReset() {
        $('#categoriesInfoEdit>form')[0].reset();
    }

    // 分页条
    /**
     * 页面加载默认显示
     */
    getdata(1,0);
	$("#searchCategories").click(function(){
		var selectLevel=0;
		//获取等级数据，然后判断
    	var onelevelSelectVal = $("#oneleveltype").val();
    	var twolevelSelectVal = $("#twoleveltype").val();
    	var threelevelSelectVal=$("#threeleveltype").val();
    	if(onelevelSelectVal!=-1){
    		selectLevel = onelevelSelectVal;
    	}else if(twolevelSelectVal!=-1){
    		selectLevel=twolevelSelectVal;
    	}else if(threelevelSelectVal!=-1){
    		selectLevel=threelevelSelectVal;
    	}else{
    		selectLevel = 0;
    	}
    	//alert(oneleveSelectVal);
		getdata(1,selectLevel);
	});
	
    /**
     * 初始化分页 
     * @param totalCount 总页数
     * @param pageIndex  页面索引
     * @returns
     */
    function initPage(totalCount,pageIndex){
    	if(totalCount<=pageSize) totalCount=pageSize+1;
    	//page
		laypage({
			cont: 'pages',
			pages:Math.ceil(totalCount/pageSize), // 总的分页数
			groups: 5, // 展示的页数
			first: 1,
			last: Math.ceil(totalCount/pageSize),
			skip: true,
			curr:pageIndex,
			jump: function(obj, first) {
				//alert("初始化成功"+obj.curr);
				if(!first) {
					getdata(obj.curr);
				}
			}
		});
		
	}
    
    /**
     * 获取数据
     * @param pageIndex
     * @returns
     */
    function getdata(pageIndex,selectLevel){
    	if(selectLevel==undefined) selectLevel=0;
    	$.ajax({
			url : "/levelinfo",
			type : "get",
			async : false,
			data : {"partsTypeId":selectLevel,"selecDepth":6,"pageSize":pageSize,"pageIndex":pageIndex},
			success : function(data){
				comboTable(data,pageIndex);
			},
			error:function(data){
				alert("页面查询失败");
			}
		});
    	
	}
    
    /**
     * 
     * @param res 返回的数据 
     * @param pageIndex 显示的页面索引
     * @returns
     */
    function comboTable(res,pageIndex){
		/**
		 * 显示一二三等级
		 */
    	partsLevel();

    	/**
    	 * 显示表单数据
    	 */
    	displayPartsTypeInfo(res);
    	
		/**
		 * 初始化分页
		 */
		initPage(res.totalCount,pageIndex); 
    }
    
    /**
     * 获取等级数据源
     * @returns
     */
    function partsLevel(){
    	$.ajax({
			url : "/findpartslevel",
			type : "get",
			async : false,
			data : {"pid":0,"operflag":1},
			success : function(res){
				displayLevel(res);
			}
		});
    }
    /**
     * 显示等级
     * @param res
     * @returns
     */
    function displayLevel(res){
    	var onlevel=`<option value="-1">全部</option>`,twolevel=`<option value="-1">全部</option>`,threelevel=`<option value="-1">全部</option>`;
		var trs=``,one=``,two=``,three=``;
		//循环菜单树
		if(res.children!=null&&res.children!=undefined){
			var oneChildren = res.children;
			for (var i = 0; i < oneChildren.length; i++) { //一级菜单
				onlevel+=`<option value="${oneChildren[i].id}">${oneChildren[i].name}</option>`;
					var twoChildren = res.children[i].children;
				if(twoChildren!=null&&twoChildren!=undefined){
					for (var j = 0; j < res.children[i].children.length; j++) { //二级菜单
						twolevel+=`<option value="${twoChildren[j].id}">${twoChildren[j].name}</option>`;
						var threeChildren = res.children[i].children[j].children;
						if(threeChildren!=null&&threeChildren!=undefined){
							for (var q = 0; q <threeChildren.length; q++) {//三级菜单
								threelevel+=`<option value="${threeChildren[q].id}">${threeChildren[q].name}</option>`;
								continue;
							}
							//第三级拼接
							$("#threeleveltype").html(threelevel);
						}
						continue;
					}
					//第二级别的拼接
					$("#twoleveltype").html(twolevel);
				}
				continue;
				 
			}
			$("#oneleveltype").html(onlevel);
		}
    }
    
    /**
     * 组装显示等级的数据
     * @param res
     * @returns
     */
    function displayPartsTypeInfo(res){
    	var tr;
    	if(res!=null){
    		var len = res.result.length;
    		for (var i = 0; i < len; i++) { 
    			var levelInfo = res.result[i].length;
    			tr += `<tr>`;
				for (var j = 0; j < levelInfo; j++) {
					tr+=` <td>${res.result[i][j].name}<input type="hidden" value=${res.result[i][j].id}></td>`;
				}
				tr+=`<td class="operation">
		                  <button id="view" data-method="view" class="layui-btn">查看</button>
		                  <button id="edit" data-method="edit" class="layui-btn layui-btn-normal">编辑</button>
		       </td></tr>`;
			$("#partsTypeInfo").html(tr);
    		}
    		
    	}
    	
    }
    
    /**
     * 添加子节点
     * @param selectNodeId 当前选中节点
     * @param typeName 类型名称
     * @param index 弹出层
     * @returns
     */
    
    function addChildren(selectNodeId,typeName,index){
    	//把他的id当做 pid
    	//新增一个配件信息	
    	/**
         * 加载节点树
         * @param level 1
         * @param flag 1:新增  2:编辑 3.查看
         * @returns
         */
        //function loadNodes(level,flag)
    	var data = {"partsTypeId":selectNodeId,"typeName":typeName};
    	data = JSON.stringify(data);
    	$.ajax({
			url : "/addpartstype",
			type : "post",
			async : false,
			contentType: "application/json; charset=utf-8",
			data : data,
			success : function(data){
				layer.msg("子节点添加成功");
				//layer.close(index);
				//loadNodes(0,0);
				loadNodes(selectNodeId,1)
			},
			error:function(data){
				layer.msg("子节点添加失败");
			}
		});
    } 
    
    /**
     * 添加相同节点
     * @returns
     */
    function addSameLevelNode(selectNodeId,typeName){
    	var data = {"partsTypeId":selectNodeId,"typeName":typeName};
    	data = JSON.stringify(data);
    	$.ajax({
			url : "/addsamelevelpartstype",
			type : "post",
			async : false,
			contentType: "application/json; charset=utf-8",
			data : data,
			success : function(data){
				layer.msg("添加同级几点成功");
				//layer.close(index);
				//loadNodes(0,0);
			},
			error:function(data){
				layer.msg("添加同级几点失败");
			}
		});
    }
    
    /**
     * 删除节点
     * @returns
     */
    function del(selectNodeId){
    	var data= {"partsTypeId":selectNodeId};
    	data = JSON.stringify(data);
    	//alert("删除里面的:"+selectNodeId);
    	$.ajax({
			url : "/deletepartstype/?partTypeId="+selectNodeId,
			type : "DELETE",
			async : false,
			//contentType: "application/json; charset=utf-8",
			//data : data,
			success : function(data){
				layer.msg("删除成功");
				layer.close(index);
				
				//loadNodes(0,0);
			},
			error:function(data){
				layer.msg("删除失败");
			}
		});
    }
    
    /**
     * 删除检查
     * @returns
     */
    function check(selectNodeId){
//    	var data = {"partsTypeId":selectNodeId};
//    	data = JSON.stringify(data);
    	$.ajax({
			url : "/checkdel",
			type : "get",
			async : false,
			//contentType: "application/json; charset=utf-8",
			data :  {"partTypeId":selectNodeId},
			success : function(data){
				if(data.result==true){
					layer.msg("存在子集不可删除");
					layer.close(index);
				}else{
					del(selectNodeId);
				}
			},
			error:function(data){
				layer.msg("检查节点失败");
			}
		});
    }
    
    //按钮禁用
   function buttongForbidden(){
	   $("#view").attr("disabled","disabled");
	   $("#edit").attr("disabled","disabled");
	   $("#addCategories").attr("disabled","disabled");
	   $("#searchCategories").attr("disabled","disabled");
   }
   
   //按钮启用
   function clearButtonForbidden(){
	   $("#view").removeAttr("disabled");
	   $("#edit").removeAttr("disabled");
	   $("#addCategories").removeAttr("disabled");
	   $("#searchCategories").removeAttr("disabled");
   }
});