layui.use(['layer', 'form', 'laypage', 'upload'], function() {
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
	        } else if (method == 'view') {
	            title = '查看';
	            $('#commodityBox input,#commodityBox select').each(function(elem) {
	                $(this).attr('disabled', 'disabled');
	            })
	        } else if (method == 'edit') {
	            title = '编辑';
	        }
	        method && layer.open({
	            type: 1,
	            title: '新增', // 标题
	            skin: 'layui-layer-lan', //弹框主题
	            shade: 0,
	            area: '800px', // 宽高
	            content: $('#commodityBox'), // 内容
	            btn: title == '查看' ? ['确定', '取消'] : ['提交', '取消'],
	            btnAlign: 'c', //按钮居中
	            yes: function(index, layero) {
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

    //初次进入页面的数据
    
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
		//page
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
					getData(obj.curr);
				}
			}
		})
		
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
				(data,pageIndex);
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
					'<td><button id="addCommodity" class="layui-btn" data-method="addCommodity" data-type="t">查看</button></td>'+
					'<td><button id="addCommodity" class="layui-btn layui-btn-normal" data-method="addCommodity" data-type="t">编辑</button></td>'+
					'<td>';
			if(tr.is_usable){
				trs += '<button id="isUse" class="layui-btn layui-btn-primary">下架</button>';
			}else{
				trs += '<button class="layui-btn layui-btn-normal uORd" >上架</button>';
			}
			trs +='</td></tr>'
		}
		$("#service_tb").html(trs);
		initPage(res.totalCount,pageIndex);
    }
    
    /**
     * 新增商品
     */ 
    function addPart(){
    	var partsAttrExtrs = attrExtra(); //扩展属性
    	var partsPics = attrExtra(); //图片集合
//    	$.ajax({
//			url : 'http://localhost:8881/addparts',
//			type : 'post',
//			async : false,
//			data : {
//				  "curPrice": $("").val(),
//				  "discount": $("").val(),
//				  "isUsable": $("").val(),
//				  "origin": $("").val(),
//				  "partsAttrExtrs":partsAttrExtrs,
//				  "partsBrandId": $("").val(),
//				  "partsCode": $("").val(),
//				  "partsFactory": $("").val(),
//				  "partsId": $("").val(),
//				  "partsModel": $("").val(),
//				  "partsName": $("").val(),
//				  "partsPics":partsPics ,
//				  "partsSpec": $("").val(),
//				  "partsType": $("").val(),
//				  "partsTypeId": $("").val(),
//				  "partsUnit": $("").val(),
//				  "pid": $("").val(),
//				  "price": $("").val(),
//				  "remark": $("").val(),
//				  "shelfLife": $("").val(),
//				  "typeName": $("").val()
//				},
//			success : function(data){
//				
//			}
//		});
    }
    
    /**
     * 编辑商品
     */
    function edidPart(){
    	//先查看 商品类型 ,封装一个查看方法
    }
    
    /**
     * 查看明细
     * @returns
     */
    function Detailview(){
    	$.ajax({
			url : "http://localhost:8881/getpartsdetail",
			type : "get",
			async : false,
			data : {"partsId":$("#partsId").val()},
			success : function(data){
				displayHtml(data);
			}
		});
    }
    
    function showDetail(data){
    	var pics; //图片
    	var attrExtra; //配件的动态属性
    	//get data
    	$("#partsCode").val(data.partsCode);
    	$("#partsName").val(data.partsName);
    	$("#partsModel").val(data.partsModel);
    	$("#partsSpec").val(data.partsSpec);
    	$("#partsBrandName").val(data.partsBrandName);
    	//显示图片
    	for (var int = 0; int < data.partsPicList.length; int++) {
			
		}
    	//显示动态属性
    	for (var int = 0; int < data..length; int++) {
			
		}
    }
    /**
     * 封装所有图片集合的json
     * @returns
     */
    function imgs(){
//    	var imgs = [],flag=0,json;
//    	
//    	$(".uploadImg img").each(function(){
//    		 json= {
//    				"sorting":flag,
//    				"url":$(this).attr("url")
//    		};
//    		imgs.push(json);
//    		flag++;
//    	});
//    	return JSON.stringify(imgs);
    	
    	
//    	"partsPics": [
//		    {
//		      "createTime": "2017-08-19T08:17:36.517Z",
//		      "partsId": 0,
//		      "partsPicId": 0,
//		      "remark": "string",
//		      "sorting": 0,
//		      "url": "string"
//		    }
//		  ],
    }
    /**
     * 封住所有扩展属性的json
     * @returns
     */
    function attrExtra(){
    	var attrExtra = [],json;
    	
    	$(".uploadImg img").each(function(){
    		 json= {
    				"attrKey":$("#a").val(),
    				"attrValue":$("#a").val()
    		};
    		imgs.push(json);
    	});
    	return JSON.stringify(imgs);
//    	[
//		    {
//		      "attrKey": "string",
//		      "attrValue": "string",
//		      "partsAttrExtrId": 0,
//		      "partsId": 0
//		    }
//		  ]
    	
    	 
    }
    
    
    /**
     * 封装ajax请求
     * @param {}
     * @returns
     */
    function ajax(obj){
    	console.log(obj.url);
    	$.ajax({
			url : obj.url,
			type : obj.method,
			async : false,
			data : obj.data,
			success : function(data){
				return data;
			}
		});
    }
});