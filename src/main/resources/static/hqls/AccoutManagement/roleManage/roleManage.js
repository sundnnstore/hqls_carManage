layui.use(['layer', 'laypage'], function() {
    var layer = layui.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    pageSize = 10;
    // 新增和编辑角色信息的弹框
    $('.RoleManage').on('click', 'button', function() {
        var method = $(this).data('method');
        // layer.closeAll();
        if (method == 'addRole') {
        	var nodes = getallauths();
        	$.fn.zTree.init($("#roleTree"), setting, nodes);
        	$("#roleName").val("");
            roleInfoModel('新增', '#roleTreeBox',0);
        }else{
        	var roleId = $(this).attr("name");
        	var flag = checkRole(roleId);
        	if(method == 'edit'){
        		$("#roleName").val(($(this).parent().prev().html()));//赋予角色名称
        		//显示权限数据
        		initZtree(roleId);
        	}
    	   if (flag) {
    		   // 有人员
    		   if(method == 'del'){
    			   $("#role_msg").html("不可删除！");
    		   }else{
    			   $("#role_msg").html("是否确认修改？");
    		   }
               layer.open({
                   type: 1,
                   title: '提示', // 标题
                   skin: 'layui-layer-lan', //弹框主题
                   shade: 0,
                   area: '350px', // 宽高
                   content: $('#roleEdit'),
                   btn:method == 'del' ? '确认':['确认', '取消'],
                   yes: function(index, layero) {
                	   layer.close(index);
                	   if(method == 'edit'){
                		   roleInfoModel('编辑', '#roleTreeBox',roleId);
                	   }
                   },
                   btn2: function(index, layero) {
                       layer.close(index);
                   },
                   end:function(){
                	   $('#roleEdit').css("display","none");
                   }
               });
           }else{
        	   if(method == 'edit'){
        		   roleInfoModel('编辑', '#roleTreeBox',roleId);
        	   }else{
        		   roleInfoModel('提示', '#roleDelete',roleId);
        	   }
        	   
           }
        }
        	
    })

    // 角色信息弹框
    function roleInfoModel(title, content,roleId) {
        layer.open({
            type: 1,
            title: title, // 标题
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '350px', // 宽高
            content: $(content),
            btn: title == '提示' ? ['确认', '取消'] : ['提交', '取消'],
            yes: function(index, layero) { // 提交或确认操作, othis代表当前点击的按钮元素
                /*
                    此处回调有三种操作：新增，编辑，删除，可根据title值区分（也可自行寻找方法来处理）
                */
            	if(title == '新增'){
            		addRole(index);
            	}else if(title == '编辑'){
            		editRole(index,roleId);
            	}else{//删除操作
            		delRole(index,roleId);
            	}
            },
            btn2: function(index, layero) { // 取消操作
                layer.close(index);
            }
        });
    }
    
    getData(1);//初始化数据
    
    function checkRole(roleId){
    	var flag = false;
    	$.ajax({
    		url : "/checkrole",
    		type : 'post',
    		async : false,
    		data : {roleId : roleId},
    		success : function(data){
    			if(data.result > 0){
    				flag = true;//角色下面有人员
    			}
    		}
    	});
    	return flag;
    }
    
    function delRole(index,roleId){
    	$.ajax({
    		url : "/delrole",
    		type : "post",
    		async : false,
    		data : {roleId:roleId},
    		success : function(data){
    			layer.msg('删除成功！');
    			layer.close(index);
    			getData(1);
    		},
    		error : function(data){
    			layer.msg(data.responseJSON.errmsg);
    		}
    	});
    }
    
    
    function editRole(index,roleId){
    	var roleName = $("#roleName").val();
    	var authorityIds = getAllCheckedNodes();
    	var role={};
    		role.roleName = roleName;
    		role.authorityIds = authorityIds;
    		role.roleId = roleId;
    	if(roleName == ''){
    		layer.msg('角色名不能为空！');
    		return;
    	}
    	if(authorityIds.length == 0){
    		layer.msg('请勾选菜单！');
    		return;
    	}
    	$.ajax({
    		url : "/editrole",
    		type : "post",
    		async : false,
    		data : JSON.stringify(role),
    		contentType :'application/json;charset=UTF-8',
    		success : function(data){
    			layer.msg('编辑成功！');
    			layer.close(index);
    			getData(1);
    		},
    		error : function(data){
    			layer.msg(data.responseJSON.errmsg);
    		}
    	});
    	
    }
    
    
    function addRole(index){
    	var roleName = $("#roleName").val();
    	var authorityIds = getAllCheckedNodes();
    	var role={};
    		role.roleName = roleName;
    		role.authorityIds = authorityIds;
    	if(roleName == ''){
    		layer.msg('角色名不能为空！');
    		return;
    	}
    	if(authorityIds.length == 0){
    		layer.msg('请勾选菜单！');
    		return;
    	}
    	$.ajax({
    		url : "/addrole",
    		type : "post",
    		async : false,
    		data : JSON.stringify(role),
    		contentType :'application/json;charset=UTF-8',
    		success : function(data){
    			layer.msg('添加成功！');
    			layer.close(index);
    			getData(1);
    		},
    		error : function(data){
    			layer.msg(data.responseJSON.errmsg);
    		}
    	});
    }
    
	function getData(pageIndex){
		$.ajax({
			url : "/findroles",
			type : "get",
			async : false,
			data : {"pageSize":pageSize,"pageIndex":pageIndex},
			success : function(data){
				comboTable(data,pageIndex);
			}
		});
	}
	
	function comboTable(res,pageIndex){
		var data = res.result;
		var trs = "";
		for(i=0;i<data.length;i++){
			var tr = data[i];
			trs += '<tr>'+
                	'<td>'+tr.roleName+'</td>'+
                	'<td>'+
                    '<button data-method="edit" name="'+tr.roleId+'"  class="layui-btn layui-btn-normal rolredit">编辑</button>'+
                    '<button data-method="del" name="'+tr.roleId+'" class="layui-btn layui-btn-danger roledel">删除</button>'+
                	'</td></tr>'
		}
		$("#role_tb").html(trs);
		initPage(res.totalCount,pageIndex);
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
});

$(document).ready(function() {
    $.fn.zTree.init($("#roleTree"), setting, zNodes);
});

var setting = {
    check: {
        enable: true
    },
    data: {
        key: {
            title: "title"
        },
        simpleData: {
            enable: true
        }
    },
    callback: {}
};

var zNodes = getallauths();

function getallauths(){
	var res;
	$.ajax({
		url : "/findallauthoritys",
		type : "get",
		async : false,
		success : function(data){
			res = data.result;
		}
	});
	return res;
}

function getAllCheckedNodes(){
	var checkedNodes = $.fn.zTree.getZTreeObj("roleTree").getCheckedNodes(true);
	var ckNodes = [];
	for(i=0;i<checkedNodes.length;i++){
		var node = checkedNodes[i];
		if(node.pId != null && node.pId != 0){
			ckNodes.push(node.id);
		}
	}
	return ckNodes;
}

function initZtree(roleId){
	var nodes;
	var nocheck="";
	$.ajax({
		url : "/findcheckedauthoritys",
		type : "post",
		async : false,
		data : {roleId : roleId},
		success : function(data){
			nodes = data.result;
			for(i=0;i<nodes.length;i++){
				var node = nodes[i];
				if(node.pId != null && node.pId != 0){
					if(!(node.checked)){
						nocheck += node.pId+",";
					}
				}
			}
			for(j=0;j<nodes.length;j++){
				var node = nodes[j];
				if(node.pId ==0 || node.pId == null){
					if(nocheck.indexOf(node.id) == -1){
						node.checked = true;
					}
				}
			}
		}
	});
	$.fn.zTree.init($("#roleTree"), setting, nodes);
}