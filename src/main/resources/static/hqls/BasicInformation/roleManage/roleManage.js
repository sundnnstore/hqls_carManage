layui.use(['layer', 'laypage'], function() {
    var layer = layui.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    pageSize = 10;
    // 新增和编辑角色信息的弹框
    $('#myContent').on('click', 'button', function() {
        var method = $(this).data('method');
        // layer.closeAll();
        if (method == 'addRole') {
            roleInfoModel('新增', '#roleTreeBox');
        } else if (method == 'edit') { // 如果是编辑操作，需先判断该角色下是否有人员，若有则提示用户是否继续编辑，否则直接进行编辑
            /*
                判断该角色下是否有人员
            */
            if (true) { // 有人员
                layer.open({
                    type: 1,
                    title: '提示', // 标题
                    skin: 'layui-layer-lan', //弹框主题
                    shade: 0,
                    area: '350px', // 宽高
                    content: $('#roleEdit'),
                    btn: ['确认', '取消'],
                    yes: function(index, layero) {
                        roleInfoModel('编辑', '#roleTreeBox');
                        layer.close(index);
                    },
                    btn2: function(index, layero) {
                        layer.close(index);
                    }
                });
            } else {
                roleInfoModel('编辑', '#roleTreeBox');
            }
        } else if (method == 'del') {
            roleInfoModel('提示', '#roleDelete');
        }
    })

    // 角色信息弹框
    function roleInfoModel(title, content) {
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
            	addRole();
                layer.close(index);
            },
            btn2: function(index, layero) { // 取消操作
                layer.close(index);
            }
        });
    }
    
    getData(1);//初始化数据
    
    function addRole(){
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
    		url : "http://localhost:8881/addrole",
    		type : "post",
    		async : false,
    		data : JSON.stringify(role),
    		contentType :'application/json;charset=UTF-8',
    		success : function(data){
    			layer.msg('添加成功！');
    		},
    		error : function(data){
    			layer.msg('编辑失败！')
    		}
    	});
    }
    
	function getData(pageIndex){
		$.ajax({
			url : "http://localhost:8881/findroles",
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
                    '<button data-method="edit" class="layui-btn layui-btn-normal">编辑</button>'+
                    '<button data-method="del" class="layui-btn layui-btn-danger">删除</button>'+
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
		url : "http://localhost:8881/findallauthoritys",
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