package com.xiezhenyu.response;

import lombok.Data;

/**
 * @author Tim
 */
@Data
public class CommonResult<T> {
    private int code = ReturnCodeEnum.SUCCESS.getValue();
    private String message = "成功";
    private T data;
    private Boolean success = true;

    public CommonResult(T data,String message){
        this.data = data;
        this.message = message;
    }

    public CommonResult(String message){
        this.message = message;
    }

    public static CommonResult successCommonResult(Object data,String message){
        return new CommonResult(data,message);
    }

    public static CommonResult errorCommonResult(Object data,String message){
        CommonResult commonResult = new CommonResult(data, message);
        commonResult.setCode(ReturnCodeEnum.FAIL.getValue());
        return commonResult;
    }

    public static CommonResult successCommonResult(String message){
        CommonResult commonResult = new CommonResult(message);
        return commonResult;
    }

    public static CommonResult errorCommonResult(String message){
        CommonResult commonResult = new CommonResult(message);
        commonResult.code = ReturnCodeEnum.FAIL.getValue();
        commonResult.setSuccess(false);
        return commonResult;
    }
}
