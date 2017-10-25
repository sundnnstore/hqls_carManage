layui.use(['jquery', 'layer', 'form', 'laypage', 'upload', 'tree'], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage;
    var pageSize = 10;
    // 弹出框
    var title; // 弹出框的标题
    var active = {

    };
    //车辆参数
    var carParam = {};
    
    
    // 触发弹框事件
    $('.commodity').on('click', 'button', function() {
        var method = $(this).data('method');
        $('.closeBtn').removeAttr('style'); // 移除查看状态下的图片右上角删除样式的隐藏效果

        if (method == 'addCommodity') {
            title = '新增';
            /**
             * 图片上传
             */
        	var picAppend = `
             	<div class="siteUpload">
                     <img id="commodityImgUrl" src="">
                     <div class="layui-box layui-upload-button">
                         <input type="file" name="file" class="commodityImg" class="layui-upload-file" onchange="change(this)" accept="image/*">	                        
                    	 <span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>上传图片</span>
                     </div>
                     <span class="closeBtn" onclick="delImg(this)"><i class="layui-icon">&#x1006;</i></span>
                 </div>`;
        	$(".uploadImg").append(picAppend);  
        	hideLastImgDelIcon();
        	
        	/**
        	 * 显示配件品牌
        	 */
            partsBrand();
            
            /**
             * 车辆
             */
            car();
            
            /**
             * 禁用其他点击按钮
             */
            ButtonForbidden();
      
            
        } else if (method == 'view') {
            title = '查看';
            Detailview($(this).parent().find("#partsId").val());
            forbidden(); //禁用元素
            ButtonForbidden(); //禁用其他按钮
            $('#addOption').css("display", "none"); //隐藏
        } else if (method == 'edit') {
            title = '编辑';
            var partsId = $(this).parent().find("#partsId").val();
            //编辑方法
            Detailview(partsId,method);
            ButtonForbidden();
        }
       
        method && layer.open({
            resize: false,
            type: 1,
            title: title, // 标题
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '810px', // 宽高
            content: $('#commodityBox'), // 内容
            btn: title == '查看' ? ['确定', '取消'] : ['提交', '取消'],
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) {
                if (title != '查看') {
                    //折扣
                    // 校验规格、型号、品牌必填
                    var titleInfo; // 校验提示信
                    if (!$('#partsName').val()) {
                        titleInfo = '请输入配件名称参数，该项必填！';
                        layer.msg(titleInfo);
                        return;
                    } else if (!$("#TheTreeIdOfAdd").val()) {
                        titleInfo = '请选择配件分类，该项必填!';
                        layer.msg(titleInfo);
                        return;
                    } else if (!$('#partsSpec').val()) {
                        titleInfo = '请输入规格参数，该项必填！';
                        layer.msg(titleInfo);
                        return;
                    } else if (!$('#partsModel').val()) {
                        titleInfo = '请输入型号参数，该项必填！';
                        layer.msg(titleInfo);
                        return;
                    } else if (!$('#partsBrandId').val()) {
                        titleInfo = '请选择品牌，,该项必填!';
                        layer.msg(titleInfo);
                        return;
                    } else if (!$('#price').val()) {
                        titleInfo = '请输入价格,该项必填!';
                        layer.msg(titleInfo);
                        return;
                    } else if (!$('#discount').val()) {
                        titleInfo = '请输入折扣,该项必填!';
                        layer.msg(titleInfo);
                        return;
                    } else if (parseFloat($('#discount').val()) > 1 || parseFloat($('#discount').val()) < 0) {
                        titleInfo = '折扣价格,为0-1的数字,该项必填!';
                        layer.msg(titleInfo);
                        return;
                    } else if (!$('#curPrice').val()) {
                        titleInfo = '请输入现价,该项必填!';
                        layer.msg(titleInfo);
                        return;
                    } else if (!$('#partsUnit').val()) {
                        titleInfo = '请输入单位,该项必填!';
                        layer.msg(titleInfo);
                        return;
                    } else {
                    	//价格
                        var pric = parseFloat($('#price').val());
                        var disc = parseFloat($('#discount').val());
                        var curPrice = parseFloat($('#curPrice').val());
                        if (pric * disc != curPrice) {
                            titleInfo = '现价*折扣价格不等于当前价格!';
                            layer.msg(titleInfo);
                            return;
                        }
                        
                        //上传图片
                        if(imgCount()<2){
                        	layer.msg("请上传至少一个图片");
                            return;
                        }
                        
                    }
                }
                switch (title) {
                    case "新增":
                        addPart(index);
                        break;
                    case "编辑":
                        editPart(index);
                        break;
                    case "查看":
                        layer.close(index); //关闭弹框
                        break;
                    default:
                        layer.msg("未知标题");
                        break;
                }
            },
            end: function(index, layero) { //最后统一走的方法
                if (method == 'addCommodity') {
                    afterAddPart();
                } else if (method == 'view') {
                    afterView();
                } else if (method == 'edit') {
                    afterEditPart(); //清空之前数据
                }
                $(".uploadImg").html("");
                $('#commodityBox').css("display", "none"); //隐藏
                $('#addOption').css("display", "block"); //新增按钮显示
                $(".attrExtra").html("");//清空
                clearButtonForbidden(); //启用按钮
                layer.close(index); //关闭弹框
            }
        });
    });

    // 上下架事件及结果提示
    $('.layui-table tbody').on('click', '#isUse', function() {
        var othis = $(this);
        var partsId = $(this).parent().find("#partsId").val();
        $('#chooseState').text(othis.text());
        layer.open({
            type: 1,
            title: '提示', // 标题
            skin: 'layui-layer-lan', //弹框主题
            shade: 0,
            area: '300px', // 宽高
            content: $('#commodityState'), // 内容
            btn: ['确定', '取消'],
            btnAlign: 'c', //按钮居中
            yes: function(index, layero) {
                if (othis.text() === '上架') { //上架操作
                    isUnable(partsId, 1, othis);
                } else { //下架操作
                    isUnable(partsId, 0, othis);
                }
                layer.close(index); //如果设定了yes回调，需进行手工关闭
            },
            btn2: function(index, layero) {
                layer.close(index);
            },
            end: function() {
                $('#commodityState').css("display", "none");
            }
        });
    });

    // 在商品参数项末尾新加一行新的参数框
    $('#addOption').click(function() {
        var html = `
	            <tr class="attrExtra">
	                <td colspan="2" class="paramGroup">
	                    <input type="text" name="" class="layui-input paramName" placeholder="请输入参数名称">
	                    <span></span>
	                    <input type="text" name="" class="layui-input paramContent" placeholder="请输入内容">
	                </td>
	                <td colspan="2" class="paramGroup">
	                    <input type="text" name="" class="layui-input paramName" placeholder="请输入参数名称">
	                    <span></span>
	                    <input type="text" name="" class="layui-input paramContent" placeholder="请输入内容">
	                </td>
	                <td colspan="2" class="paramGroup">
	                    <input type="text" name="" class="layui-input paramName" placeholder="请输入参数名称">
	                    <span></span>
	                    <input type="text" name="" class="layui-input paramContent" placeholder="请输入内容">
	                </td>
	            </tr>
	        `;
        $('.modelTableContainer tbody').append(html);
    });
    
    /**
     * 页面加载默认显示
     */
    getData(1);
    $("#searchCommodity").click(function() {
        getData(1);
    });

    /**
     * 初始化分页 
     * @param totalCount 总页数
     * @param pageIndex  页面索引
     * @returns
     */
    function initPage(totalCount, pageIndex) {
        if (totalCount <= pageSize) totalCount = pageSize + 1;
        //page
        laypage({
            cont: 'pages',
            pages: Math.ceil(totalCount / pageSize), // 总的分页数
            groups: 5, // 展示的页数
            first: 1,
            last: Math.ceil(totalCount / pageSize),
            skip: true,
            curr: pageIndex,
            jump: function(obj, first) {
                //alert("初始化成功"+obj.curr);
                if (!first) {
                    getData(obj.curr);
                }
            }
        });

    }


    /**
     * 获取分页数据
     * @param pageIndex
     * @returns
     */
    function getData(pageIndex) {
        $.ajax({
            url: "/findparts",
            type: "get",
            async: false,
            data: {
                "partsCode": $("#partsCodeQuery").val(),
                "partsTypeId": $("#TheTreeIdOfSearch").val(), //暂无树形结构
                "partsTopTypeId": $("#partsTypeTop").val(),
                "partsName": $("#partsNameQuery").val(),
                "pageSize": pageSize,
                "pageIndex": pageIndex
            },
            success: function(data) {
                comboTable(data, pageIndex);
            },
            error: function(data) {
                alert("页面查询失败");
            }
        });
    }

    /**
     * 
     * @param res 返回的数据 
     * @param pageIndex 显示的页面索引
     * @returns
     */
    function comboTable(res, pageIndex) {
        var data = res.result;
        var trs = "";
        for (i = 0; i < data.length; i++) {
            var tr = data[i];
            trs += '<tr>' +
                '<td>' + tr.partsName + '</td>' +
                '<td>' + tr.partsTopType + '</td>' +
                '<td>' + tr.price + '</td>' +
                '<td>' + tr.curPrice + '</td>' +
                '<td>' + tr.partsCode + '</td>' +
                '<td><button id="view" data-method="view" class="layui-btn view_c">查看</button>' +
                '<button id="edit" data-method="edit" class="layui-btn layui-btn-normal edit_c">编辑</button>' +
                '';
            if (tr.isUsable == 1) {
                $("#isUse").removeClass('layui-btn-warm').addClass('layui-btn-primary');
                trs += '<button id="isUse" class="layui-btn layui-btn-warm isUse_c">下架</button>';
            } else {
                $("#isUse").removeClass('layui-btn-primary').addClass('layui-btn-warm');
                trs += ' <button id="isUse" class="layui-btn layui-btn-primary isUse_c">上架</button>';
            }
            trs += '<input type="hidden" value="' + tr.partsId + '" id="partsId" /></td></tr>'
        }
        $(".parts_info").html(trs);
        initPage(res.totalCount, pageIndex);
    }

    /**
     * 新增商品
     */
    function addPart(index) {
        $.ajax({
            url: '/addparts',
            type: 'POST',
            async: false,
            beforeSend: function(x) {
                x.setRequestHeader("Content-Type", "application/json; charset=utf-8");
            },
            data: requestData(),
            success: function(data) { 
                var pageIndex = parseInt($(".layui-laypage-skip").val());
                $("#TheTreeIdOfSearch").val(0);
                getData(pageIndex);
                layer.msg("添加商品成功");
                layer.close(index); // 如果必填校验出错，此时应不必关闭弹框；此处代码暂定，请根据需要进行修改
            },
            error: function(data) {
                layer.msg("添加商品失败");
            }
        });
    }

    /**
     * 编辑商品
     */
    function editPart(index) {
        //编辑商品
        $.ajax({
            url: "/updateparts",
            type: "POST",
            contentType: "application/json;charset=utf-8",
            async: false,
            data: requestData(),
            success: function(data) {
                var pageIndex = parseInt($(".layui-laypage-skip").val());
//                $("#TheTreeIdOfSearch").val(0);
//                $("#commodityName").attr('placeholder','请选择分类');
                getData(pageIndex);
                layer.msg("编辑商品成功");
                layer.close(index); // 如果必填校验出错，此时应不必关闭弹框；此处代码暂定，请根据需要进行修改
            },
            error: function(data) {
                layer.msg("编辑商品失败" + data);
            }
        });
    }


    /**
     * 上下架
     * @returns
     */
    function isUnable(partsId, isUsable, elem) {
        var Jsonstr = { "partsId": partsId, "isUsable": isUsable };
        Jsonstr = JSON.stringify(Jsonstr);
        $.ajax({
            url: "/updateparts",
            type: "POST",
            contentType: "application/json;charset=utf-8",
            async: false,
            data: Jsonstr,
            success: function(data) {
                if (elem.text() === '上架') { //上架操作
                    elem.removeClass('layui-btn-primary').addClass('layui-btn-warm');
                    elem.text('下架');
                } else { //下架操作
                    elem.removeClass('layui-btn-warm').addClass('layui-btn-primary');
                    elem.text('上架');
                }
                layer.msg("操作成功");
            },
            error: function(data) {
                layer.msg("操作失败");
            }
        });
    }

    /**
     * 查看明细
     * @returns
     */
    function Detailview(partsId,method) {
        $.ajax({
            url: "/getpartsdetail",
            type: "get",
            async: false,
            data: { "partsId": partsId },
            success: function(data) {
                if (data.errcode == 0) {
                    displayHtml(data, data.result.partsBrandId,method);
                } else {
                    layer.msg("undefined");
                }
            },
            error: function(data) {
                layer.msg("查看明细失败返回值" + data.errmsg);
            }
        });
    }
    
    
    /**
     * 显示配件明细数据
     * @param data
     * @returns
     */
    function displayHtml(data, partsBrandId,method) {
        var piclen, attrlen;
        if (data.result.partsPicList == null) {
            piclen = 0; 
        } else {
            piclen = data.result.partsPicList.length;
        }
        if (data.result.partsAttrExtrs == null) {
            attrlen = 0;
        } else {
            attrlen = data.result.partsAttrExtrs.length;
        }
        var pics = ""; //图片
        var attrExtra = ""; //配件的动态属性
        //get data
        $("#partsIdForOper").val(data.result.partsId);
        $("#partsCode").val(data.result.partsCode);
        $("#partsName").val(data.result.partsName);
        $("#partsModel").val(data.result.partsModel);
        $("#partsSpec").val(data.result.partsSpec);
        $("#price").val(data.result.price);
        $("#discount").val(data.result.discount);
        $("#curPrice").val(data.result.curPrice);
        $("#origin").val(data.result.origin);
        $("#partsFactory").val(data.result.partsFactory); 
        $("#shelfLife").val(data.result.shelfLife);
        $("#partsUnit").val(data.result.partsUnit);
        $(".attrExtra").html(""); //清空动态扩展属性的div
        for (var i = 0; i < piclen; i++) {
            pics += `
               <div class="siteUpload">
	                <img id="commodityImgUrl${i}" src="${data.result.partsPicList[i].url}">
	                <div class="layui-box layui-upload-button">
                		<input type="file" name="image" class="commodityImg" class="layui-upload-file" onchange="change(this)" accept="image/*" disabled="disabled">
                		<span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>上传图片</span>
					</div>
            		<span class="closeBtn" onclick="delImg(this)"><i class="layui-icon">&#x1006;</i></span>
    			</div>
            `;
        }
     
        //动态属性追加到指定位置
        $(".uploadImg").prepend(pics);
        if(method == 'edit'){
        	var picAppend = `
	         	<div class="siteUpload">
	                 <img id="commodityImgUrl" src="">
	                 <div class="layui-box layui-upload-button">
	                     <input type="file" name="file" class="commodityImg" class="layui-upload-file" onchange="change(this)" accept="image/*">	                        
	                	 <span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>上传图片</span>
	                 </div>
	                 <span class="closeBtn" onclick="delImg(this)"><i class="layui-icon">&#x1006;</i></span>
	             </div>`;
        	$(".uploadImg").append(picAppend);
        	if(piclen>=5){
		        //隐藏最后一张图片
		        $(".siteUpload:last").hide();
        	}
        	//隐藏最后一个图片的删除按钮
		    hideLastImgDelIcon();  
        }
        
        var flag = 0;
        for (var i = 0; i <= attrlen; i++) {
            //清空动态属性框,然后拼接显示
            if ((flag % 3 == 0 && i > 0) || (flag == attrlen)) {
                $(".attrExtra:last").html(attrExtra);
                $(".attrExtra:last").after("<tr class=\"attrExtra\"></tr>");
                attrExtra = "";
            }
            if (i < attrlen) {
                attrExtra += `
                        <td colspan="2" class="paramGroup">
                            <input type="text" name="" value="${data.result.partsAttrExtrs[i].attrKey}" class="layui-input paramName" placeholder="请输入参数名称">
                            <span></span>
                            <input type="text" name="" value="${data.result.partsAttrExtrs[i].attrValue}" class="layui-input paramContent" placeholder="请输入内容">
                    	</td>
        	        `;
            }
            flag++;
        }

        /**
         * 配件品牌显示
         */
        partsBrand(partsBrandId);

        /**
         * 配件分类
         *
         */
        $("#cName").val(data.result.typeName);
        $("#TheTreeIdOfAdd").val(data.result.partsTypeId);
    
        /**
         * 启用配件车型选择器
         */
    	car();
    	/**
         * 查询已选择配件车型
         * 
         */
        //showPartsCarModel(data.result.partsId);
        
        
        
        /**
         * 上下架
         */
        if (data.result.isUsable == 0) {
            $('input:radio:last').prop('checked', 'checked');
        } else {
            $('input:radio:first').prop('checked', 'checked');
        }

        /**
         * 配件类型
         */
        //$("#partsType").val(data.result.partsType);
        if (data.result.partsType == 1) {
            $("#partsType").find("option[value='1']").prop("selected", true);
        } else if (data.result.partsType == 2) {
            $("#partsType").find("option[value='2']").prop("selected", true);
        }

    }

    /**
     * 查询配件的品牌
     * @param partsBranId  配件对应的ID
     * @returns
     */
    function partsBrand(partsBranId) {
        $.ajax({
            url: "/findpartsbrands",
            type: "get",
            async: false,
            success: function(data) {
                if (partsBranId === undefined) {
                    displayPartsBrand(data);
                } else {
                    displayPartsBrand(data, partsBranId);
                }

            }
        });
    }

    /**
     * 显示配件品牌
     * @returns
     */
    function displayPartsBrand(data, partsBrandId) {
        //清空
        $(".partsBrands").html("");
        var html = `<select class="layui-select" id="partsBrandId">`;
        var brand = data.result == null ? 0 : data.result.length;
        for (var i = 0; i < brand; i++) {
            if (partsBrandId == data.result[i].partsBrandId) {
                html += `<option value="${data.result[i].partsBrandId}" selected>${data.result[i].partsBrandName}</option>`;
            } else {
                html += `<option value="${data.result[i].partsBrandId}">${data.result[i].partsBrandName}</option>`;
            }
        }
        html += `</select>`;
        $(".partsBrands").html(html);
    }

    /**
     * 封装
     * 	新增,或者编辑图片集合的json
     * @returns
     */
    function imgs() {
        var imgs = "[",
            flag = 1;
        var len = $(".uploadImg img").length;
        $(".uploadImg .siteUpload img").each(function(i) {
            var url = $(this).attr("src");
            if (url != "" && url != undefined) {
                imgs += "{\"sorting\":" + flag + ",\"url\":\"" + url + "\"},";
                flag++;
            }
        });
        if (imgs.indexOf(",") > 0) {
            imgs = imgs.substring(0, imgs.length - 1);
        }
        imgs += "]";
        return imgs;
    }

    /**
     * 封住所有扩展属性的json
     * @returns
     */
    function attrExtra() {
        var attrExtra = "[",
            flag = 1;
        var len = $(".attrExtra .paramGroup").length;
        $(".attrExtra .paramGroup").each(function(i) {
            var paramName = $(this).find(".paramName").val();;
            var paramContent = $(this).find(".paramContent").val();
            if (paramName != null && paramName != "" && paramContent != null && paramContent != "") {
                attrExtra += "{\"attrKey\":\"" + paramName + "\",\"attrValue\":\"" + paramContent + "\"},";
            }
        });
        if (attrExtra.indexOf(",") > 0) {
            attrExtra = attrExtra.substring(0, attrExtra.length - 1);
        }
        attrExtra += "]";
        return attrExtra;
    }
    
    /**
     * 封装新增的车型
     * @returns
     */
    function carModel(){
    	var carModelsJson = "[";
    	if($(".parts_car_info tr").length>0){
    		$(".parts_car_info tr").each(function(i){
        		carModelsJson+="{\"id\":\"" + $(this).find(".carModelId").val() + "\"},";
        	});
    	}
    	if (carModelsJson.indexOf(",") > 0) {
    		carModelsJson = carModelsJson.substring(0, carModelsJson.length - 1);
        }
    	carModelsJson += "]";
    	return carModelsJson;
    	
    }

    /**
     * 封装新增和add的请求数据
     * @returns 返回json 字符串
     */
    function requestData() {
        var partsAttrExtrs = attrExtra(); //扩展属性
        var partsPics = imgs(); //图片集合
        var carModels = carModel();
        var partsBrandId = $("#partsBrandId").val() == undefined ? -1 : $("#partsBrandId").val(),
            curPrice = $("#curPrice").val() == undefined ? -1 : $("#curPrice").val(),
            discount = $("#discount").val() == undefined ? -1 : $("#discount").val(),
            partsTypeId = $("#partsTypeId").val() == undefined ? -1 : $("#partsTypeId").val(),
            partsId = $("#partsIdForOper").val() == undefined ? -1 : $("#partsIdForOper").val(),
            uable = $('input:radio:checked').val(),
            typeName = $("#cName").val() == undefined ? "" : $("#cName").val();
        var dataJson = "{" +
            "\"partsBrandId\":\"" + partsBrandId + "\"," +
            "\"partsModel\":\"" + $("#partsModel").val() + "\"," + 
            "\"curPrice\":\"" + curPrice + "\"," +
            "\"discount\":\"" + discount + "\"," +
            "\"isUsable\":\"" + uable + "\"," +
            "\"origin\":\"" + $("#origin").val() + "\"," +
            "\"partsFactory\":\"" + $("#partsFactory").val() + "\"," +
            "\"partsSpec\":\"" + $("#partsSpec").val() + "\"," +
            "\"partsUnit\":\"" + $("#partsUnit").val() + "\"," +
            "\"partsTypeId\":\"" + $("#TheTreeIdOfAdd").val() + "\"," +
            //"\"pname\":\""+typeName+"\"," +
            "\"price\":\"" + $("#price").val() + "\"," +
            "\"shelfLife\":\"" + $("#shelfLife").val() + "\"," +
            "\"typeName\":\"" + typeName + "\"," +
            "\"partsName\":\"" + $("#partsName").val() + "\"," +
            "\"partsId\":\"" + partsId + "\"," +
            "\"partsPics\":";
        dataJson += partsPics + ",";
        dataJson += "\"partsAttrExtrs\":" + partsAttrExtrs+",";
        dataJson += "\"carModels\":" + carModels; 
        dataJson += "}";
        dataJson = JSON.parse(dataJson); //解析成json对象
        var data = JSON.stringify(dataJson); //转换为json字符串
        //console.log(data);
        return data;
    }

    //点击新增按钮
    function afterAddPart() {
        //清空数据
        $("#commodityBox input,#commodityBox select").not(":radio").each(function() {
            $(this).removeAttr("disabled");
            $(this).val("");
        });
        $("#cName").val(""); //清空树形菜单数据
        $("#modelContent a").removeClass('curSelectedNode'); //移除样式
        //清空图片
        $(".uploadImg").html("");
        //清空车型
        $(".parts_car_info").html("");
        //将新增按钮变为可用
        $("#addCommodity").removeAttr("disabled");
    }

    /**
     * 编辑之后清空
     * @returns
     */
    function afterEditPart() {
        //清空数据
        $("#commodityBox :text,#commodityBox select").each(function() {
            $(this).val("");
        });
        $("#modelContent a").removeClass('curSelectedNode'); //移除样式
        //清空动态参数div
        $(".attrExtra").html("");
        //清空图片
        $(".uploadImg").not(".uploadImg:first").html("");
        //清空车型
        $(".parts_car_info").html("");
        //radio还原
        $("input[name='isUnable']").attr("checked", false);
        //将编辑按钮变为可用
        $("#edit").removeAttr("disabled");
    }

    //点击查看按钮调用方法
    function afterView() {
        $("#commodityBox input,#commodityBox select").each(function() {
            $(this).removeAttr("disabled");
            $(this).val("");
        });
        $("#cName").val(""); //清空树形菜单数据
        $("#modelContent a").removeClass('curSelectedNode'); //移除样式
        //清空动态参数div
        $(".attrExtra").html("");
        //清空图片
        $(".uploadImg").not(".uploadImg:first").html("");
        //清空车型
        $(".parts_car_info").html("");
        //将查看按钮变为可用
        $("#view").removeAttr("disabled");
    }

    //查看的时候禁用元素
    function forbidden() {
        //禁用元素
        $("#commodityBox input,#commodityBox select").each(function() {
            $(this).attr("disabled", "disabled");
        });
        $("#view").attr("disabled", "disabled");
        $('.closeBtn').css('display', 'none');
        $('.parts_car_info tr').find("button").removeAttr("onclick");
        $('.parts_car_info tr').find("button").attr("disabled", "disabled");
    }
    //按钮禁用
    function ButtonForbidden() {
        $(".view_c").attr("disabled", true);
        $(".edit_c").attr("disabled", true);
        $(".add_c").attr("disabled", true);
        $(".isUse_c").attr("disabled", true);
        $("#searchCommodity").attr("disabled", true); 
    }
    //按钮启用
    function clearButtonForbidden() {
        $(".view_c").attr("disabled", false);
        $(".edit_c").attr("disabled", false);
        $(".add_c").attr("disabled", false);
        $(".isUse_c").attr("disabled", false);
        $("#searchCommodity").attr("disabled", false);
    }
    
    /**
     * 车型选择框点击事件
     * @returns
     */
    function car(){
    	
    	var res;
    	
    	/**
    	 * 加载车辆品牌信息
    	 */
    	loadCarBrand();
    	
    	/**
    	 * 车辆品牌选中事件
    	 */
    	$("#carBrand").on("select2:select",function(){
    		res = $(this).select2("data");
    		$("#brandId").val(res[0].id);
    		/**
    		 * 清空车系
    		 */
    		$("#carSeries").html("");
    		
    		/**
    		 * 启用车系
    		 */
    		$("#carSeries").prop("disabled", false);
    		
    		/**
    		 * 禁用车型
    		 */
    		$("#carModel").prop("disabled", true);
    		
    		loadCarSeries();
    	});

    	
    	
    	/**
    	 *  车系选中事件
    	 */
    	$("#carSeries").on("select2:select",function(){
    		res = $(this).select2("data");
    		$("#seriesId").val(res[0].id);
    		/**
    		 * 禁用车型
    		 */
    		$("#carModel").prop("disabled", false);
    		/**
    		 * 加载车辆车型
    		 */
    		loadCarModel();
    	});
    }
    
    /**
	 * 加载品牌信息
	 */
    function loadCarBrand(){ 
    	/**
    	 * 显示placaholder
    	 */
    	$('#carSeries').select2({ language : 'zh-CN',placeholder: "请选择车辆车系"});
    	$('#carModel').select2({ language : 'zh-CN',placeholder: "请选择车型"});
    	
    	/**
    	 * 禁用车系、车型
    	 */
    	$("#carSeries").prop("disabled", true); 
		$("#carModel").prop("disabled", true);
		
		
    	$('#carBrand').select2({
    		language : 'zh-CN',
            placeholder: "请选择车辆品牌",
            ajax:{
                url:"/carbrandcombobox",
                dataType:"json",
                delay:200,//millionsecond
                data:function(params){
                    return {
                    	pageIndex:params.page || 1,
                    	pageSize:pageSize,
                    	brandName:params.term,
                    };
                },
                cache:true,
                processResults: function (res, params) {
                	params.page = params.page || 1;
                    var options = [];
                	if(res!=null){
                		var result = res.result;
                		for (var i = 0; i < result.length; i++) {
                			var option = {"id":result[i].key,"text":result[i].value};
                			options.push(option);
    					}
                	}
                    return {
                        results: options,
                        pagination: {
                            more: (params.page * pageSize) < res.totalCount
                        }
                    };
                },
            },
	    	escapeMarkup: function (markup) { return markup; } //防止sql注入
        });
    	
    	/**
    	 * 车辆品牌点击事件
    	 */
    	$('#carBrand').on('select2:select', function (e) {
    	     var data = e.params.data;
    	     $("#brandId").val(data.id);
    	     $("#brandId").attr("name",data.text);
    	    
    	});
    }
    
    
    /**
     * 加载车系信息
     * @returns
     */
    function loadCarSeries(){
		if($("#brandId").val()){
			/**
    		 * 清空车系
    		 */
    		$("#carSeries").html("");
    		
    		/**
    		 * 清空车型
    		 */
    		$("#carModel").html("");
    		
    		$('#carSeries').select2({
	    		language : 'zh-CN',
	            placeholder: "请选择车辆车系",
	            ajax:{
	                url:"/carseriescombobox",
	                dataType:"json",
	                delay:200,//millionsecond
	                data:function(params){
	                    return {
	                    	pageIndex:params.page || 1,
	                    	pageSize:pageSize,
	                    	serName:params.term,
	                    	brandId:$("#brandId").val()
	                    };
	                },
	                cache:true,
	                processResults: function (res, params) {
	                	params.page = params.page || 1;
	                    var options = [];
	                	if(res!=null){
	                		var result = res.result;
	                		for (var i = 0; i < result.length; i++) {
	                			var option = {"id":result[i].key,"text":result[i].value};
	                			options.push(option);
	    					}
	                	}
	                    return {
	                        results: options,
	                        pagination: {
	                            more: (params.page * pageSize) < res.totalCount
	                        }
	                    };
	                },
	            },
		    	escapeMarkup: function (markup) { return markup; } //防止sql注入
	        });
		}else{
			layer.msg("请先选择车辆品牌");
		}
	}
    
    /**
	 * 车系选中事件
	 */
	$('#carSeries').on('select2:select', function (e) {
	     var data = e.params.data;
	     $("#seriesId").val(data.id);
	     $("#seriesId").attr("name",data.text);
	});
    
    /**
	 * 加载车辆车型
	 */
	function loadCarModel(){
		if($("#seriesId").val()){
    		/**
    		 * 清空车型
    		 */
    		$("#carModel").html("");
    		$('#carModel').select2({
	    		language : 'zh-CN',
	            placeholder: "请选择车型",
	            maximumSelectionLength:3,
	            ajax:{
	                url:"/carmodelcombobox",
	                dataType:"json",
	                delay:200,//millionsecond
	                data:function(params){
	                    return {
	                    	pageIndex:params.page || 1,
	                    	pageSize:pageSize,
	                    	modelName:params.term,
	                    	seriesId:$("#seriesId").val()
	                    };
	                },
	                //cache:true,
	                processResults: function (res, params) {
	                	params.page = params.page || 1;
	                    var options = [];
	                	if(res!=null){
	                		var result = res.result;
	                		for (var i = 0; i < result.length; i++) {
	                			var option = {"id":result[i].key,"text":result[i].value};
	                			options.push(option);
	    					}
	                	}
	                    return {
	                        results: options,
	                        pagination: {
	                            more: (params.page * pageSize) < res.totalCount
	                        }
	                    };
	                },
	            },
		    	escapeMarkup: function (markup) { return markup; } //防止sql注入
	        });
    		
    		
		}else{
			layer.msg("请先选择车辆车系");
		}
	}
	
	/**
	 * 车型选中事件
	 */
	$('#carModel').on('select2:select', function (e) {
	    var data = e.params.data;
	    $("#modelId").val(data.id);
  	    $("#modelId").attr("name",data.text);
  	    var isExistCarModelId=0;
	    $(".parts_car_info tr").each(function(i){
	    	var alreadyExistCarModelId = $(this).find(".carModelId").val();
	    	if(alreadyExistCarModelId==data.id){
	    		isExistCarModelId = 1;
	    	}
	    	
	    });
	    if(isExistCarModelId==0){
	    	var tr=`<tr>
	    		<td>${$("#brandId").attr("name")}</td>
	    		<td>${$("#seriesId").attr("name")}</td>
	    		<td>${$("#modelId").attr("name")} <input type="hidden" value="${$("#modelId").val()}" class="carModelId"></td>
	    		<td><button class="layui-btn"  type="button" onclick="javascript:$(this).parent().parent().remove();">删除</button></td>
	    	</tr>`;
	    	$(".parts_car_info").append(tr);
	    }
  	    
	});
	
	/**
	 * 显示车型
	 * 
	 */
	function showPartsCarModel(partsId){
		$.ajax({
            url: "/viewpartsmodel",
            type: "get",
            async: false,
            data:{partsId:partsId},
            success: function(data) {
            	//车型配件数据
            	if(data.result!=null){
            		var tr="";
            		for (var i = 0; i < data.result.length; i++) {
            			tr+=`<tr>
        		    		<td>${data.result[i].carBrandName}</td>
        		    		<td>${data.result[i].carSeriesName}</td>
        		    		<td>${data.result[i].carModelName} <input type="hidden" value="${data.result[i].carModelId}" class="carModelId"></td>
        		    		<td><button class="layui-btn"  type="button" onclick="javascript:$(this).parent().parent().remove();">删除</button></td>
        		    	</tr>`;
            			
					}
            		$(".parts_car_info").append(tr);
            	}
            }
        });
	}
});

