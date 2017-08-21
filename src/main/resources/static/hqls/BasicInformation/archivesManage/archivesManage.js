layui.use(['laypage', 'layer', 'jquery'], function() {
    var $ = layui.jquery,
        laypage = layui.laypage,
        layer = layui.layer;

    var pageSize = 10;
    // 新增/编辑档案信息弹框
    $('#myContent').on('click', 'button', function() {
        var othis = $(this), // 当前按钮元素
            method = othis.data('method'),
            title = '',
            content = '';
        layer.closeAll();
        if (method == 'addArchives') {
            title = '新增';
            content = '#archivesBox';
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
            area: '350px', // 宽高
            content: $(content),
            btn: title == '提示' ? ['确认', '取消'] : ['提交', '取消'],
            yes: function(index, layero) { // 提交或确认操作, othis代表当前点击的按钮元素
                /*
                    此处回调就有四种操作：新增，编辑，启/禁用，重置密码，可根据title值区分（也可自行寻找方法来处理）
                */
                //  根据启/禁用操作后的结果来决定是否切换按钮以及提示
                if (title == '提示') {
                    if (othis.text() === '启用') {
                        if (true) { //启用成功
                            othis.removeClass('layui-btn-primary').addClass('layui-btn-warm');
                            othis.text('禁用');
                        } else { //启用失败
                            layer.msg('启用失败');
                        }
                    } else {
                        if (true) { //禁用成功
                            othis.removeClass('layui-btn-warm').addClass('layui-btn-primary');
                            othis.text('启用');
                        } else { //禁用失败
                            layer.msg('禁用失败');
                        }
                    }
                }
                layer.close(index);
            },
            btn2: function(index, layero) { // 取消操作
                layer.close(index);
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
    		url: 'http://localhost:8881/findallrole',
    		type: 'GET',
    		async: false,
    		success: function(data) {
    			var html = "";
    			html += `<option value="">全部</option>`;
    			html += generatorRole(data.result);
    			$('#role').html(html);
    			$('#add_role').html(`<option value=""></option>` + generatorRole(data.result));
    		}
    	});
    }
    function generatorRole(res) {
    	var html = "";
    	for (var role in res) {
			html += `<option value="${role.key}">${role.value}</option>`;
		}
    	return html;
    }
    initRole();
    
    /**
     * 查询档案
     * @param pageIndex
     * @returns
     */
    function search(pageIndex) {
    	var roleId = $('#role').val(),
    		userName = $('#name').val(),
    		mobile = $('#mobile').val();
    	var	url = 'http://localhost:8881/findusers';
    	url += `?pageIndex=${pageIndex}
    			&pageSize=${pageSize}
    			&roleId=${roleId}
    			&userName=${userName}
    			&mobile=${mobile}`;
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
    	for (var user in obj.result) {
    		html += `<tr>
    				<td>${user.userName}</td>
    				<td>${user.mobile}</td>
    				<td>`;
    		for (var role in user.roles) {
    			html += `${role.roleName}，`;
    		}
    		html = html.substring(0, html.length-1);
    		html += `</td>
    				 <td class="operation" data-id=${user.userId}>
                        <button id="edit" data-method="edit" class="layui-btn layui-btn-normal">编辑</button>
                        <button id="isUse" data-method="isUse" class="layui-btn layui-btn-warm">禁用</button>
                        <button id="reset" data-method="reset" class="layui-btn layui-btn-danger">重置密码</button>
                     </td></tr>`;
    	}
    	$('#user_list').html(html);
    	initPage(obj.totalCount, pageIndex);
    }
    
    /**
     * 新增
     */
    $('body').on('click', '.layui-layer-btn0', function() {
    	var roleIds = $('#add_role').val();
    		Param = {};
    	Param.mobile = $('#mobile').val();
    	Param.userName = $('#name').val();
    	if (!roleIds) {
    		layer.msg('请添加角色');
    		return;
    	}
    	for (var id in roleIds) {
    		Param.roles = {roleId: id};
    	}
    	$.ajax({
    		url: 'http://localhost:8881/adduser',
    		header: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm8iOiJscyIsImNsdCI6IndlYiIsIm9zIjoiMS4wIiwiZGlkIjoiMzQ4YzAzZTMtY2NhMC00MTJkLWJlMGEtZTg1MmU4MDU2NGUzIiwiaWQiOiI4NSJ9.Vh7iLY4yh66X3UlHlNWHkTZV_PTRuc6rLmQodcKuH2I',
    		type: 'POST',
    		data: Param,
    		async: false,
    		success: function(data) {
    			if (data.errmsg == 'success') {
    				layer.msg('添加成功');
    			} else {
    				layer.msg('添加失败');
    			}
    		}
    	});
    });
    
    
    
});