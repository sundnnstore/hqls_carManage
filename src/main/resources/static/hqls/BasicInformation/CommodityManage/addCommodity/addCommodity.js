layui.use(['form', 'upload'], function() {
    var form = layui.form(); //只有执行了这一步，部分表单元素才会修饰成功
    var $ = layui.jquery;
    // var imgId;
    // $('.uploadImg').on('click', '.layui-upload-file', function() {
    //     console.log(this);
    //     console.log(this.id);
    //     console.log($(this)[0].src);
    //     // imgId = elem.target.id;
    //     $(this).attr('src', '../../../images/icon1.jpg');
    // });
    layui.upload({
        url: '', //上传接口
        elem: '#commodityImg1', //指定第一个图片上传框
        method: 'get', //上传接口的http类型            
        success: function(res) { //上传成功后的回调
            commodityImg1.src = res.url; // 将上传成功的图片展示到页面第一个图片上传框上
        }
    });

    layui.upload({
        url: '', //上传接口
        elem: '#commodityImg2', //指定第二个图片上传框
        method: 'get', //上传接口的http类型            
        success: function(res) { //上传成功后的回调
            commodityImg2.src = res.url; // 将上传成功的图片展示到页面第二个图片上传框上
        }
    });
    layui.upload({
        url: '', //上传接口
        elem: '#commodityImg3', //指定第三个图片上传框
        method: 'get', //上传接口的http类型            
        success: function(res) { //上传成功后的回调
            commodityImg3.src = res.url; // 将上传成功的图片展示到页面第三个图片上传框上
        }
    });
    layui.upload({
        url: '', //上传接口
        elem: '#commodityImg4', //指定第四个图片上传框
        method: 'get', //上传接口的http类型            
        success: function(res) { //上传成功后的回调
            commodityImg4.src = res.url; // 将上传成功的图片展示到页面第四个图片上传框上
        }
    });
    layui.upload({
        url: '', //上传接口
        elem: '#commodityImg5', //指定第五个图片上传框
        method: 'get', //上传接口的http类型            
        success: function(res) { //上传成功后的回调
            commodityImg5.src = res.url; // 将上传成功的图片展示到页面第五个图片上传框上
        }
    });

    // 在商品参数项末尾新加一行参数输入框
    $('#addOption').click(function() {
        var html = `
            <tr>
                <td><input type="text" name="" placeholder="请输入参数名称"></td>
                <td><input type="text" name="" placeholder="请输入参数内容"></td>
                <td><input type="text" name="" placeholder="请输入参数名称"></td>
                <td><input type="text" name="" placeholder="请输入参数内容"></td>
            </tr>
        `;
        $('.layui-table tbody').append(html);
    })
});