/**
 * 图片上传
 * @param obj 图片对象
 * @returns
 */
function change(obj) {
    //上传图片
    upload(obj);
}

function upload(obj) {
    var form = $("#form"); 
    var imgUrl = uploadImg(form, obj);
    if (imgUrl != "") { //如果上传文件成功
        appendImg(obj);
        $(obj).parent().parent().find("img").attr("src", imgUrl);
        //禁用上传按钮
        $(obj).attr("disabled", "disabled");
    } else {}

}

//上传追加图片
function appendImg(obj) {
    var img = `
		<div class="siteUpload">
            <img id="commodityImgUrl" src="">
            <div class="layui-box layui-upload-button">
                <input type="file" name="image" class="commodityImg" class="layui-upload-file" onchange="change(this)" accept="image/*"> 
                <span class="layui-upload-icon"><i class="layui-icon">&#xe61f;</i>上传图片</span>
            </div>
            <span class="closeBtn" onclick="delImg(this)"><i class="layui-icon">&#x1006;</i></span>
        </div>`;
    $(obj).parent().parent().parent().append(img);
    var flag = $(".siteUpload").length;
    hideLastImgDelIcon();
    if (flag == 6) {
        //隐藏最后一张图片
        $(".siteUpload:last").hide();
    } else {
        $(".siteUpload:last").show();
    }
    
}

