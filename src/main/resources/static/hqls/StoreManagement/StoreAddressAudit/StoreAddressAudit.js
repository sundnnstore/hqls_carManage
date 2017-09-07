layui.use(['layer', 'form', 'laypage'], function() {
    var layer = layui.layer,
        form = layui.form(),
        laypage = layui.laypage,
        $ = layui.jquery;
    
    var pageSize = 10;
    
    findStore(1);// 初始化加载门店信息
    $("#searchStoreInfo").on('click',function(){
    	findStore(1);
    });
    
    
	function findStore(pageIndex){
		$.ajax({
    	url : "/finduserjoininfo",
		type : "get",
		async:false,
		data : { "storeName":$("#storeName").val(),
				"contactName":$("#contactName").val(),
				"contactMobile":$("#contactMobile").val(),
				 "pageIndex":pageIndex,
				 "pageSize":pageSize},
    	success : function(data){ 
    		comboTable(data,pageIndex);
    		}
    }); 
}
	

	
function comboTable(res,pageIndex){
		var list = res.result;
		var tb_html="";
		var store_tb = $("#store_tb");
		for(i=0;i<list.length;i++){
			var tr_html=list[i];
			tb_html += '<tr><td>'+tr_html.storeName+'</td>'+
						'<td>'+tr_html.contactName+'</td>'+
						'<td>'+tr_html.contactMobile+'</td>'+
						'<td>'+tr_html.address+'</td>'+
						'<td>'+tr_html.remark+'</td>'+
						'</tr>';
			
		}
		store_tb.html(tb_html);
		initPage(res.totalCount,pageIndex);
	}




//通过、不通过
$("body").on('click','.pass',function(){
	var ids = $(this).attr("name");
	var arr = new Array();
	arr = ids.split(",");
	var storeId = arr[0];
	var reviewStatus = arr[1];
	$.ajax({
    	url : "/updatereviewstatus",
		type : "post",
		data : {"storeId":storeId,"reviewStatus":reviewStatus},
    	success : function(data){
    		layer.msg("审核成功！");
    		window.location.reload();
    		}
    }); 
})
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

});