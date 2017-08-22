layui.use(['laypage', 'layer', 'jquery'], function() {
    var $ = layui.jquery,
        laypage = layui.laypage,
        layer = layui.layer;

    var pageSize = 10;
    // 新增/编辑档案信息弹框
    $('.archives').on('click', 'button', function() {
        var othis = $(this), // 当前按钮元素
            method = othis.data('method'),
            title = '',
            content = '';
        if (method == 'addArchives') {
            title = '新增';
            content = '#archivesBox';
            $('input[name=add_name]').val('');
			$('input[name=add_mobile]').val('');
			$('#roleName').attr('value', '');
        } else if (method == 'edit') {
            title = '编辑';
            content = '#archivesBox';
        } else if (method == 'isUse') {
            title = '提示'; // 启/禁用
            content = '#archivesState';
            $('#chooseState').text($(this).text());
        } else if (method == 'reset') {
            title = '重置密码';
            content = '#archivesReset';
        }
        title && layer.open({
            type: 1,
            title: title, // 标题
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '380px', // 宽高
            content: $(content),
            btn: title == '提示' ? ['确认', '取消'] : ['提交', '取消'],
            yes: function(index, layero) { // 提交或确认操作, othis代表当前点击的按钮元素
                /*
                    此处回调就有四种操作：新增，编辑，启/禁用，重置密码，可根据title值区分（也可自行寻找方法来处理）
                */
                //  根据启/禁用操作后的结果来决定是否切换按钮以及提示
                if (title == '提示') {
	                /**   
				     * 启用/禁用操作
				     * @returns
				     */
			    	$.ajax({
			    		url: '/updateuserstatus?userId=' + othis.val(),
			    		type: 'GET',
			    		async: false,
			    		success: function(data) {
			    			if (data.errmsg == 'success') {
			    				if (othis.text() === '启用') {
			    					othis.removeClass('layui-btn-primary').addClass('layui-btn-warm');
			    					othis.text('禁用');
								} else {
									othis.removeClass('layui-btn-warm').addClass('layui-btn-primary');
									othis.text('启用');
								}
							} else {
								layer.msg('操作失败');
							}
			    		}
			    	});
                } else if (title == '新增' || title == '编辑') {
                	var zTree = $.fn.zTree.getZTreeObj("roleTree"),
                	nodes = zTree.getSelectedNodes(),
                	roles = [];
                	nodes.sort(function compare(a, b) { return a.id - b.id; });
                	// 组装角色Id
                	for (var i = 0, l = nodes.length; i < l; i++) {
                		var role = {roleId: nodes[i].id};
                		roles.push(role);
                	}
                	var	Param = {};
                	Param.mobile = $('input[name=add_mobile]').val();
                	Param.userName = $('input[name=add_name]').val();
                	Param.roles = roles;
                	var url = "";
                	if (title == '编辑') {
                		Param.userId = userId;
                		console.log(Param.userId);
                		url = "/updateuser";
                	} else {
                		url = '/adduser';
                	}
                	var userDto = JSON.stringify(Param);
                	$.ajax({
                		url: url,
                		headers: {"Authorization": localStorage.token},
                		type: 'POST',
                		contentType: "application/json; charset=utf-8",
                		data: userDto,
                		async: false,
                		success: function(data) {
                			if (data.errmsg == 'success') {
                				layer.msg(data.result);
                			} else {
                				layer.msg('添加失败');
                			}
                			search(1);
                			$('input[name=add_mobile]').attr('readonly', false);// 修改完成将input的只读状态改回可编辑
                		}
                	});
                } else if (title == '重置密码') {
                	$.ajax({
                		url: 'http://42.159.202.20:11111/admin/resetpwd/'+ othis.val() +'?projectName=ls',
                		headers: {'Authorization': localStorage.token},
                		type: 'PUT',
                		contentType: "application/json; charset=utf-8",
                		success: function(data) {
                			layer.msg('密码将以短信形式发送至手机');
                		}
                	});
                }
                layer.close(index);
            },
            btn2: function(index, layero) { // 取消操作
                layer.close(index);
            },
            end: function() {
            	$(content).css('display', 'none');
            }
        });
    });

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
					search(obj.curr);
				}
			}
		})
	}
    
    /**
     * 页面初始化加载角色列表
     * @returns
     */
    function initRole() {
    	$.ajax({
    		url: '/findallrole',
    		type: 'GET',
    		async: false,
    		success: function(data) {
    			var html = "";
    			html += `<option value="">全部</option>`;
    			html += generatorRole(data.result);
    			$('#role').html(html);
    			zNodes = data.result;
    		}
    	});
    }
    function generatorRole(res) {
    	var html = "";
    	res.forEach(function(role, index, res) {
			html += `<option value="${role.id}">${role.name}</option>`;
		});
    	return html;
    }
    initRole();
    
    $('#searchArchives').click(function () {
    	search(1);
    });
    
    /**
     * 查询档案
     * @param pageIndex
     * @returns
     */
    function search(pageIndex) {
    	var roleId = $('#role').val(),
    		userName = $('#name').val(),
    		mobile = $('#mobile').val();
    	var	url = '/findusers';
    	url += `?pageIndex=${pageIndex}&pageSize=${pageSize}&roleId=${roleId}&userName=${userName}&mobile=${mobile}`;
    	$.ajax({
    		url: url,
    		type: 'GET',
    		async: false,
    		success: function (data) {
    			comboTable(data, pageIndex);
    		}
    	});
    }
    search(1);
    
    function comboTable(obj, pageIndex) {
    	var html = "";
    	var data = obj.result;
    	data.forEach(function(user, index, data) {
    		html += `<tr>
    				<td>${user.userName}</td>
    				<td>${user.mobile}</td>
    				<td>`;
    		var roles = user.roles;
    		roles.forEach(function(role, index, roles) {
    			html += `${role.roleName}，`;
    		});
    		html = html.substring(0, html.length-1);
    		html += `  </td>
    				 <td class="operation">
                        <button data-method="edit" value="${user.userId}" class="layui-btn layui-btn-normal edit">编辑</button>
                        <button data-method="isUse" value="${user.userId}" class="layui-btn isUse`;
    		if (user.isUseable) {
    			html += ` layui-btn-warm">禁用`;
    		}
    		else {
    			html += ` layui-btn-primary">启用`;
    		}
            html += 	`</button>
                        <button data-method="reset" value="${user.globalUserId}" class="layui-btn layui-btn-danger reset">重置密码</button>
                     </td></tr>`;
    	});
    	$('#user_list').html(html);
    	initPage(obj.totalCount, pageIndex);
    }
    
    
    
    /**
     * 编辑
     */
    var userId;
    $('body').on('click', '.edit', function () {
    	$('input[name=add_mobile]').attr('readonly',true);// 编辑的时候设置手机号码不可修改
    	userId = $(this).val();
    	$.ajax({
    		url: '/getuserbyuserid?userId=' + userId,
    		type: 'GET',
    		async: false,
    		success: function(data) {
    			$('input[name=add_name]').val(data.result.userName);
    			$('input[name=add_mobile]').val(data.result.mobile);
    			var roles = data.result.roles;
    			var html = "";
    			roles.forEach(function(val, index, roles) {
    				html += `${val.roleName}，`;
    			});
    			html = html.substring(0, html.length-1);
    			$('#roleName').attr('value', html);
    		}
    	});
    });
    
});

// 在角色树中选中角色后写入input中
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("roleTree"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	nodes.sort(function compare(a, b) { return a.id - b.id; });
	for (var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0) v = v.substring(0, v.length - 1);
	$("#roleName").attr('value', v);
}

// 角色树展示与收缩
function showMenu() {
	$("#menuContent").css({ left: "123px", top: "63px" }).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}

// 角色树初始化
$(document).ready(function() {
	$.fn.zTree.init($("#roleTree"), setting, zNodes);
});
// 角色树设置
var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick
		}
};
// 角色树数据源
var zNodes = [];
