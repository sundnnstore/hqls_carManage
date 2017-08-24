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
        first_title = second_title = '';
        if (method == 'addCategories') {
            first_title = '新增';
            second_title = method;
            $('#storeTree').html('');
            /**
             * 1 是节点等级
             * 0 是新增  1 编辑的
             */
            loadNodes(1,0);//加载节点树
        } else if (method == 'view') {
        	$('#storeItemTree').html('');
            first_title = '查看';
        } else if (method == 'edit') {
        	$('#storeItemTree').html('');
            first_title = '编辑';
            //传入一级菜单id
            $(this).parent().parent().val();//节点树等级
            loadNodes(1,1);//加载节点树
        }
        first_title && layer.open({
            type: 1,
            title: first_title, // 标题
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '450px', // 宽高
            content: method == 'addCategories' ? $('#categoriesTreeBox') : $('#categoriesTreeView'), // 内容
            btn: method == 'addCategories' ? null : '确定'
        });
    });

    /**
     * 
     * @param level
     * @param flag 0:新增  1:编辑
     * @returns
     */
    function loadNodes(level,flag){
    	$.ajax({
			url : "/findpartstree",
			type : "get",
			async : false,
			data : {"pid":level},
			success : function(res){
				//data=JSON.stringify(res);
//				res=JSON.parse(res);
//				alert("操作标记:"+flag);
				
				if(!flag){
					addTree(res);
				}else{
					editTree(res);
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
    	console.log(data);
    	 // 构建商品分类节点树
        layui.tree({
            elem: '#storeTree',//;'#categoriesTreeBox':新增, categoriesTreeView：编辑 //指定元素
            click: function(item) { //点击节点回调
                first_title != '查看' && categoriesEdit(item);
            },
            nodes: data
        });
    }
    function editTree(data){
    	console.log(data);
    	 layui.tree({
             elem: '#storeItemTree',//;'#categoriesTreeBox':新增, categoriesTreeView：编辑 //指定元素
             click: function(item) { //点击节点回调
                 first_title != '查看' && categoriesEdit(item);
             },
             nodes: data
         });
    }
//    layui.tree({
//        elem: '#categoriesTreeView', //指定元素
//        click: function(item) {
//            first_title != '查看' && categoriesEdit(item);
//        },
//        nodes: createTree()
//    });
   
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
            btn: ['添加子节点', '上方添加节点', '下方添加节点', '删除'],
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) { // 选中在上方添加节点的回调
                layer.close(index);
                categoriesEditCommon(); // 调用store方法
            },
            btn2: function(index, layero) { // 选中在下方添加节点的回调
                layer.close(index);
                categoriesEditCommon(); // 调用store方法
            },
            btn3: function(index, layero) { // 选中在添加子节点的回调
                layer.close(index);
                categoriesEditCommon(); // 调用store方法
            },
            btn4: function(index, layero) { // 选中删除的回调
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
    // 商品分类详细信息
    function categoriesEditCommon() {
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
                // console.log(layero);
                layer.close(index); //如果设定了yes回调，需进行手工关闭
                inputReset(); // 清空表单

                // do something

                // 在新增的情况下，如果要添加的的内容已存在，则提示用户已存在
                if (second_title != 'view' && false) { // 第一参数表明是否新增，第二参数表明是否已存在
                    layer.open({
                        type: 1,
                        title: '提示',
                        skin: 'layui-layer-lan', //弹框主题
                        shade: 0,
                        area: '400px', //宽高
                        content: $('#categoriesAdd'),
                        btn: '确定',
                        btnAlign: 'c', //按钮居中
                        yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                            layer.close(index);
                        }
                    });
                }
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
    getdata(1);
	$("#searchCategories").click(function(){
		getdata(1);
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
     * 获取分页数据
     * @param pageIndex
     * @returns
     */
    function getdata(pageIndex){
    	$.ajax({
			url : "/findpartslevel",
			type : "get",
			async : false,
			data : {
				"pid":0,
				"pageSize":pageSize,
				"pageIndex":pageIndex
				},
			success : function(res){
				comboTable(res,pageIndex);
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
		var onlevel=``,twolevel=``,threelevel=``;
		var trs=``,one=``,two=``,three=``;
		//循环菜单树
		if(res.children!=null){
			for (var i = 0; i < res.children.length; i++) { //一级菜单
				onlevel+=`<option value="${res.children[i].id}">${res.children[i].name}</option>`;
				if(res.children[i].children!=null){
					for (var j = 0; j < res.children[i].children.length; j++) { //二级菜单
						twolevel+=`<option value="${res.children[i].children[j].id}">${res.children[i].children[j].name}</option>`;
//						one=`<tr><td>${res.children[i].name}</td>`;
//						trs+=one;
//						two=`<td>${res.children[i].children[j].name}</td>`;
//						trs+=two;
						if(res.children[i].children[i].children!=null){
							for (var q = 0; q <res.children[i].children[j].children.length; q++) {//三级菜单
								threelevel+=`<option value="${res.children[i].children[j].children[q].id}">${res.children[i].children[j].children[q].name}</option>`;
//								three=`<td>${res.children[i].children[j].children[q].name}</td>`;
//								trs+=three;
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
//				trs+=`
//					<td class="operation">
//	                <button id="view" data-method="view" class="layui-btn">查看</button>
//	                <button id="edit" data-method="edit" class="layui-btn layui-btn-normal">编辑</button>
//	                </td>`;
//				trs+=`</tr>`;
//				$("#partsTypeInfo").append(trs);
				continue;
				 
			}
			$("#oneleveltype").html(onlevel);
		}
		
		/**
		 * 拼接  一级  二级  三级  菜单
		 */
		initPage(res.totalCount,pageIndex); 
    }
});