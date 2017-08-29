layui.use(['jquery','layer', 'form', 'laypage', 'upload','tree'], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage;
		var pageSize = 10;
		selectTreeId=0;
		// 弹出框
	    var title; // 弹出框的标题
	    var active = {

	    };
	    // 触发弹框事件
	    $('.commodity').on('click', 'button', function() {
	        var method = $(this).data('method');
	        $('.closeBtn').removeAttr('style');
	        if (method == 'addCommodity') {
	            title = '新增';
	            afterAddPart();//点击新增按钮之后
	            //显示配件品牌
	        	partsBrand();
	        } else if (method == 'view') {
	            title = '查看';
	            Detailview($(this).parent().find("#partsId").val());
	            afterView();
	        } else if (method == 'edit') {
	            title = '编辑';
	            afterEditPart();//清空之前数据
				//编辑方法
	            Detailview($(this).parent().find("#partsId").val());
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
	            	// 校验规格、型号、品牌必填
	                var titleInfo; // 校验提示信
	    	    	if (!$('#partsSpec').val()) {
	                    titleInfo = '请输入规格参数，该项必填！';
	                    layer.msg(titleInfo);
	                    return;
	                } else if (!$('#partsModel').val()) {
	                    titleInfo = '请输入型号参数，该项必填！';
	                    layer.msg(titleInfo);
	                    return;
	                } else if (!$('#partsBrandId').val()) {
	                    titleInfo = '请输入品牌参数，该项必填！';
	                    layer.msg(titleInfo);
	                    return;
	                }else if(!$('#partsName').val()){
	                	titleInfo = '请输入配件名称参数，该项必填！';
	                	layer.msg(titleInfo);
	                	 return;
	                }else if(selectTreeId==0){
	                	titleInfo = '请选择配件分类，该项必填!';
	                	layer.msg(titleInfo);
	                	 return;
	                }else if(!$('#partsType').val()){
	                	titleInfo = '请选择配件类型,该项必填!';
	                	layer.msg(titleInfo);
	                	return;
	                }else if(!$('#partsBrandId').val()){
	                	titleInfo = '请选择品牌，,该项必填!';
	                	layer.msg(titleInfo);
	                	return;
	                }else if(!$('#price').val()){
	                	titleInfo = '请输入价格,该项必填!';
	                	layer.msg(titleInfo);
	                	return;
	                }else if(!$('#discount').val()){
	                	titleInfo = '请输入折扣,该项必填!';
	                	layer.msg(titleInfo);
	                	return;
	                }else if(!$('#curPrice').val()){
	                	titleInfo = '请输入现价,该项必填!';
	                	layer.msg(titleInfo);
	                	return;
	                }else if(!$('#partsUnit').val()){
	                	titleInfo = '请输入单位,该项必填!';
	                	layer.msg(titleInfo);
	                	return;
	                }
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
	               
	                !titleInfo && layer.close(index); // 如果必填校验出错，此时应不必关闭弹框；此处代码暂定，请根据需要进行修改
	            },
	            btn2: function(index, layero) { //取消按钮
	            	afterAddPart();
	            	afterEditPart();
	            	 afterView();
	                layer.close(index);
	                
	            },
	            end : function (){ //最后统一走的方法
	            	$('#commodityBox').css("display","none");
	            	
	            }
	        });
	    });
	     
