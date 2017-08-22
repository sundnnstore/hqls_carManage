layui.use(['layer', 'tree', 'form', 'laypage'], function() {
    var layer = layui.layer,
        form = layui.form(),
        laypage = layui.laypage,
        $ = layui.jquery,
        first_title,
        second_title = '';

    // 商品分类节点弹框触发事件
    $('#myContent').on('click', 'button', function() {
        var othis = $(this),
            method = othis.data('method');
        first_title = second_title = '';
        if (method == 'addCategories') {
            first_title = '新增';
            second_title = method;
        } else if (method == 'view') {
            first_title = '查看';
        } else if (method == 'edit') {
            first_title = '编辑';
        }
        first_title && layer.open({
            type: 1,
            title: first_title, // 标题
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '450px', // 宽高
            content: method == 'addCategories' ? $('#categoriesTreeBox') : $('#categoriesTreeView'), // 内容
            btn: method == 'addCategories' ? null : '确定'
        });
    });

    // 构建商品分类节点树
    layui.tree({
        elem: '#categoriesTreeBox', //指定元素
        click: function(item) { //点击节点回调
            first_title != '查看' && categoriesEdit(item);
        },
        nodes: [{ //节点数据        // nodes: createTree(),
            name: '我的邮箱',
            id: 2,
            spread: true, // 是否展开，true展开，false收起, 默认false
            children: [{
                name: 'QQ邮箱',
                id: 21,
                spread: true,
                children: [{
                    name: '收件箱',
                    id: 211,
                    children: [{
                        name: '所有未读',
                        id: 2111
                    }, {
                        name: '置顶邮件',
                        id: 2112
                    }, {
                        name: '标签邮件',
                        id: 2113
                    }]
                }, {
                    name: '已发出的邮件',
                    id: 212
                }, {
                    name: '垃圾邮件',
                    id: 213
                }]
            }, {
                name: '阿里云邮',
                id: 22,
                spread: true,
                children: [{
                    name: '收件箱',
                    id: 221
                }, {
                    name: '已发出的邮件',
                    id: 222
                }, {
                    name: '垃圾邮件',
                    id: 223
                }]
            }, {
                name: '收藏夹',
                id: 3,
                spread: true,
                children: [{
                    name: '爱情动作片',
                    id: 31,
                    alias: 'love'
                }, {
                    name: '技术栈',
                    id: 12,
                    children: [{
                        name: '前端',
                        id: 121
                    }, {
                        name: '全端',
                        id: 122
                    }]
                }]
            }]
        }]
    });
    var createTree = function(node, start) {
        node = node || function() {
            var arr = [];
            arr.push({
                name: '1'.toString().replace(/(\d)/, '$1$1$1$1$1$1$1$1$1'),
                spread: true,
            });
            return arr;
        }();
        start = start || 1;
        layui.each(node, function(index, item) {
            if (start < 10 && index < 9) {
                var child = [{
                    name: (1 + index + start).toString().replace(/(\d)/, '$1$1$1$1$1$1$1$1$1')
                }];
                node[index].children = child;
                node[index].spread = true;
                createTree(child, index + start + 1);
            }
        });
        return node;
    };
    layui.tree({
        elem: '#categoriesTreeView', //指定元素
        click: function(item) {
            first_title != '查看' && categoriesEdit(item);
        },
        nodes: createTree()
    });
    // 弹框：以何种方式添加商品分类及删除提示
    function categoriesEdit(item) {
        $('#categoriesName').text(item.name);
        layer.open({ // 弹框询问门店添加位置
            type: 1,
            title: '提示',
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: ['300px', '260px'], //宽高
            content: $('#categoriesLocate'),
            btn: ['添加子节点', '上方添加节点', '下方添加节点', '删除'],
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) { // 选中在上方添加节点的回调
                layer.close(index);
                categoriesEditCommon(); // 调用store方法
            },
            btn2: function(index, layero) { // 选中在下方添加节点的回调
                layer.close(index);
                categoriesEditCommon(); // 调用store方法
            },
            btn3: function(index, layero) { // 选中在添加子节点的回调
                layer.close(index);
                categoriesEditCommon(); // 调用store方法
            },
            btn4: function(index, layero) { // 选中删除的回调
                layer.close(index);
                var temp = 'del';
                /*
                    删除操作。。。
                    
                    是否可以删除？
                    是   temp='del'，为测试效果默认可以删除
                    否   temp=''                    
                */
                layer.open({
                    type: 1,
                    title: temp == 'del' ? '删除' : '提示',
                    skin: 'layui-layer-lan', //弹框主题
                    shade: 0,
                    area: '400px', //宽高
                    content: temp === 'del' ? $('#categoriesDelete') : $('#categoriesNotDel'),
                    btn: temp === 'del' ? ['确定', '取消'] : '确定',
                    btnAlign: 'c', //按钮居中
                    yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                        // console.log(layero);
                        layer.close(index); //如果设定了yes回调，需进行手工关闭
                    },
                    btn2: function(index, layero) {
                        // console.log(index);
                        layer.close(index);
                    }
                });
            },
        });
    }
    // 商品分类详细信息
    function categoriesEditCommon() {
        layer.open({
            type: 1,
            title: second_title == 'edit' ? '编辑' : '新增',
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '500px', //宽高
            content: second_title == 'view' ? $('#storeInfoView') : $('#categoriesInfoEdit'),
            btn: second_title == 'view' ? '确定' : '提交',
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                // console.log(layero);
                layer.close(index); //如果设定了yes回调，需进行手工关闭
                inputReset(); // 清空表单

                // do something

                // 在新增的情况下，如果要添加的的内容已存在，则提示用户已存在
                if (second_title != 'view' && false) { // 第一参数表明是否新增，第二参数表明是否已存在
                    layer.open({
                        type: 1,
                        title: '提示',
                        skin: 'layui-layer-lan', //弹框主题
                        shade: 0,
                        area: '400px', //宽高
                        content: $('#categoriesAdd'),
                        btn: '确定',
                        btnAlign: 'c', //按钮居中
                        yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                            layer.close(index);
                        }
                    });
                }
            }
        });
    }
    // 重置表单输入框
    function inputReset() {
        $('#categoriesInfoEdit>form')[0].reset();
    }

    // 分页条
    laypage({
        cont: 'paging',
        pages: 100, //总页数
        groups: 5, //连续显示分页数
        skip: true, //跳转到某页
        jump: function(obj, first) { //触发分页后的回调: obj(object)包括了分页的所有配置信息,first(Boolean)检测页面是否初始加载,可避免无限刷新。
            var curr = obj.curr; //得到了当前页，用于向服务端请求对应数据
        }
    });
});