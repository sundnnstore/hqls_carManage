layui.use(['tree', 'layer', 'form'], function() {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;
    // option = widows.options;
    // 构建节点树
    layui.tree({
        elem: '#storeTree', //指定元素            
        target: '_blank', //是否新选项卡打开（比如节点返回href才有效）            
        click: function(item) { //点击节点回调
            layer.msg('当前节名称：' + item.name + '<br>全部参数：' + JSON.stringify(item));
            console.log(item);
            // active[option] ? active[option].call(this) : '';
        },
        // nodes: createTree(),
        nodes: [{ //节点
            name: '常用文件夹',
            id: 1,
            alias: 'changyong',
            children: [{
                name: '所有未读（设置跳转）',
                id: 11,
                href: 'http://www.layui.com/',
                alias: 'weidu'
            }, {
                name: '置顶邮件',
                id: 12
            }, {
                name: '标签邮件',
                id: 13
            }]
        }, {
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
            }]
        }]
    });

    // 对门店树的节点进行编辑或新增操作，二次弹框
    var html = `
        <table class="layui-table layui-form storeInfo">
            <!-- input框的name值请根据需要自行设置 -->
            <tbody>
                <tr>
                    <td>门店名称</td>
                    <td><input type="text" name="" class="layui-input"></td>
                </tr>
                <tr>
                    <td>门店联系人</td>
                    <td><input type="text" name="" class="layui-input"></td>
                </tr>
                <tr>
                    <td>联系人电话号码</td>
                    <td><input type="text" name="" class="layui-input"></td>
                </tr>
                <tr>
                    <td>门店地址</td>
                    <td><input type="text" name="" class="layui-input" onchange="areaChange()"></td>
                </tr>
                <tr>
                    <td>门店位置</td>
                    <td>23:393:94</td>
                </tr>
                <tr>
                    <td>图片上传</td>
                    <td>
                        <input type="file" name="" id="storeImg" class="layui-input">
                    </td>
                </tr>
                <tr>
                    <td>是否启用</td>
                    <td>
                        <!-- 单选框的value的值请根据需要重新设置 -->
                        <input type="radio" name="isEnable" value="yes" title="是">
                        <input type="radio" name="isEnable" value="no" title="否">
                    </td>
                </tr>
            </tbody>
        </table>
    `;
    var active = {
        edit: function() {
            layer.open({
                type: 2,
                area: ['400px', '500px'], // 宽高
                title: '编辑', // 标题
                id: 'HQ' + type, //防止重复弹出
                content: html, // 内容
                btn: '提交',
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                    console.log(layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        },
        add: function() {
            layer.open({
                type: 2,
                area: ['400px', '500px'], // 宽高
                title: '添加', // 标题
                id: 'HQ' + type, //防止重复弹出
                content: '', // 内容
                btn: '提交',
                btnAlign: 'c', //按钮居中
                yes: function(index, layero) { // 当前层索引参数（index）、当前层的DOM对象（layero）
                    console.log(layero);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            });
        }
    };
    // 当门店地址更改后，立即调用地图接口返回经纬度并设置到页面上
    function areaChange() {
        console.log($(this));
    }
});