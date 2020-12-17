package com.xiezhenyu.response;

/**
 * @author Tim
 */

public enum ReturnCodeEnum {
    SUCCESS(0),FAIL(-1),PARAMS_ERROR(3),DENIED_PERMISSION(4);
    private final Integer value;
    ReturnCodeEnum(Integer value) {
        this.value = value;
    }
    public Integer getValue(){
        return this.value;
    }
}