<<<<<<< HEAD
	     /* 表单检查
	     * @returns
	     */
	    function formCheck(){
	    	var titleInfo;
	    	if (!$('#partsSpec').val()) {
                titleInfo = '请输入规格参数，该项必填！';
                layer.msg(titleInfo);
                return;
            } else if (!$('#partsModel').val()) {
                titleInfo = '请输入型号参数，该项必填！';
                layer.msg(titleInfo);
                return;
            } else if (!$('#partsBrandId').val()) {
                titleInfo = '请输入品牌参数，该项必填！';
                layer.msg(titleInfo);
                return;
            }else if(!$('#partsName').val()){
            	titleInfo = '请输入配件名称参数，该项必填！';
            	layer.msg(titleInfo);
            	 return;
            }else if(selectTreeId==-1){
            	titleInfo = '请选择配件分类，该项必填!';
            	layer.msg(titleInfo);
            	 return;
            }else if(!$('#partsType').val()){
            	titleInfo = '请选择配件类型,该项必填!';
            	layer.msg(titleInfo);
            	return;
            }else if(!$('#partsBrandId').val()){
            	titleInfo = '请选择品牌，,该项必填!';
            	layer.msg(titleInfo);
            	return;
            }else if(!$('#price').val()){
            	titleInfo = '请输入价格,该项必填!';
            	layer.msg(titleInfo);
            	return;
            }else if(!$('#discount').val()){
            	titleInfo = '请输入折扣,该项必填!';
            	layer.msg(titleInfo);
            	return;
            }else if(!$('#curPrice').val()){
            	titleInfo = '请输入现价,该项必填!';
            	layer.msg(titleInfo);
            	return;
            }else if(!$('#partsUnit').val()){
            	titleInfo = '请输入单位,该项必填!';
            	layer.msg(titleInfo);
            	return;
            }
	    
	    }
	    
	    
