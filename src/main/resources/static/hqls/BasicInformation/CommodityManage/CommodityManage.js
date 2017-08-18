layui.use(['layer', 'form', 'laypage'], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage;
		var pageSize = 10;
    // 弹出框
    var title; // 弹出框的标题
    var active = {
        addCommodity: function(othis) {
            var type = othis.data('type'),
                text = othis.text();
            layer.open({
                type: 2,
                area: ['700px', '515px'], // 宽高
                title: title, // 标题
                id: 'HQ' + type, //防止重复弹出
                content: 'BasicInformation/CommodityManage/addCommodity/addCommodity.html', // 内容
                btn: '提交',
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) {
                    console.log(layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        }
    };
    // 触发弹框事件
    $('.commodity').on('click', '#addCommodity', function() {
        var othis = $(this),
            method = othis.data('method');
        if ($(this).text() === '新增') {
            title = '新增';
        } else if ($(this).text() === '查看') {
            title = '查看';
        } else if ($(this).text() === '编辑') {
            title = '编辑';
        }
        active[method] ? active[method].call(this, othis) : '';
    });
    // 上下架事件及结果提示
    $('.layui-table tbody').on('click', '#isUse', function() {
        if ($(this).text() === '上架') {
            /*
                上架操作
            */
            if (true) { //上架成功切换成下架按钮，同时提示用户
                layer.msg('上架成功');
                $(this).removeClass('layui-btn-normal').addClass('layui-btn-primary');
                $(this).text('下架')
            } else { //上架失败不切换按钮，同时提示用户
                layer.msg('上架失败');
            }
        } else {
            /*
                下架操作
            */
            if (true) { //下架成功切换成上架按钮，同时提示用户
                layer.msg('下架成功');
                $(this).removeClass('layui-btn-primary').addClass('layui-btn-normal');
                $(this).text('上架')
            } else { //上架失败不切换按钮，同时提示用户
                layer.msg('下架成功');
            }
        }
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
    
});