/**
 * 删除图片
 * @param obj 删除按钮
 * @returns
 */
function delImg(obj) {
    var url = $(obj).parent().find("img").attr("src");
    delFile(url);
    $(obj).parent().remove(); 
    var disp = $(".siteUpload:last").css("display");
    if(disp=="none"){
    	$(".siteUpload:last").show();
    }
    hideLastImgDelIcon();
}

//隐藏最后一个图片的删除按钮
function hideLastImgDelIcon(){
	var flag = $(".siteUpload").length;
    $(".siteUpload .closeBtn").each(function(index){
    	if((flag-1)==index){
    		$(this).css("display","none");
    	}else{
    		$(this).css("display","block");
    	}
    });
}

//图片数量
function imgCount(){
	var count = $(".siteUpload").length;
	return count;
}
//在配件树中选中配件后写入input中
function beforeClick(treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    if (!check) {
    	 var treeObj = $.fn.zTree.getZTreeObj(treeId);
    	 treeObj.expandNode(treeNode);
         layer.msg("存在子类不可选！",{time:500});
    }
    return check;
}

/**
 * 
 * @param e 当前元素
 * @param treeId id="cTree"
 * @param treeNode  当前节点
 * @returns
 */
function onClick(e, treeId, treeNode) {
    $(e.target).parents('li').siblings('li').find('.curSelectedNode').removeClass('curSelectedNode');
    if (treeId == 'commodityTree') {
    	var display = $("#menuContent").css("display");
		if(display=="block"){
			$("#menuContent").hide();
		}
		$("#TheTreeIdOfSearch").val(treeNode.id);
        $("#commodityName").val(treeNode.name);
    } 
}

