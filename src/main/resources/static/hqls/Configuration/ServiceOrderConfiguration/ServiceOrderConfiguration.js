layui.use(['jquery', 'layer','laypage'], function() {
	var $ = layui.jquery;
	var layer = layui.layer;
	var  laypage=layui.laypage;
	var pageSize = 10;
	$('#addService').on('click', function() {
		layer.open({
			type: 1, // 添加一个模板
			title: '添加服务订单配置',
			content: $('#addServicePage'), // 弹出框的内容
			skin: 'layui-layer-lan', // 弹框主题
			area: '800px', // 宽高
			cancel: function() {
				// 右上角关闭的回调
			},
			shade: 0
		})
	})
	
	$("#okadd").on ('click',function(){
		var serviceType = {};
			serviceType.serviceTypeName = $("#service_type_name").val();
			serviceType.serviceTypeContent=$("#service_type_content").val();
			serviceType.serviceAmount=$("#service_amount").val();
			serviceType.url=$("#service_order_url").val();
			//TODO上传功能
			serviceType.isUsable=true;
		$.ajax({
			url : "http://localhost:8881/addservicetype",
			type : "post",
			data:JSON.stringify(serviceType),
			contentType: 'application/json;charset=utf-8',
			success : function(data){
				$("#service_type_name").val("");
				$("#service_type_content").val("");
				$("#service_amount").val("");
				layer.msg("添加成功！");
			},
			error : function(data){
				layer.msg("添加失败！");
			}
		});
	});
	
	
	$('body').on('click','.toView', function() {
		layer.open({
			type: 1, // 添加一个模板
			title: '图片查看',
			content: '<img src="'+$(this).attr("name")+'"/>', // 弹出框的内容
			skin: 'layui-layer-lan', // 弹框主题
			area: '800px', // 宽高
			cancel: function() {
				// 右上角关闭的回调
			},
			shade: 0
		})
	})
	
	
	// 点击上架还是下架
	$('body').on('click','.uORd', function() {
		let flag = changeStatus($(this).attr("name"));
		if(flag == 1){//修改成功
			layer.msg("修改成功");
			let content = $(this).text();
			if(content == '上架'){
				$(this).attr("class","layui-btn  layui-btn-primary uORd");
				$(this).text("下架");
			}else{
				$(this).attr("class","layui-btn layui-btn-normal uORd");
				$(this).text("上架");
			}
		}else{//修改失败
			layer.msg("修改失败");
		}
	})
	getData(1);
	$("#searchService").click(function(){
		getData(1);
	});
	
	function getData(pageIndex){
		$.ajax({
			url : "http://localhost:8881/findservicetypesbytypename",
			type : "get",
			async : false,
			data : {"typeName":$("#service_title").val(),"pageSize":pageSize,"pageIndex":pageIndex},
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
					getData(obj.curr);
				}
			}
		})
	}
	
	
	function comboTable(res,pageIndex){
		var data = res.result;
		var trs = "";
		for(i=0;i<data.length;i++){
			var tr = data[i];
			trs += '<tr>'+
					'<td>'+tr.serviceTypeName+'</td>'+
					'<td>'+tr.serviceTypeContent+'</td>'+
					'<td>'+tr.serviceAmount+'</td>'+
					'<td><button class="layui-btn toView" name="'+tr.url+'">查看</button></td>'+
					'<td>';
			if(tr.isUsable){
				trs += '<button  name="'+tr.serviceTypeId+'" class="layui-btn  layui-btn-primary uORd " >下架</button>';
			}else{
				trs += '<button name="'+tr.serviceTypeId+'" class="layui-btn layui-btn-normal uORd" >上架</button>';
			}
			trs +='</td></tr>'
		}
		$("#service_tb").html(trs);
		initPage(res.totalCount,pageIndex);
	}
	
	$("#service_order_file").on('change',function(){
		upload();
	});
	function upload(){
		var formObj =$("#hq_form"); //上传 form
		var imgObj=$("#service_order_file")[0]; //上传 图片对象
		var getImgUrl = uploadImg(formObj,imgObj); //可以加上图片的显示位置
		if(getImgUrl != null && getImgUrl != ''){
			$("#service_order_url").val(getImgUrl);
			layer.msg("上传成功！");
			$($(".mylayui-img")[0]).attr("src",getImgUrl);
			$($(".mylayui-img")[0]).css("display","block");
		}else{
			layer.msg("上传失败！");
		}
	}
	
	function changeStatus(serviceTypeId){
		let flag = 0;
		$.ajax({
			url : "http://localhost:8881/updateservicetypestatus",
			type : "post",
			async : false,
			data : {"serviceTypeId":serviceTypeId},
			success : function(data){
				flag = 1;
			}
		});
		return flag;
	}
	
})


