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
		if(imgObj.files.length>1){
			formdata.append("images",imgObj[0].files);
			url = imgurls;
		}else{
			formdata.append("image",imgObj.files[0]);
			url = imgurl;
		}
		
	}else{  
		if(imgObj.files.length>1){
			formdata.append("images",imgObj);
			url = imgurls;
		}else{
			formdata.append("image",imgObj.files[0]);
			url = imgurl;
		}
		
	}  
	//上传文件的流
	console.log(imgObj.files[0]);
	var returnResult = "";
	$.ajax({
		type : "POST",
		async : false,
		data :formdata,
		processData: false,
	    contentType: false,
	    headers:{
			  "Authorization":localStorage.token
		},
		url : url,
		success : function(data) {
			returnResult =data.result.fileUrl;
		},
		error : function(e) {
			e = JSON.stringify(e);
			layer.msg(e);
		}
	}); 
	return returnResult;
}

/**
 * 图片显示
 * @returns
 */
//function displayImg(imgObj,imgurl){
//	$(imgObj).css("background-image","url("+imgurl+")"); 
//}

/**
 * 删除图片
 */
function delFile(imgUrl){
	$.ajax({
		type : "DELETE",//"OPTIONS"可以删除,
		async : false,
		data:imgUrl,
		url : fileurl+"/?url="+imgUrl,
		success : function(data) {
			layer.msg("删除图片成功");
		},
		error : function(e) {
			layer.msg("删除图片失败");
		}
	}); 
}
