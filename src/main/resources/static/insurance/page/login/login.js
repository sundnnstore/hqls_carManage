layui.use(['layer', 'form'], function() {
    var form = layui.form();

    //监听提交
    form.on('submit(login)', function(data) {
        if (data.field.name === "admin" && data.field.password === "123456") {
            window.sessionStorage.setItem('login', 'success');
            if (data.field.savePwd === "on") {
                window.localStorage.setItem('name', data.field.name);
                window.localStorage.setItem('password', data.field.password);
                window.localStorage.setItem('savePwd', data.field.savePwd);
            } else {
                window.localStorage.clear();
            }
            window.location.href = "../../index.html";
        } else {
            layer.msg("用户名或密码错误！");
        }
        return false;
    });
})