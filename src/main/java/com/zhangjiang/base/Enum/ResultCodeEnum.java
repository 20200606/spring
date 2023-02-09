package com.zhangjiang.base.Enum;

/**
 * @className ResultCodeEnum
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/5 16:40:06
 */
public enum ResultCodeEnum {
    SUCCESS("200", "成功"),
    FAIL("201","失败");

    private String code;
    private String message;

    ResultCodeEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
