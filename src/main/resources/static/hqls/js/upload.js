//文件相关操作路径
var localareaUrl='http://42.159.202.20:9999/location';
var imgurl='http://42.159.202.20:22222/image';
var imgurls='http://42.159.202.20:22222/images';
var fileurl='http://42.159.202.20:22222/file';
/**
 * formObj: 指定上传文件的form
 * imgObj: 上传的图片 
 * 		可以为单个对象也可以为集合
 * 		var fileObj = $("#first_file");
 *	  	var bo=fileObj[0].files[0];
 * displayObj:
 * 		上传完成 之后图片显示到的对象	  	
 *	  	
 * @param form
 * @returns
 */
function uploadImg(formObj,imgObj){
	//form对象
	var formdata;
	var url ; //上传地址
	if(formObj instanceof jQuery){  
		formdata = new FormData(formObj[0]); 
	}else{  
		formdata = new FormData(formObj);
	}  
	
	if(imgObj instanceof jQuery){
		if(imgObj.length>1){
			formdata.append("images",imgObj[0]);
			url = imgurls;
		}else{
			formdata.append("image",imgObj[0]);
			url = imgurl;
		}
		
	}else{  
		if(imgObj.length>1){
			formdata.append("images",imgObj);
			url = imgurls;
		}else{
			formdata.append("image",imgObj);
			url = imgurl;
		}
		
	}  
	
	$.ajax({
		type : "post",
		async : false,
		data :formdata,
		processData: false,
	    contentType: false,
	    headers:{
			  "Authorization":localStorage.token
		},
		url : url,
		success : function(data) {
			alert("返回数据:"+JSON.stringify(data));
			var response = JSON.stringify(data)
			//displayImg(imgObj,imgUrl); //显示图片
		},
		error : function(e) {
			alert(JSON.stringify(e));
			console.log(JSON.stringify(e));
		}
	}); 
}

/**
 * 图片显示
 * @returns
 */
function displayImg(imgObj,imgurl){
	$(imgObj).css("background-image","url("+imgurl+")"); 
}