package com.sinoauto.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * Rest接口返回模型
 * @author fujl
 * @version 1.0
 * @date 2017-07-03 14:17:06
 */
public class RestModel<T> {
	@ApiModelProperty("错误码")
	private int errcode;
	@ApiModelProperty("错误消息")
	private String errmsg;
	@ApiModelProperty("数据")
	@JsonInclude(Include.NON_NULL)
	private T result;
	@ApiModelProperty("总条数")
	@JsonInclude(Include.NON_DEFAULT)
	private int totalCount;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		if (errmsg != null) {
			this.errmsg = errmsg;
		}
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		if (result != null) {
			this.result = result;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public RestModel(int errcode, String errmsg) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public RestModel(int errcode, String errmsg, T result) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
		this.result = result;
	}
	
	public RestModel(int errcode, String errmsg, T result, int totalCount) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
		this.result = result;
		this.totalCount = totalCount;
	}

	public RestModel(ErrorStatus errorStatus){
		super();
		this.errcode = errorStatus.getErrcode();
		this.errmsg = errorStatus.getErrmsg();
	}
	
	public RestModel(ErrorStatus errorStatus, T result){
		super();
		this.errcode = errorStatus.getErrcode();
		this.errmsg = errorStatus.getErrmsg();
		this.result = result;
	}
	
	public static <T> ResponseEntity<RestModel<T>> success() {
		return new ResponseEntity<RestModel<T>>(new RestModel<T>(0, "success"), HttpStatus.OK);
	}

	public static <T> ResponseEntity<RestModel<T>> success(T result) {
		return new ResponseEntity<RestModel<T>>(new RestModel<T>(0, "success", result), HttpStatus.OK);
	}
	/**
	 * 分页时调用的构造方法
	 * @param result 返回的结果集
	 * @param totalCount 返回的总条数
	 * @return
	 */
	public static <T> ResponseEntity<RestModel<T>> success(T result,int totalCount) {
		return new ResponseEntity<RestModel<T>>(new RestModel<T>(0, "success", result, totalCount), HttpStatus.OK);
	} 

	public static <T> ResponseEntity<RestModel<T>> error(HttpStatus httpStatus, ErrorStatus errorStatus) {
		return new ResponseEntity<RestModel<T>>(new RestModel<T>(errorStatus.getErrcode(), errorStatus.getErrmsg()), httpStatus);
	}

	public static <T> ResponseEntity<RestModel<T>> error(HttpStatus httpStatus, ErrorStatus errorStatus, String errorMsg) {
		return new ResponseEntity<RestModel<T>>(new RestModel<T>(errorStatus.getErrcode(), errorMsg), httpStatus);
	}
	
	public static <T> ResponseEntity<RestModel<T>> error(HttpStatus httpStatus, int errcode, String errorMsg) {
		return new ResponseEntity<RestModel<T>>(new RestModel<T>(errcode, errorMsg), httpStatus);
	}

	public static <T> ResponseEntity<RestModel<T>> error(HttpStatus httpStatus, ErrorStatus resultStatus, T errorMsg) {
		return new ResponseEntity<RestModel<T>>(new RestModel<T>(resultStatus.getErrcode(), resultStatus.getErrmsg(), errorMsg), httpStatus);
	}
}
