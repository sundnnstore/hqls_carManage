function navBar(strData) {
    var data;
    if (typeof(strData) == "string") {
        var data = JSON.parse(strData); //部分用户解析出来的是字符串，转换一下
    } else {
        data = strData;
    }
    var ulHtml = `<ul class="layui-nav layui-nav-tree">`;
    for (var i = 0; i < data.length; i++) {
        if (data[i].spread) { // 默认展开
            ulHtml += `<li class="layui-nav-item layui-nav-itemed">`;
        } else { // 默认不展开
            ulHtml += `<li class="layui-nav-item">`;
        }
        if (data[i].children != undefined && data[i].children.length > 0) { // 含有子级菜单
            ulHtml += `<a href="javascript:;">`;
            if (data[i].icon != undefined && data[i].icon != '') { // 是否含有图标
                if (data[i].icon.indexOf("icon-") != -1) { // class形式的图标  
                    ulHtml += `<i class="iconfonts ${data[i].icon}" data-icon="${data[i].icon}"></i>`;
                } else { // unicode形式的图标
                    ulHtml += `<i class="layui-icon" data-icon="${data[i].icon}">${data[i].icon}</i>`;
                }
            }
            ulHtml += `<cite>${data[i].title}</cite><span class="layui-nav-more"></span></a><dl class="layui-nav-child">`;
            for (var j = 0; j < data[i].children.length; j++) { // 遍历子级菜单项
                if (data[i].children[j].target == "_blank") {
                    ulHtml += `<dd><a href="javascript:;" data-url="${data[i].children[j].href}" target="${data[i].children[j].target}">`;
                } else {
                    ulHtml += `<dd><a href="javascript:;" data-url="${data[i].children[j].href}">`;
                }
                if (data[i].children[j].icon != undefined && data[i].children[j].icon != '') { // 是否含有图标
                    if (data[i].children[j].icon.indexOf("icon-") != -1) { // class形式的图标  
                        ulHtml += `<i class="iconfonts ${data[i].children[j].icon}" data-icon="${data[i].children[j].icon}"></i>`;
                    } else { // unicode形式的图标
                        ulHtml += `<i class="layui-icon" data-icon="${data[i].children[j].icon}">${data[i].children[j].icon}</i>`;
                    }
                }
                ulHtml += `<cite>${data[i].children[j].title}</cite></a></dd>`;
            }
            ulHtml += "</dl>";
        } else { // 不含有子级菜单
            ulHtml += `<a href="javascript:;" data-url="${data[i].href}">`;
            if (data[i].icon != undefined && data[i].icon != '') { // 是否含有图标
                if (data[i].icon.indexOf("icon-") != -1) { // class形式的图标   
                    ulHtml += `<i class="iconfonts ${data[i].icon}" data-icon="${data[i].icon}"></i>`;
                } else { // unicode形式的图标
                    ulHtml += `<i class="layui-icon" data-icon="${data[i].icon}">${data[i].icon}</i>`;
                }
            }
            ulHtml += `<cite>${data[i].title}</cite></a>`;
        }
        ulHtml += `</li>`;
    }
    ulHtml += `</ul>`;
    return ulHtml;
}

// function nav(data){
//     var listHtml;
//     if (data.spread) {
//         listHtml += `<li class="layui-nav-item layui-nav-itemed">`;
//     } else {
//         listHtml += `<li class="layui-nav-item">`;
//     }
//     if(data.children != undefined && data.children.length > 0){

//         `<a href="javascript:;">`
//             `<i class="iconfont icon-text" data-icon="icon-text"></i>`
//             `<cite>文章列表</cite>`
//             `<span class="layui-nav-more"></span>`
//         `</a>`
//         `<dl class="layui-nav-child">`
//             `<dd class="layui-this">`
//                 `<a href="javascript:;" data-url="">`
//                     `<i class="layui-icon" data-icon=""></i>`
//                     `<cite>话题</cite>`
//                 `</a>`
//             `</dd>`
//         `</dl>`
//     }else{
//         `<a href="javascript:;" data-url="page/main.html">`
//             `<i class="iconfont icon-computer" data-icon="icon-computer"></i>`
//             `<cite>首页</cite>`
//         `</a>`
//     }
//     return listHtml + `</li>`;
// }