=======
>>>>>>> branch 'develop' of http://git.sinoauto.com/service/hqls-service.git
	    // 上下架事件及结果提示
	    $('.layui-table tbody').on('click', '#isUse', function() {
	        var othis = $(this);
	        var partsId= $(this).parent().find("#partsId").val();
	        $('#chooseState').text(othis.text());
	        //alert("你好");
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
	                	isUnable(partsId,1,othis);
	                } else { //下架操作
	                	isUnable(partsId,0,othis);
	                }
	                layer.close(index); //如果设定了yes回调，需进行手工关闭
	            },
	            btn2: function(index, layero) {
	                // console.log(layero);
	                layer.close(index);
	            },
	            end : function (){
	            	$('#commodityState').css("display","none");
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
	    });

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
			url : "/findparts",
			type : "get",
			async : false,
			data : {
				"partsCode":$("#partsCodeQuery").val(),
				"partsTypeId":selectTreeId, //暂无树形结构
				"partsTopTypeId":$("#partsTypeTop").val(),
				"partsName":$("#partsNameQuery").val(),
				"pageSize":pageSize,
				"pageIndex":pageIndex
				},
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
			if(tr.isUsable==0){
				$("#isUse").removeClass('layui-btn-warm').addClass('layui-btn-primary');
				trs += '<button id="isUse" class="layui-btn layui-btn-warm">下架</button>';
			}else{
				$("#isUse").removeClass('layui-btn-primary').addClass('layui-btn-warm');
				trs += ' <button id="isUse" class="layui-btn layui-btn-primary">上架</button>';
			}
			trs +='<input type="hidden" value="'+tr.partsId+'" id="partsId" /></td></tr>'
		}
		$(".parts_info").html(trs);
		initPage(res.totalCount,pageIndex); 
    }
    
    /**
     * 新增商品
     */ 
    function addPart(){
    	$.ajax({
			url:'/addparts',
			type:'POST',
			async:false,
			beforeSend : function(x) {
				x.setRequestHeader("Content-Type","application/json; charset=utf-8");
			},
			data :requestData(),
			success : function(data){
				var pageIndex =parseInt($(".layui-laypage-skip").val());
				selectTreeId = 0; //全部
				getData(pageIndex);
				alert("成功");
			},
			error : function(data) {
				alert("失败");
			}
		});
    }
    
    /**
     * 编辑商品
     */
    function editPart(){
    	//编辑商品
    	$.ajax({
			url : "/updateparts",
			type : "POST",
			contentType:"application/json;charset=utf-8",
			async : false,
			data : requestData(),
			success : function(data){
				var pageIndex =parseInt($(".layui-laypage-skip").val());
				selectTreeId = 0; //全部
				getData(pageIndex);
			},
			error:function (data){
				alert("编辑商品失败"+data);
			}
		});
    }
    
    
    /**
     * 上下架
     * @returns
     */
    function isUnable(partsId,isUsable,elem){
    	var Jsonstr ={"partsId":partsId,"isUsable":isUsable};
    		Jsonstr = JSON.stringify(Jsonstr);
    	$.ajax({
			url : "/updateparts",
			type : "POST",
			contentType:"application/json;charset=utf-8",
			async : false,
			data : Jsonstr,
			success : function(data){
				if (elem.text() === '上架') { //上架操作
					elem.removeClass('layui-btn-primary').addClass('layui-btn-warm');
					elem.text('下架');
                } else { //下架操作
                	elem.removeClass('layui-btn-warm').addClass('layui-btn-primary');
                	elem.text('上架');
                }
				alert("操作成功");
			},
			error:function(data){
				alert("操作失败");
			}
		});
    }
    
    /**
     * 查看明细
     * @returns
     */
    function Detailview(partsId){
    	$.ajax({
			url : "/getpartsdetail",
			type : "get",
			async : false,
			data :{"partsId":partsId},
			success : function(data){
				if(data.errcode==0){
					displayHtml(data,data.result.partsBrandId);
				}else{
					alert("undefined");
				}
				
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
    	}else{
    		piclen=data.result.partsPicList.length;
    	}
    	if(data.result.partsAttrExtrs==null){
    		attrlen=0;
    	}else{
    		attrlen = data.result.partsAttrExtrs.length;
    	}
    	var pics=""; //图片
    	var attrExtra=""; //配件的动态属性
    	//get data
    	$("#partsId").attr("value",data.result.partsId);
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
    	//$(".uploadImg").html(""); //清空图片上传div
    	$(".attrExtra").html("");//清空动态扩展属性的div
    	for (var i = 0; i < piclen; i++) {
    		pics += `
               <div class="siteUpload">
	                <img id="commodityImgUrl${i}" src="${data.result.partsPicList[i].url}">
	                <div class="layui-box layui-upload-button">
                		<input type="file" name="image" class="commodityImg" class="layui-upload-file" onchange="change(this)" accept="image/*">
                		<span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>图片上传</span>
					</div>
            		<span class="closeBtn" onclick="delImg(this)"><i class="layui-icon">&#x1006;</i></span>
    			</div>
            `;
		}
    	//给一个上传用的图片
//    	pics+=`
//			<div class="siteUpload">
//            	<img id="commodityImgUrl" src="">
//            	<div class="layui-box layui-upload-button">
//                <input type="file" name="image" class="commodityImg" class="layui-upload-file" onchange="change(this)" accept="image/*">
//                <span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>编辑图片上传</span>
//				</div>
//            	<span class="closeBtn" onclick="delImg(this)"><i class="layui-icon">&#x1006;</i></span>
//			</div>
//		`;
    	
    	//动态属性追加到指定位置
    	$(".uploadImg").prepend(pics);
    	
    	var flag= 0;
    	for (var i = 0; i <=attrlen; i++) {
    			//清空动态属性框,然后拼接显示
    			if((flag%3==0&&i>0)||(flag==attrlen)){
        			$(".attrExtra:last").html(attrExtra);
        			$(".attrExtra:last").after("<tr class=\"attrExtra\"></tr>");
        			attrExtra="";
        		}
    			if(i<attrlen){
    				attrExtra+=`
                        <td colspan="2" class="paramGroup">
                            <input type="text" name="" value="${data.result.partsAttrExtrs[i].attrKey}" class="layui-input paramName" placeholder="请输入参数名称">
                            <span></span>
                            <input type="text" name="" value="${data.result.partsAttrExtrs[i].attrValue}" class="layui-input paramContent" placeholder="请输入内容">
                    	</td>
        	        `;
    			}
    			flag++;
		}
    	
    	/**
    	 * 配件品牌显示
    	 */
    	partsBrand(partsBrandId);
    	
    	/**
    	 * 配件分类
    	 *
    	 */
    	$("#cName").val(data.result.typeName);
    	selectTreeId = data.result.partsTypeId;
    	
    	/**
    	 * 上下架
    	 */
    	if(data.result.isUsable==0){
    		$('input:radio:last').attr('checked', 'checked');
    	}else{
    		$('input:radio:first').attr('checked', 'checked');
    	}
    	
    	/**
    	 * 配件类型
    	 */
    	if(data.result.partsType==1){
    		$("#partsType").find("option[value='1']").attr("selected",true);
    	}else if(data.result.partsType==2){
    		$("#partsType").find("option[value='2']").attr("selected",true);
    	}
    	
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
			url : "/findpartsbrands",
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
    	var html=`<select class="layui-select" id="partsBrandId">`;
    	var brand= data.result==null?0:data.result.length;
    	for (var i = 0; i < brand; i++) {
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
    	$(".uploadImg .siteUpload img").each(function(i){
    		var url = $(this).attr("src");
    		if(url!=""&&url!=undefined){
    			imgs+="{\"sorting\":"+flag+",\"url\":\""+url+"\"},";
        		flag++;
    		}
    	});
    	if(imgs.indexOf(",")>0){
    		imgs = imgs.substring(0,imgs.length-1);
    	}
    	imgs+="]";
    	return imgs;
    }
    
    /**
     * 封住所有扩展属性的json
     * @returns
     */
    function attrExtra(){
    	var attrExtra ="[",flag=1;
    	var len =$(".attrExtra .paramGroup").length;
    	$(".attrExtra .paramGroup").each(function(i){
    		var paramName = $(this).find(".paramName").val();;
    		var paramContent=$(this).find(".paramContent").val();
    		console.log("paramName:"+paramName+"\nparamContent:"+paramContent);
    		if(paramName!=null&&paramName!=""&&paramContent!=null&&paramContent!=""){
	    			attrExtra += "{\"attrKey\":\""+paramName+"\",\"attrValue\":\""+paramContent+"\"},";
    		}
    	});
    	if(attrExtra.indexOf(",")>0){
    		attrExtra = attrExtra.substring(0,attrExtra.length-1);
    	}
    	attrExtra+="]";    	
    	return attrExtra;
    }
    
    
    /**
     * 封装新增和add的请求数据
     * @returns 返回json 字符串
     */
    function requestData(){
    	var partsAttrExtrs = attrExtra(); //扩展属性
    	var partsPics = imgs(); //图片集合
    	var partsBrandId = $("#partsBrandId").val()==undefined?-1:$("#partsBrandId").val(),
    			curPrice=$("#curPrice").val()==undefined?-1:$("#curPrice").val(),
    			discount=$("#discount").val()==undefined?-1:$("#discount").val(),
    			partsTypeId=$("#partsTypeId").val()==undefined?-1:$("#partsTypeId").val(),
    			partsId=$("#partsId").val()==undefined?-1:$("#partsId").val(),
    			//pid=$("#pid").val()==undefined?-1:$("#pid").val(),
    			uable=$('input:radio:checked').val(),
    			typeName=$("#cName").val()==undefined?"":$("#cName").val()
    			; 
    	console.log(uable);
    	var dataJson="{" +
    					"\"partsBrandId\":\""+partsBrandId+"\"," +
    					//"\"partsCode\":\""+$("#partsCode").val()+"\"," +
    					"\"partsModel\":\""+$("#partsModel").val()+"\"," +
    					"\"curPrice\":\""+curPrice+"\"," +
    					"\"discount\":\""+discount+"\"," +
    					"\"isUsable\":\""+uable+"\"," +
    					"\"origin\":\""+$("#origin").val()+"\"," +
    					"\"partsFactory\":\""+$("#partsFactory").val()+"\"," +
    					"\"partsSpec\":\""+$("#partsSpec").val()+"\"," +
    					//"\"partsTypeId\":\""+partsTypeId+"\"," +
    					"\"partsType\":\""+$("#partsType").val()+"\"," +
    					"\"partsUnit\":\""+$("#partsUnit").val()+"\"," +
    					"\"partsTypeId\":\""+selectTreeId+"\"," +
    					//"\"pname\":\""+typeName+"\"," +
    					"\"price\":\""+$("#price").val()+"\"," +
    					"\"shelfLife\":\""+$("#shelfLife").val()+"\"," +
    					"\"typeName\":\""+typeName+"\"," +
    					"\"partsName\":\""+$("#partsName").val()+"\"," +
    					"\"partsId\":\""+partsId+"\"," +
    					"\"partsPics\":";
    	dataJson+=partsPics+",";
    	dataJson+="\"partsAttrExtrs\":"+partsAttrExtrs;
		dataJson+="}";
		//alert("dataJosn---->"+dataJson);
		console.log("dataJson"+dataJson);
		dataJson = JSON.parse(dataJson); //解析成json对象
		var data =JSON.stringify(dataJson);//转换为json字符串
		return data;
    }

    //点击新增按钮
    function afterAddPart(){
    	//清空数据
        $("#commodityBox input,#commodityBox select").not("input:radio").each(function(){
        	$(this).removeAttr("disabled");
        	$(this).val('');
        });
        $("#form")[0].reset(); //清空表单数据
        $("#cName").val(''); //清空树形菜单数据
        $("#modelContent a").removeClass('curSelectedNode'); //移除样式
        selectTreeId=0;//清空树形菜单id
        //清空图片
        //$(".uploadImg").html('');
    }
    
    /**
     * 编辑之后清空
     * @returns
     */
    function afterEditPart(){
    	 $("#form")[0].reset();
    	 $(".uploadImg").html('');
    }
    
    //点击查看按钮调用方法
    function afterView(){
    	 $('#commodityBox input,#commodityBox select').each(function(elem) {
             $(this).attr('disabled', 'disabled');
         });
    	 $('.closeBtn').css('display', 'none');
    }
});

/**
 * 图片上传
 * @param obj 图片对象
 * @returns
 */
function change(obj){
	//alert("文件上传");
	var flag=$(".siteUpload").length;
	if(flag==5){
		//alert("最后一张图片上传");
		upload(obj);
		//alert("只能上传5张图片");
		return;
	}
	//上传图片
	upload(obj);
	
} 

function upload(obj){
	console.log("上传的文件---->,",obj);
	var form =$("#form");
	var imgUrl = uploadImg(form,obj);
	if(imgUrl!=""){ //如果上传文件成功
		appendImg(obj);
		$(obj).parent().parent().find("img").attr("src",imgUrl);
		//禁用上传按钮
		$(obj).attr("disabled", "disabled");
	}else{
		//alert("图片上传返回路径:--->",imgUrl);
	}
	
}

//追加图片
function appendImg(obj){
	var img=`
		<div class="siteUpload">
            <img id="commodityImgUrl" src="">
            <div class="layui-box layui-upload-button">
                <input type="file" name="image" class="commodityImg" class="layui-upload-file" onchange="change(this)" accept="image/*"> 
                <span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>图片上传</span>
            </div>
            <span class="closeBtn" onclick="delImg(this)"><i class="layui-icon">&#x1006;</i></span>
        </div>`;
	
	$(obj).parent().parent().parent().append(img);
	var flag=$(".siteUpload").length;
	if(flag==6){
		//隐藏最后一张图片
		$(".siteUpload:last").hide();
	}else{
		$(".siteUpload:last").show();
	}
	//$(".siteUpload:last").find(".closeBtn").css('display', 'none');
}
/**
 * 删除图片
 * @param obj 删除按钮
 * @returns
 */
function delImg(obj){
	var url = $(obj).parent().find("img").attr("src");
	delFile(url);
	//干掉div
	$(obj).parent().remove(); 
	var flag=$(".siteUpload").length;
	if(flag<6){
		appendImg(obj);
	}
}
//在配件树中选中配件后写入input中
function beforeClick(treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    if (!check) {
        layer.msg("该项不可选择！");
    }
    return check;
}

/**
 * 
 * @param e 当前元素
 * @param treeId id="cTree"
 * @param treeNode  当前节点
 * @returns
 */
function onClick(e, treeId, treeNode) {
	//console.log("e:",e,"\ntreeId:",treeId,"\ntreeNode:",treeNode);
	//var nodes = $.fn.zTree.getZTreeObj(treeId).getSelectedNodes();
	$(e.target).parents('li').siblings('li').find('.curSelectedNode').removeClass('curSelectedNode');
	if (treeId == 'commodityTree') {
		// 保存id
		selectTreeId = treeNode.id;
		console.log("onClick--->"+treeNode.id);
	$("#commodityName").val(treeNode.name);
	} else {
		// 保存id
		selectTreeId = treeNode.id;
	$("#cName").val(treeNode.name);
	}
}

var treeTemp;
// 配件树展示与收缩
function showMenu(temp) {
    treeTemp = temp;
    if (treeTemp == 'model') {
        $("#modelContent").css({ left: 0, top: "37px" }).slideDown("fast");
    } else if (treeTemp == 'main') {
        $("#menuContent").css({ left: 0, top: "37px" }).slideDown("fast");
    }
    $("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
    if (treeTemp == 'model') {
        $("#modelContent").fadeOut("fast");
    } else if (treeTemp == 'main') {
        $("#menuContent").fadeOut("fast");
    }
    $("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
    if (treeTemp == 'model') {
        if (!(event.target.id == "menuBtn" || event.target.id == "modelContent" || $(event.target).parents("#modelContent").length > 0)) {
            hideMenu();
        }
    } else if (treeTemp == 'main') {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
            hideMenu();
        }
    }
}

/**
 * 配件树数据源
 * @returns
 */
function zNodes(){
	var node;
	$.ajax({
		url : "/findpartstype",
		type : "get",
		async : false,
		success : function(data){
			//node =JSON.stringify(data);
			node =data;
		},
		error:function(data){
			alert("页面查询失败");
		}
	});
	return node;
}
var zNodes = zNodes();
zNodes.unshift({ id: 0, pId: -1, name: "全部",open: true });
//alert(JSON.stringify(zNodes));
// 配件树初始化
$(document).ready(function() {
    $.fn.zTree.init($("#commodityTree"), setting,zNodes);
    $.fn.zTree.init($("#cTree"),setting,zNodes);
});
// 配件树设置
var setting = {
    view: {
        dblClickExpand: false,
        autoCancelSelected: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        //beforeClick: beforeClick, //检查
        onClick: onClick
    }
};


// 配件树数据源
//var zNodes = [
//    { id: 1, pId: 0, name: "北京" },
//    { id: 2, pId: 0, name: "天津" },
//    { id: 3, pId: 0, name: "上海" },
//    { id: 6, pId: 0, name: "重庆" },
//    { id: 4, pId: 0, name: "河北省", open: true },
//    { id: 41, pId: 4, name: "石家庄" },
//    { id: 42, pId: 4, name: "保定" },
//    { id: 43, pId: 4, name: "邯郸" },
//    { id: 44, pId: 4, name: "承德" },
//    { id: 5, pId: 0, name: "广东省", open: true },
//    { id: 51, pId: 5, name: "广州" },
//    { id: 52, pId: 5, name: "深圳" },
//    { id: 53, pId: 5, name: "东莞" },
//    { id: 54, pId: 5, name: "佛山" },
//    { id: 6, pId: 0, name: "福建省", open: true },
//    { id: 61, pId: 6, name: "福州" },
//    { id: 62, pId: 6, name: "厦门" },
//    { id: 63, pId: 6, name: "泉州" },
//    { id: 64, pId: 6, name: "三明" }
//];
