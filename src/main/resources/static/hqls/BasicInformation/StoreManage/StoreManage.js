layui.use(['layer', 'tree', 'form', 'laypage'], function() {
    var layer = layui.layer,
        form = layui.form(),
        laypage = layui.laypage,
        $ = layui.jquery;
    
    var pageSize = 2;
    
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
    
    findStore(1);//初始化加载门店信息
    
    // 门店节点弹框触发事件
    $('#myContent').on('click', 'button', function() {
        var othis = $(this),
            method = othis.data('method');
        layer.closeAll();
        if (method === 'addStore') {
            layer.open({
                type: 1,
                title: '新增', // 标题
                skin: 'layui-layer-lan', //弹框主题
                shade: 0,
                area: '450px', // 宽高
                content: $('#storeTreeBox') // 内容
            });
        } else if (method === 'isUse') {
            $('#chooseState').text($(this).text());
            layer.open({
                type: 1,
                title: '提示', // 标题
                skin: 'layui-layer-lan', //弹框主题
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
            store(method); // 门店详细信息
        }
    });

    // 构建门店节点树
    layui.tree({
        elem: '#storeTree', //指定元素            
        click: function(item) { //点击节点回调
            layer.closeAll();
            $('#storeName_show').text(item.name);
            layer.open({ // 弹框询问门店添加位置
                type: 1,
                title: '提示',
                skin: 'layui-layer-lan', //弹框主题
                shade: 0,
                area: ['420px', '240px'], //宽高
                content: $('#storeLocate'),
                btn: ['添加子节点', '上方添加节点', '下方添加节点'],
                yes: function(index, layero) { // 选中在上方添加节点的回调
                    layer.close(index);
                    store(item.storeId,""); // 调用store方法
                },
                btn2: function(index, layero) { // 选中在下方添加节点的回调
                    layer.close(index);
                    store(item.pid,""); // 调用store方法
                },
                btn3: function(index, layero) { // 选中在添加子节点的回调
                    layer.close(index);
                    store(item.pid,""); // 调用store方法
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
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '520px', //宽高
            content: temp == 'view' ? $('#storeInfoView') : $('#storeInfoEdit'),
            btn: temp == 'view' ? '确定' : '提交',
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                // console.log(layero);
                layer.close(index); //如果设定了yes回调，需进行手工关闭
                inputReset(); // 清空表单
            }
        });
    }
    // 门店图片弹框
    $('#seePic').click(function() {
        event.stopPropagation();
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shade: [1, 'transparent'],
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: $('#storeImage')
        });
    });
    // 清空表单
    function inputReset() {
        $('#storeForm')[0].reset();
    }
    
    $("#searchStore").on('click',function(){
    	findStore(1);
    });
    
	function findStore(pageIndex){
		$.ajax({
    	url : "http://localhost:8881/findstoreinfo",
		type : "get",
		async:false,
		data : { "storeName":$("#storeName_search").val(),"userName":$("#userName_search").val(),
				 "userMobile":$("#mobile_search").val(), 
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
		var formObj =$("#img_form"); //上传 form
		var imgObj=$("#storeImg")[0]; //上传 图片对象
		var getImgUrl = uploadImg(formObj,imgObj); //可以加上图片的显示位置
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
						'<button data-method="view" class="layui-btn" onclick="getStoreInfo('+tr_html.storeId+')">查看</button> '+
						'<button data-method="edit" class="layui-btn " onclick="changeStore('+tr_html.storeId+')">编辑</button> '+
						'<button  onclick="isEnable('+tr_html.storeId+')" class="layui-btn layui-btn-normal">'+status+'</button> '+
						'</td></tr>'
				
		}
		store_tb.html(tb_html);
		initPage(res.totalCount,pageIndex);
	}


function initPage(totalCount,pageIndex){
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
		url : "http://localhost:8881/findstore",
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
});