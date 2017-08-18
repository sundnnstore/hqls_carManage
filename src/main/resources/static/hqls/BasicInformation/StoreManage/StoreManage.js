layui.use(['form', 'layer','laypage'], function() {
    var form = layui.form(),
        layer = layui.layer,
         laypage=layui.laypage,
    	 pageSize = 10,
        $ = layui.jquery;
    var active = {
        view: function(othis) {
            var type = othis.data('type'),
                text = othis.text();
            layer.open({
                type: 2,
                area: ['450px', '500px'], // 宽高
                title: '查看', // 标题
                id: 'HQ' + type, //防止重复弹出
                content: 'BasicInformation/StoreManage/storeModel/storeModel.html', // 内容
                btn: '确定',
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                    console.log(index, layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        },
        edit: function(othis) {
            var type = othis.data('type'),
                text = othis.text();
            layer.open({
                type: 2,
                area: ['450px', '500px'], // 宽高
                title: '编辑', // 标题
                id: 'HQ' + type, //防止重复弹出
                content: "BasicInformation/StoreManage/storeModel/storeModel.html?options='edit'", // 内容
                btn: '确定',
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                    console.log(layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        }
    }
    $('.layui-table').on('click', 'a', function() {
        var othis = $(this),	
            method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
    
    findStore(1);
	$("#searchStore").click(function(){
		findStore(1);
	});
	
	function findStore(pageIndex){
		$.ajax({
    	url : "http://localhost:8881/findstoreinfo",
		type : "get",
		data : { "storeName":$("#storeName").val(),"userName":$("#userName").val(),
				 "userMobile":$("#userMobile").val(), 
				 "provinceId":$("#province").val(),
				 "cityId":$("#city").val(),
				 "countyId":$("#county").val(), 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize},
				
		
    	success : function(data){
    		
    		comboTable(data,pageIndex);
    		}
    		
    }); 
}
	
	function initPage(totalCount,pageIndex){
		//		page
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
						'<a data-method="view">查看</a> '+
						'<a data-method="edit">编辑</a> '+
						'<a data-method="idEnable" onclick="isEnable('+tr_html.storeId+')">'+status+'</a> '+
						'</td></tr>'
				
		}
			store_tb.html(tb_html);
		initPage(res.totalCount,pageIndex);
	}
});