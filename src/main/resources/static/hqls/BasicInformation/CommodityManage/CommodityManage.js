layui.use(['jquery','layer', 'form', 'laypage', 'upload'], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage;
		var pageSize = 10;
		// 弹出框
	    var title; // 弹出框的标题
	    var active = {

	    };
	    // 触发弹框事件
	    $('#myContent').on('click', 'button', function() {
	        var method = $(this).data('method');
	        if (method == 'addCommodity') {
	            title = '新增';
	            //清空数据
	            $("#commodityBox input").each(function(){
	            	$(this).attr("value","");
	            });
	        } else if (method == 'view') {
	            title = '查看';
	            $('#commodityBox input,#commodityBox select').each(function(elem) {
	                $(this).attr('disabled', 'disabled');
	            });
	            Detailview();
	        } else if (method == 'edit') {
	            title = '编辑';
				//编辑方法
	            Detailview();
	        }
	        method && layer.open({
	            type: 1,
	            title: title, // 标题
	            skin: 'layui-layer-lan', //弹框主题
	            shade: 0,
	            area: '800px', // 宽高
	            content: $('#commodityBox'), // 内容
	            btn: title == '查看' ? ['确定', '取消'] : ['提交', '取消'],
	            btnAlign: 'c', //按钮居中
	            yes: function(index, layero) {
	            	switch(title){
	            		case "新增":
	            			addPart();
	            			break;
	            		case "编辑":
	            			editPart();
	            			break;
	            		case "查看":
	            			break;
	            		default:
	            			alert("未知标题");
	            			break;	
	            	}
	                // 校验规格、型号、品牌必填
	                var titleInfo; // 校验提示信息
	                if (!$('#spec').val()) {
	                    titleInfo = '请输入规格参数，该项必填！';
	                } else if (!$('#model').val()) {
	                    titleInfo = '请输入型号参数，该项必填！';
	                } else if (!$('#brand').val()) {
	                    titleInfo = '请输入品牌参数，该项必填！';
	                }
	                titleInfo && layer.msg(titleInfo);

	                !titleInfo && layer.close(index); // 如果必填校验出错，此时应不必关闭弹框；此处代码暂定，请根据需要进行修改
	            },
	            btn2: function(index, layero) {
	                // console.log(layero);
	                layer.close(index);
	            }
	        });
	    });
	    // 上下架事件及结果提示
	    $('.layui-table tbody').on('click', '#isUse', function() {
	        var othis = $(this);
	        $('#chooseState').text(othis.text());
	        layer.open({
	            type: 1,
	            title: '提示', // 标题
	            skin: 'layui-layer-lan', //弹框主题
	            shade: 0,
	            area: '300px', // 宽高
	            content: $('#commodityState'), // 内容
	            btn: ['确定', '取消'],
	            btnAlign: 'c', //按钮居中
	            yes: function(index, layero) {
	                if (othis.text() === '上架') { //上架操作
	                    if (true) { //上架成功：切换成下架按钮
	                        othis.removeClass('layui-btn-primary').addClass('layui-btn-warm');
	                        othis.text('下架')
	                    } else { //上架失败：不切换按钮，同时提示用户上架失败
	                        layer.msg('上架失败');
	                    }
	                } else { //下架操作
	                    if (true) { //下架成功切换成下架按钮
	                        othis.removeClass('layui-btn-warm').addClass('layui-btn-primary');
	                        othis.text('上架')
	                    } else { //下架失败：不切换按钮，同时提示用户上架失败
	                        layer.msg('下架失败');
	                    }
	                }
	                layer.close(index); //如果设定了yes回调，需进行手工关闭
	            },
	            btn2: function(index, layero) {
	                // console.log(layero);
	                layer.close(index);
	            }
	        });
	    });

	    // 在商品参数项末尾新加一行新的参数框
	    $('#addOption').click(function() {
	        var html = `
	            <tr class="attrExtra">
	                <td colspan="2" class="paramGroup">
	                    <input type="text" name="" class="layui-input paramName" placeholder="请输入参数名称">
	                    <span></span>
	                    <input type="text" name="" class="layui-input paramContent" placeholder="请输入内容">
	                </td>
	                <td colspan="2" class="paramGroup">
	                    <input type="text" name="" class="layui-input paramName" placeholder="请输入参数名称">
	                    <span></span>
	                    <input type="text" name="" class="layui-input paramContent" placeholder="请输入内容">
	                </td>
	                <td colspan="2" class="paramGroup">
	                    <input type="text" name="" class="layui-input paramName" placeholder="请输入参数名称">
	                    <span></span>
	                    <input type="text" name="" class="layui-input paramContent" placeholder="请输入内容">
	                </td>
	            </tr>
	        `;
	        $('.modelTableContainer tbody').append(html);
	    })

    /**
     * 页面加载默认显示
     */
    getData(1);
	$("#searchCommodity").click(function(){
		getData(1);
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
					getData(obj.curr);
				}
			}
		});
		
	}
    
    
    /**
     * 获取分页数据
     * @param pageIndex
     * @returns
     */
    function getData(pageIndex){
    	$.ajax({
			url : "http://localhost:8881/findparts",
			type : "get",
			async : false,
			data : {
				"partsCode":$("#searchCommodity").val(),
				"partsTypeId":$("#searchCommodity").val(),
				"partsTopTypeId":$("#searchCommodity").val(),
				"partsName":$("#searchCommodity").val(),
				"pageSize":pageSize,
				"pageIndex":pageIndex
				},
			success : function(data){
				comboTable(data,pageIndex);
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
    	var data = res.result;
		var trs = "";
		for(i=0;i<data.length;i++){
			var tr = data[i];
			trs += '<tr>'+
					'<td>'+tr.partsName+'</td>'+
					'<td>'+tr.partsTopType+'</td>'+
					'<td>'+tr.pName+'</td>'+
					'<td>'+tr.partsName+'</td>'+
					'<td>'+tr.partsCode+'</td>'+
					'<td><button id="view" data-method="view" class="layui-btn">查看</button>'+
					'<button id="edit" data-method="edit" class="layui-btn layui-btn-normal">编辑</button>'+
					'';
			if(tr.is_usable){
				trs += '<button id="isUse" class="layui-btn layui-btn-warm">下架</button>';
			}else{
				trs += ' <button id="isUse" class="layui-btn layui-btn-primary">上架</button>';
			}
			trs +='</td></tr>'
		}
		$(".parts_info").html(trs);
		initPage(res.totalCount,pageIndex); 
    }
    
    /**
     * 新增商品
     */ 
    function addPart(){
    	var partsAttrExtrs = attrExtra(); //扩展属性
    	var partsPics = imgs(); //图片集合
    	var dataJson="{\"partsBrandId\":"+$("#partsBrandId").val()+"," +
    			"\"partsCode\":\""+$("#partsCode").val()+"\"," +
    					"\"partsModel\":\""+$("#partsModel").val()+"\"," +
    							"\"partsName\":\""+$("#partsName").val()+"\"," +
    									"\"partsPics\":";
    	dataJson+=partsPics+",";
    	dataJson+="\"partsPics\":"+partsAttrExtrs+",";
    	dataJson+="\"typeName\":"+$("#").val();
		dataJson+="}";
		dataJson = JSON.parse(dataJson); //解析成json对象
		var data =JSON.stringify(dataJson);//转换为json字符串
    	$.ajax({
			url:'http://localhost:8881/addparts',
			type:'POST',
			async:false,
			beforeSend : function(x) {
				x.setRequestHeader("Content-Type","application/json; charset=utf-8");
			},
			data :data,
			success : function(data){
				alert(data);
				alert("成功");
			},
			error : function(data) {
				alert("失败");
				alert(data.errmsg);
			}
		});
    }
    
    /**
     * 编辑商品
     */
    function editPart(){
    	//编辑商品
    	$.ajax({
			url : "http://localhost:8881/updateparts",
			type : "POST",
			async : false,
			data : {"partsId":$("#partsId").val()},
			success : function(data){
				alert("编辑商品成功");
			}
		});
    }
    
    
    /**
     * 上下架
     * @returns
     */
    function isUnable(){
    	$.ajax({
			url : "http://localhost:8881/updateparts",
			type : "POST",
			async : false,
			data : {"partsId":$("#partsId").val()},
			success : function(data){
				//提示上下架成功
			}
		});
    }
    
    /**
     * 查看明细
     * @returns
     */
    function Detailview(){
    	//var data = {"partsId":1};//$("#partsId").val()
    	//data=JSON.stringify(data);
    	$.ajax({
			url : "http://localhost:8881/getpartsdetail",
			type : "get",
			async : false,
			data :{"partsId":1},
			success : function(data){
				displayHtml(data,data.result.partsBrandId);
			},
			error:function(data){
				alert("查看明细失败返回值"+data.errmsg);
			}
		});
    }
    /**
     * 显示配件明细数据
     * @param data
     * @returns
     */
    function displayHtml(data,partsBrandId){
    	var piclen,attrlen;
    	if(data.result.partsPicList==null){
    		piclen=0;
    	}
    	if(data.result.partsAttrExtrs==null){
    		attrlen=0;
    	}
    	var pics=""; //图片
    	var attrExtra=""; //配件的动态属性
    	//get data
    	$("#partsCode").attr("value",data.result.partsCode);
    	$("#partsName").attr("value",data.result.partsName);
    	$("#partsModel").attr("value",data.result.partsModel);
    	$("#partsSpec").attr("value",data.result.partsSpec);
    	$("#price").attr("value",data.result.price);
    	$("#discount").attr("value",data.result.discount);
    	$("#curPrice").attr("value",data.result.curPrice);
    	$("#origin").attr("value",data.result.origin);
    	$("#partsFactory").attr("value",data.result.partsFactory);
    	$("#shelfLife").attr("value",data.result.shelfLife);
    	$("#partsUnit").attr("value",data.result.partsUnit);
    	//$("#partsBrandName").val(data.result.partsBrandName);
    	//清空图片和扩展属性
    	$("#uploadImg").html(""); //清空图片上传div
    	$(".attrExtra").html("");//清空动态扩展属性的div
    	//显示图片
    	for (var int = 0; int < piclen; int++) {
    		pics = `
               <div class="siteUpload">
	                <img id="commodityImgUrl${int}" src="">
	                <div>
	                    <input type="file" name="image" id="commodityImg${int}" value="">
	                </div>
    			</div>
            `;
    		
		}

    	for (var int = 0; int < attrlen; int++) {
			//清空动态属性框,然后拼接显示
    		attrExtra = `
                <td colspan="2" class="paramGroup">
	                    <input type="text" name="" class="layui-input paramName" placeholder="请输入参数名称">
	                    <span></span>
	                    <input type="text" name="" class="layui-input paramContent" placeholder="请输入内容">
	            </td>
            `;
		}
    	//动态属性追加到指定位置
    	$("#uploadImg").html(pics);
    	$(".attrExtra").html(attrExtra);
    	
    	/**
    	 * 配件品牌显示
    	 */
    	partsBrand(partsBrandId);
    }
    
    /**
     * 通用件,易损件,查询
     * @returns
     */
    function partsTopType(){
    	
    }
    
    /**
     * 菜单树
     * @returns
     */
    function partsParentType(){
    	
    }
    
	/**
	 * 查询配件的品牌
	 * @param partsBranId  配件对应的ID
	 * @returns
	 */
	function partsBrand(partsBranId){
    	$.ajax({
			url : "http://localhost:8881/findpartsbrands",
			type : "get",
			async : false,
			success : function(data){
				if(partsBranId===undefined){
					displayPartsBrand(data);
				}else{
					displayPartsBrand(data,partsBranId);
				}
				
			}
		});
    }
	
    /**
     * 显示配件品牌
     * @returns
     */
    function displayPartsBrand(data,partsBrandId){
    	//清空
    	$(".partsBrands").html("");
    	var html=`<select class="layui-select">`;
    	var brand= data.result==null?0:data.result.length;
    	for (var i = 0; i < brand; i++) {
    		//alert("当前品牌ID"+data.result[i].partsBrandId+"\n配件对应的品牌ID"+partsBrandId);
    		if(partsBrandId==data.result[i].partsBrandId){
    			html+=`<option value="${data.result[i].partsBrandId}" selected>${data.result[i].partsBrandName}</option>`;
    		}else{
    			html+=`<option value="${data.result[i].partsBrandId}">${data.result[i].partsBrandName}</option>`;
    		}
		}
    	html+=`</select>`;
    	$(".partsBrands").html(html);
    }
    
    /**
     * 封装
     * 	新增,或者编辑图片集合的json
     * @returns
     */
    function imgs(){
    	var imgs = "[",flag=1;
    	var len = $(".uploadImg img").length;
    	$(".uploadImg input").each(function(i){
    		if(i==(len-1)){
    			imgs+="{\"sorting\":"+flag+",\"url\":\""+$(this).attr("url")+"\"}";
    		}else{
    			imgs+="{\"sorting\":"+flag+",\"url\":\""+$(this).attr("url")+"\"},";
    		}
    		flag++;
    	});
    	imgs+="]";
    	return imgs;
    }
    
    /**
     * 封住所有扩展属性的json
     * @returns
     */
    function attrExtra(){
    	var attrExtra ="[",flag=1;
    	var len =$(".attrExtra td").length;
    	$(".attrExtra paramGroup").each(function(i){
    		if(i==(len-1)){
    			attrExtra += "{\"attrKey\":\""+$(".paramName").val()+"\",\"paramContent\":\""+$(".attrValue").val()+"\"}";
    		}else{
    			attrExtra += "{\"attrKey\":\""+$(".paramName").val()+"\",\"paramContent\":\""+$(".attrValue").val()+"\"},";
    		}
    	});
    	attrExtra+="]";
    	return attrExtra;
    }
    
    
});

//var dataJson ={
// //"curPrice": $("").val(),
// //"discount": $("").val(),
// //"isUsable": $("").val(),
// //"origin": $("").val(),
// //"partsAttrExtrs":partsAttrExtrs,
////"partsFactory": $("").val(),
////"partsId": $("").val(),
// "partsBrandId":$("#partsBrandId").val(),  
// "partsCode":$("#partsCode").val(),
// "partsModel":$("#partsModel").val(),
// "partsName":$("#partsName").val(),
// "partsPics":partsPics,
// "partsSpec":$("#partsSpec").val()
// //"partsType": $("").val(),
// //"partsTypeId": $("").val(),
// //"partsUnit": $("").val(),
// //"pid": $("").val(),
// //"price": $("").val(),
// //"remark": $("").val(),
// //"shelfLife": $("").val(),
// //"typeName": $("").val()
//
//};