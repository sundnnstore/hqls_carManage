layui.use(['layer', 'jquery', 'element', 'laydate'], function() {
    var url="welcome.html";
    if (window.sessionStorage.getItem('url')) {
        url = window.sessionStorage.getItem('url');
    } 

    var $ = layui.jquery;

    var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块
    myloadNewpage(url)
        //监听导航点击
    element.on('nav(test)', function(elem) {
        //通过url更改页面
        window.sessionStorage.setItem('url', $(elem[0]).attr('name'));
        myloadNewpage($(elem[0]).attr('name'))
    });

    //页面切换方法
    function myloadNewpage(url) {
        //不存入缓存
        $.ajaxSetup({
                cache: false
            })
            //加载页面
        $('#myContent').load(url);
    }
});