//新增商品配件分类
function onClickAdd(e, treeId, treeNode) {
    $(e.target).parents('li').siblings('li').find('.curSelectedNode').removeClass('curSelectedNode');
	var display = $("#modelContent").css("display");
	if(display=="block"){
		$("#modelContent").hide();
	}
	$("#TheTreeIdOfAdd").val(treeNode.id);
    $("#cName").val(treeNode.name);
}

var treeTemp;

// 配件树展示与收缩
function showMenu(temp) {
    treeTemp = temp;
    if (treeTemp == 'model') {
        $("#modelContent").css({ left: 0, top: "37px" }).slideToggle("fast");
    } else if (treeTemp == 'main') {
        $("#menuContent").css({ left: 0, top: "37px" }).slideToggle("fast");
    }
    
}


/**
 * 配件树数据源
 * @returns
 */
function zNodes() {
    var node;
    $.ajax({
        url: "/findpartstype",
        type: "get",
        async: false,
        success: function(data) {
            node = data;
        },
        error: function(data) {
            layer.msg("页面查询失败"); 
        }
    });
    return node;
}

var zNodes = zNodes();

// 配件树初始化
$(document).ready(function() {
    $.fn.zTree.init($("#commodityTree"), settingQuery, zNodes);
    $.fn.zTree.init($("#cTree"), setting, zNodes);
});
// 配件树设置
var setting = {
    view: {
        dblClickExpand: false,
        autoCancelSelected: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeClick: beforeClick, //检查
        onClick: onClickAdd
    }
};


var settingQuery = {
    view: {
        dblClickExpand: false,
        autoCancelSelected: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        //beforeClick: beforeClick, //检查
        onClick: onClick
    }
};
