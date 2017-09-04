layui.use(['layer', 'tree'], function() {
    var layer = layui.layer,
        $ = layui.jquery,
        title;

    // 商品分类节点弹框触发事件
    layui.tree({
        elem: '#storeTree', //指定元素
        click: function(item) { //点击节点回调
            console.log('111111', item);
            categoriesEdit(item);
        },
        nodes: []
    });

    // 弹框：以何种方式添加商品分类及删除提示
    function categoriesEdit(item) {
        $('#categoriesName').text(item.name); // 提示当前选中分类
        layer.open({ // 弹框询问门店添加位置
            type: 1,
            title: '提示',
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: ['300px', '260px'], //宽高
            content: $('#categoriesLocate'),
            btn: ['添加子节点', '添加同级节点', '修改', '删除'],
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) { // 选中上方添加节点的回调
                title = '添加';
                layer.close(index);
                categoriesEditCommon();
            },
            btn2: function(index, layero) { // 选中添加同级节点的回调
                title = '添加';
                layer.close(index);
                categoriesEditCommon();
            },
            btn3: function(index, layero) { // 选中修改的回调
                title = '编辑';
                // 点击修改按钮后，展示当前节点信息：名称、类型
                $('#categoriesInfoEdit input').val('123');
                $('#categoriesInfoEdit select').val('2');
                $('#categoriesInfoEdit input').attr('disabled', 'disabled');
                layer.close(index);
                categoriesEditCommon();
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
            title: title,
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '500px', //宽高
            content: $('#categoriesInfoEdit'),
            btn: ['提交', '取消'],
            yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                $('#storeTree').html('');
                // layer.close(index); //如果设定了yes回调，需进行手工关闭

                if (true) { // 在新增的情况下，如果要添加的的内容已存在，则不可添加且提示用户该分类已存在
                    layer.open({
                        type: 1,
                        title: '提示',
                        skin: 'layui-layer-lan', //弹框主题
                        shade: 0,
                        area: '400px', //宽高
                        content: $('#categoriesAdd'),
                        btn: '确定',
                        yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                            layer.close(index);
                        },
                        end: function() { // 弹出框销毁时清空表单
                            inputReset(); // 清空表单
                        }
                    });
                }
            },
            end: function() { // 弹出框销毁时清空表单
                inputReset(); // 清空表单
            }
        });
    }
    // 重置表单输入框
    function inputReset() {
        $('#categoriesInfoEdit>form')[0].reset();
        $('#categoriesInfoEdit input').removeAttr('disabled');
    }
});