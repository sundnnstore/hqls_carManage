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
	            $("#commodityBox input,#commodityBox select").each(function(){
	            	$(this).attr("value","");
	            });
	            //显示配件品牌
	        	partsBrand();
	        } else if (method == 'view') {
	            title = '查看';
	            Detailview($(this).parent().find("#partsId").val());
	            $('#commodityBox input,#commodityBox select').each(function(elem) {
	                $(this).attr('disabled', 'disabled');
	            });
	            
	        } else if (method == 'edit') {
	            title = '编辑';
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
	                if (!$('#partsSpec').val()) {
	                    titleInfo = '请输入规格参数，该项必填！';
	                } else if (!$('#partsModel').val()) {
	                    titleInfo = '请输入型号参数，该项必填！';
	                } else if (!$('#partsBrandId').val()) {
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
	                	isUnable(partsId,0,othis);
	                } else { //下架操作
	                	isUnable(partsId,0,othis);
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
			url : "/findparts",
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
				alert(data);
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
				alert("编辑商品成功"+data.errmsg);
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
    	 //清空数据
        $("#commodityBox input,#commodityBox select").each(function(){
        	$(this).attr("value","");
        });
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
    	$(".uploadImg").html(""); //清空图片上传div
    	$(".attrExtra").html("");//清空动态扩展属性的div
    	//显示图片
    	for (var i = 0; i < piclen; i++) {
    		pics += `
               <div class="siteUpload">
	                <img id="commodityImgUrl${i}" src="${data.result.partsPicList[i].url}">
	                <div class="layui-box layui-upload-button">
                    <input type="file" name="image" id="commodityImg${i}" value="" class="layui-upload-file">
                    <span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>上传图片</span>
                </div>
    			</div>
            `;
		}
    	//alert(pics+":pics");
    	//动态属性追加到指定位置
    	$(".uploadImg").html(pics);
    	
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
    	 * 上下架
    	 */
    	$('input:radio:last').attr('checked', 'checked');
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
    	var len =$(".attrExtra .paramGroup").length;
    	$(".attrExtra .paramGroup").each(function(i){
    		var paramName = $(this).find(".paramName").val();;
    		var paramContent=$(this).find(".paramContent").val();
    		
    		console.log("paramName:"+paramName+"\nparamContent:"+paramContent);
    		if(paramName!=null&&paramContent!=null){
	    		if(i==(len-1)){
	    			attrExtra += "{\"attrKey\":\""+paramName+"\",\"attrValue\":\""+paramContent+"\"}";
	    		}else{
	    			attrExtra += "{\"attrKey\":\""+paramName+"\",\"attrValue\":\""+paramContent+"\"},";
	    		}
    		}
    	});
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
    	var partsBrandId = $("#partsBrandId").val()=="undefined"?-1:$("#partsBrandId").val(),
    			curPrice=$("#curPrice").val()=="undefined"?-1:$("#curPrice").val(),
    			discount=$("#discount").val()=="undefined"?-1:$("#discount").val(),
    			isUsable=$('input[name="state"]:checked').val(),
    			partsTypeId=$("#partsTypeId").val()=="undefined"?-1:$("#partsTypeId").val(),
    			partsId=$("#partsId").val()=="undefined"?-1:$("#partsId").val()
    			pid=$("#pid").val()=="undefined"?-1:$("#pid").val()
    			; 
    	//alert("isUsable:"+isUsable);
    	var dataJson="{" +
    					"\"partsBrandId\":\""+partsBrandId+"\"," +
    					"\"partsCode\":\""+$("#partsCode").val()+"\"," +
    					"\"partsModel\":\""+$("#partsModel").val()+"\"," +
    					"\"curPrice\":\""+curPrice+"\"," +
    					"\"discount\":\""+discount+"\"," +
    					"\"isUsable\":\""+0+"\"," +
    					"\"origin\":\""+$("#origin").val()+"\"," +
    					"\"partsFactory\":\""+$("#partsFactory").val()+"\"," +
    					"\"partsSpec\":\""+$("#partsSpec").val()+"\"," +
    					//"\"typeName\":\""+$("#partsTypeId").find("option:selected").text()+"\"," +
    					//"\"partsTypeId\":\""+partsTypeId+"\"," +
    					"\"partsType\":\""+$("#partsType").val()+"\"," +
    					"\"partsUnit\":\""+$("#partsUnit").val()+"\"," +
    					"\"pid\":\""+pid+"\"," +
    					"\"price\":\""+$("#price").val()+"\"," +
    					"\"shelfLife\":\""+$("#shelfLife").val()+"\"," +
    					//"\"typeName\":\""+$("#typeName").val()+"\"," +
    					"\"partsName\":\""+$("#partsName").val()+"\"," +
    					"\"partsId\":\""+partsId+"\"," +
    					"\"partsPics\":";
    	dataJson+=partsPics+",";
    	dataJson+="\"partsAttrExtrs\":"+partsAttrExtrs;
		dataJson+="}";
		console.log(dataJson);
		dataJson = JSON.parse(dataJson); //解析成json对象
		var data =JSON.stringify(dataJson);//转换为json字符串
		return data;
    }
    
    
    /**
     * 图片上传
     */
    function uploadImg(obj){
    	alert("你好");
////    	alert($(obj));
//    	//判断,最后一个图片 ,上传追加,前提是 最后一个图片的不大于最大的上传限
//    	var abc = $(obj).parent().parent();
//    	appendImg(abc);
////    	alert(abc.html());
//    	alert($(abc).html());
    }
    
    /**
     * 追加图片
     * @returns
     */
    function appendImg(obj){
    	    var index = $(".siteUpload").index(obj);
    	    alert(index);
    	   
//    	    $("ul li").click(function () {
//    	        var index = $("ul li").index(this);
//    	        alert(index);
//    	     });
    }
    
    /**
     * 图片新增
     */
    $(".uploadImg").on('change', '.commodityImg', function(obj) {
    	alert($(obj).html());
    	alert("图片点击事件");
    });
    
});
