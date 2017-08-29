layui.use(['layer', 'tree', 'form', 'laypage'], function() {
    var layer = layui.layer,
        form = layui.form(),
        laypage = layui.laypage,
        $ = layui.jquery;
    
    var pageSize = 10;
    
    comboProvinces('province_search');
    comboProvinces('province');
    
    $("#province_search").on('change',function(){
    	comboChlidren('province_search','city_search');
    });
    
    $("#city_search").on('change',function(){
    	comboChlidren('city_search','county_search');
    });
    
    $("#province").on('change',function(){
    	comboChlidren('province','city');
    });
    
    $("#city").on('change',function(){
    	comboChlidren('city','county');
    });
    
   
    
    // 门店节点弹框触发事件
    $('#myContent').on('click', 'button', function() {
        var othis = $(this),
            method = othis.data('method');
        if (method === 'addStore') {
        	$("#storeImgUrl").attr("src","");
			$("#jwd").html("");
			$("#storeName").val("");
			$("#userName").val("");
			$("#mobile").val("");
			$("#address").val("");
			$("#province").val("");
    		$("#city").val("");
    		$("#county").val("");
			
            layer.open({
                type: 1,
                title: '新增', // 标题
                skin: 'layui-layer-lan', // 弹框主题
                shade: 0,
                area: '450px', // 宽高
                content: $('#storeTreeBox') // 内容
            });
        } else if (method === 'isUse') {
            $('#chooseState').text($(this).text());
            layer.open({
                type: 1,
                title: '提示', // 标题
                skin: 'layui-layer-lan', // 弹框主题
                shade: 0,
                area: '450px', // 宽高
                content: $('#storeState'), // 内容
                btn: ['确认', '取消'],
                yes: function(index, layero) {

                    // 禁用或启用操作。。。

                    layer.close(index);
                },
                btn2: function(index, layero) {
                    layer.close(index);
                }
            });
        } else if (method != null) {
            store(-1,method); // 门店详细信息
        }
    });

    // 构建门店节点树
    layui.tree({
        elem: '#storeTree', // 指定元素
        click: function(item) { // 点击节点回调
            layer.closeAll();
            $('#storeName_show').text(item.name);
            layer.open({ // 弹框询问门店添加位置
                type: 1,
                title: '提示',
                skin: 'layui-layer-lan', // 弹框主题
                shade: 0,
                area: ['420px', '240px'], // 宽高
                content: $('#storeLocate'),
                btn: ['添加子节点', '上方添加节点', '下方添加节点'],
                yes: function(index, layero) { // 选中在上方添加节点的回调
                	store(item.storeId,"addStore"); // 调用store方法
                    layer.close(index);
                },
                btn2: function(index, layero) { // 选中在下方添加节点的回调
                	store(item.pid,"addStore"); // 调用store方法
                    layer.close(index);
                },
                btn3: function(index, layero) { // 选中在添加子节点的回调
                	store(item.pid,"addStore"); // 调用store方法
                    layer.close(index);
                },
            });
        },
        nodes: getNodes()
    });
    // 弹框：门店详细信息
    function store(pid,temp) {
        layer.open({
            type: 1,
            title: temp == 'view' ? '查看' : temp == 'edit' ? '编辑' : '新增',
            skin: 'layui-layer-lan', // 弹框主题
            shade: 0,
            area: '520px', // 宽高
            content: temp == 'view' ? $('#storeInfoView') : $('#storeInfoEdit'),
            btn: temp == 'view' ? '确定' : '提交',
            btnAlign: 'c', // 按钮居中
            yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                // console.log(layero);
            		if(temp == 'addStore'){// 添加门店
            			addStore(pid,index);
            		}else if(temp == 'edit'){
            			editStore(index);// 门店编辑
            		}else if(temp == 'view'){
            			layer.close(index);
            		}
                //layer.close(index); // 如果设定了yes回调，需进行手工关闭
                inputReset(); // 清空表单
            }
        });
    }
    
    
    function getStoreInfo(){
    	var storeInfoDto ={};
		var isUseable=$('input:radio[name="isEnable"]:checked').val();
		storeInfoDto.storeName = $("#storeName").val();
		storeInfoDto.userName = $("#userName").val();
		storeInfoDto.mobile = $("#mobile").val();
		storeInfoDto.address = $("#address").val();
		storeInfoDto.provinceId=$("#province").find("option:selected").val();
		storeInfoDto.provinceName=$("#province").find("option:selected").text();
		storeInfoDto.cityId=$("#city").find("option:selected").val();
		storeInfoDto.cityName=$("#city").find("option:selected").text();
		storeInfoDto.countyId=$("#county").find("option:selected").val();
		storeInfoDto.countyName=$("#county").find("option:selected").text();
		storeInfoDto.backUrl = $("#storeImgUrl").attr("src");
		storeInfoDto.longitude = $("#slng").val(); 
		storeInfoDto.latitude =$("#slat").val(); 
		if(isUseable == 1){
			storeInfoDto.isUseable =true;
		}else{
			storeInfoDto.isUseable =false;
		}
		return storeInfoDto;
    }
    
    //编辑用户
    function editStore(index){
    	var storeInfoDto =getStoreInfo();
    	var storeId = $("#storeId").val();
    	storeInfoDto.storeId = storeId;
    	var uname = $("#userName").attr("name");
    	var mob = $("#mobile").attr("name");
    	if((storeInfoDto.mobile == mob) && storeInfoDto.userName != uname){
    		layer.msg("未修改联系人电话，不可修改门店联系人！");
    		return;
    	}
    	if(storeInfoDto.cityId == '' || storeInfoDto.cityId == null){
    		layer.msg('城市不能为空！');
    		return;
    	}
    	if(storeInfoDto.provinceId == '' || storeInfoDto.provinceId == null){
    		layer.msg('省份不能为空！');
    		return;
    	}
    	if(storeInfoDto.countyId == '' || storeInfoDto.countyId == null){
    		layer.msg('区县不能为空！');
    		return;
    	}
    	if(storeInfoDto.address == '' || storeInfoDto.address == null){
    		layer.msg('详细地址不能为空！');
    		return;
    	}
    	if(storeInfoDto.longitude =='' || storeInfoDto.latitude == ''){
    		layer.msg('请点击定位按钮');
    		return;
    	}
    	if(!(/^1[34578]\d{9}$/.test(storeInfoDto.mobile))){ 
            layer.msg('联系人电话有误，请重填');
            return;
    	}
    	if(storeInfoDto.mobile ==''  || storeInfoDto.mobile == null){
    		layer.msg('联系人电话不能为空！');
    		return;
    	}
    	if(storeInfoDto.storeName ==''  || storeInfoDto.storeName == null){
    		layer.msg('门店名称不能为空！');
    		return;
    	}
    	if(storeInfoDto.userName ==''  || storeInfoDto.userName == null){
    		layer.msg('门店联系人不能为空！');
    		return;
    	}
    	if(storeInfoDto.backUrl ==''  || storeInfoDto.backUrl == null){
    		layer.msg('图片不能为空！');
    		return;
    	}
    	
    	
		$.ajax({
			url : "/changestorebystoreid",
			type : "post",
			async:false,
			headers:{
				  "Authorization":localStorage.token
				 },
				
			data : JSON.stringify(storeInfoDto),
			contentType:"application/json;charset=UTF-8",
	    	success : function(data){
	    		if(0 == data.errcode){
	    			layer.msg("编辑成功！");
	    			layer.close(index);
	    			window.location.reload();
	    		}
	    		
	    	},
	    	error:function(data){
	    		layer.msg('编辑失败！');
	    		
	    	}
			
			
		});
    }
    
    function addStore(pid,index){
    	var storeInfoDto =getStoreInfo();
    	storeInfoDto.pid = pid;
    	if(storeInfoDto.cityId == '' || storeInfoDto.cityId == null){
    		layer.msg('城市不能为空！');
    		return;
    	}
    	if(storeInfoDto.provinceId == '' || storeInfoDto.provinceId == null){
    		layer.msg('省份不能为空！');
    		return;
    	}
    	if(storeInfoDto.countyId == '' || storeInfoDto.countyId == null){
    		layer.msg('区县不能为空！');
    		return;
    	}
    	if(storeInfoDto.address == '' || storeInfoDto.address == null){
    		layer.msg('详细地址不能为空！');
    		return;
    	}
    	if(storeInfoDto.longitude =='' || storeInfoDto.latitude == ''){
    		layer.msg('请点击定位按钮');
    		return;
    	}
    	if(!(/^1[34578]\d{9}$/.test(storeInfoDto.mobile))){ 
            layer.msg('联系人电话有误，请重填');
            return;
    	}
    	if(storeInfoDto.mobile ==''  || storeInfoDto.mobile == null){
    		layer.msg('联系人电话不能为空！');
    		return;
    	}
    	if(storeInfoDto.storeName ==''  || storeInfoDto.storeName == null){
    		layer.msg('门店名称不能为空！');
    		return;
    	}
    	if(storeInfoDto.userName ==''  || storeInfoDto.userName == null){
    		layer.msg('门店联系人不能为空！');
    		return;
    	}
    	if(storeInfoDto.backUrl ==''  || storeInfoDto.backUrl == null){
    		layer.msg('图片不能为空！');
    		return;
    	}
    	if(storeInfoDto.isUseable != true && storeInfoDto.isUseable != false){
    		layer.msg('是否启用不能为空！');
    		return;
    	}
		$.ajax({
			url : "/insertstore",
			type : "post",
			async:false,
			data : JSON.stringify(storeInfoDto),
			headers:{
				  "Authorization":localStorage.token
				 },
			contentType:"application/json;charset=UTF-8",
	    	success : function(data){
	    		layer.msg("添加成功!");
	    		layer.close(index);
	    		window.location.reload();
	    	},
	    	error : function(data){
	    		layer.msg("添加失败！");
	    	}
			
		});
    }
    // 门店图片弹框
    $('#seePic').click(function() {
    	layer.open({
    		type: 1,
    		title: false, // 标题
    		shade: 0,
    		id:'pic',
    		skin: 'layui-layer-nobg', //没有背景色
    		content: $('#storeImage'),
    		success: function(layero, index) {
    		$(layero).find('.layui-layer-content').css('max-height', '500px').css('max-width', '700px');
    		}
    		});
    });
    // 清空表单
    function inputReset() {
        $('#storeForm')[0].reset();
    }
    
    findStore(1);// 初始化加载门店信息
    $("#searchStore").on('click',function(){
    	findStore(1);
    });
    
	function findStore(pageIndex){
		$.ajax({
    	url : "/findstoreinfo",
		type : "get",
		async:false,
		data : { "storeName":$("#storeName_search").val(),"userName":$("#userName_search").val(),
				 "mobile":$("#mobile_search").val(), 
				 "provinceId":$("#province_search").val(),
				 "cityId":$("#city_search").val(),
				 "countyId":$("#county_search").val(), 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize},
    	success : function(data){ 
    		comboTable(data,pageIndex);
    		}
    }); 
}
	
	$("#storeImg").on('change',function(){
		var formObj =$("#img_form"); // 上传 form
		var imgObj=$("#storeImg")[0]; // 上传 图片对象
		var getImgUrl = uploadImg(formObj,imgObj); // 可以加上图片的显示位置
		if(getImgUrl != null && getImgUrl != ''){
			layer.msg("上传成功!");
			$("#storeImgUrl").attr("src",getImgUrl);
		}else{
			layer.msg("上传失败！");
		}
	});
	
function comboTable(res,pageIndex){
		var list = res.result;
		var tb_html="";
		var store_tb = $("#store_tb");
		for(i=0;i<list.length;i++){
			var tr_html=list[i];
			var status = "启用";
			if(tr_html.isUseable){
				status = "禁用";
			}
			tb_html += '<tr><td>'+tr_html.storeName+'</td>'+
						'<td>'+tr_html.userName+'</td>'+
						'<td>'+tr_html.mobile+'</td>'+
						'<td>'+tr_html.address+'</td>'+
						'<td>('+tr_html.longitude+','+tr_html.latitude+')</td>'+
						'<td>'+
						'<button data-method="view" name="'+tr_html.storeId+'" class="layui-btn sshow" >查看</button> '+
						'<button data-method="edit" name="'+tr_html.storeId+'" class="layui-btn sedit" >编辑</button> ';
			if(tr_html.isUseable){
				tb_html +='<button  name="'+tr_html.storeId+'" class="layui-btn layui-btn-normal sea">'+status+'</button> </td></tr>';
			}else{
				tb_html +='<button  name="'+tr_html.storeId+'" class="layui-btn layui-btn-primary sea">'+status+'</button> </td></tr>';
			}
		}
		store_tb.html(tb_html);
		initPage(res.totalCount,pageIndex);
	}

// 编辑接口
$("body").on('click','.sedit',function(){
	$("#storeId").val($(this).attr("name"));
	var newAddress = "";
	$.ajax({
    	url : "/getstorebystoreid",
		type : "get",
		data : {"storeId":$(this).attr("name")},
    	success : function(data){
    		var result = data.result;
    		$("#storeName").val(result.storeName);
    		$("#userName").val(result.userName);
    		$("#userName").attr("name",result.userName);
    		$("#mobile").val(result.userMobile);
    		$("#mobile").attr("name",result.userMobile);
    		$("#province").val(result.provinceId);
    		comboChlidren('province','city');
    		$("#city").val(result.cityId);
    		comboChlidren('city','county');
    		$("#county").val(result.countyId);
    		$("#address").val(result.address);
    		$("#jwd").html("("+result.latitude+","+result.longitude+")");
    		$("#storeImgUrl").attr("src",result.backUrl);
    		$("#slat").val(result.latitude);
    		$("#slng").val(result.longitude);
    		var h = "";
    		
    		if(result.isUseable){
    			h ='<input type="radio" name="isEnable" value="1" checked="checked">是'+
                    '<input type="radio" name="isEnable" value="0" >否';
    		}else{
    			h ='<input type="radio" name="isEnable" value="1" >是'+
                '<input type="radio" name="isEnable" value="0" checked="checked" >否';
    		}
    		$("#state_s").html(h);
    		
    		}
		
    }); 
	

});

// 查看接口
$("body").on('click','.sshow',function(){
	$.ajax({
    	url : "/getstorebystoreid",
		type : "get",
		data : {"storeId":$(this).attr("name")},
    	success : function(data){
    		var result = data.result;
    		$("#storeName_shows").html(result.storeName);
    		$("#userName_show").html(result.userName);
    		$("#mobile_show").html(result.userMobile);
    		/*
			 * $("#province").val(result.provinceId);
			 * comboChlidren('province','city'); $("#city").val(result.cityId);
			 * comboChlidren('city','county');
			 * $("#county").val(result.countyId);
			 */
    		console.log(result);
    		$("#address_show").html(result.provinceName+result.cityName+result.countyName+result.address);
    		$("#jwd_show").html("("+result.latitude+","+result.longitude+")");
    		$("#img_show").attr("src",result.backUrl);
    		if(result.isUseable){
    			$("#isuse_show").html("是")
    		}else{
    			$("#isuse_show").html("否")
    		}
    		}
    }); 
});

// 编辑状态
$("body").on('click','.sea',function(){
	var obj = $(this);
	$.ajax({
    	url : "/storeisuseable",
		type : "post",
		data : {"storeId":obj.attr("name")},
    	success : function(data){
    		layer.msg("修改成功");
			let content = obj.text();
			if(content == '禁用'){
				obj.attr("class","layui-btn layui-btn-primary sea");
				obj.text("启用");
			}else{
				obj.attr("class","layui-btn layui-btn-normal sea");
				obj.text("禁用");
			}
    		},
    		error : function(data){
    			layer.msg("修改失败！");
    		}
    });
});

/**
 * 初始化分页 
 * @param totalCount 总页数
 * @param pageIndex  页面索引
 * @returns
 */
function initPage(totalCount,pageIndex){
	if(totalCount<=pageSize) totalCount=pageSize+1;
	// page
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
				findStore(obj.curr);
			}
		}
	})
}

function getNodes(){
	var nodes =[];
	$.ajax({
		url : "/findstore",
		type : "post",
		async : false,
		data : {},
    	success : function(data){
    		var result = data.result;
    		nodes = result;
    	}
		
	});
	return nodes;
	
}

function getlocation(adder,obj){
	AMap.plugin('AMap.Geocoder',function() {
		var geocoder = new AMap.Geocoder({
			city: "全国", //城市，默认：“全国”
			radius:500
		});
		
	geocoder.getLocation(adder, function(status, result) {
		if(status == 'complete' && result.geocodes.length) {
			var lo = (result.geocodes)[0].location;
			obj.html("("+lo.lat+","+lo.lng+")");
			$("#slat").val(lo.lat);
			$("#slng").val(lo.lng);
		} else {
				layer.msg("解析失败");
		}
	})

});
	
}


$("#dw").on('click',function(){
	var provinceName=$("#province").find("option:selected").text();
	var cityName=$("#city").find("option:selected").text();
	var countyName=$("#county").find("option:selected").text();
	var address = $("#address").val();
	var obj = $("#jwd");
	var dw = provinceName+cityName+countyName+address;
	getlocation(dw,obj);
});

});