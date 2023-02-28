package com.zhangjiang.base.lock;

/**
 * @className LockResultDTO
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/10 14:28:24
 */
public class LockResultDTO<T> {

    public static final String SUCCESS = "1";
    public static final String PROCESS = "0";
    public static final String FAIL = "-1";

    public static LockResultDTO success(Object data){
        LockResultDTO<Object> lockResultDTO = new LockResultDTO<>();
        lockResultDTO.setCode(SUCCESS);
        lockResultDTO.setData(data);
        return lockResultDTO;
    }

    public static LockResultDTO process(Object data){
        LockResultDTO<Object> lockResultDTO = new LockResultDTO<>();
        lockResultDTO.setCode(PROCESS);
        lockResultDTO.setData(data);
        return lockResultDTO;
    }

    public static LockResultDTO fail(Object data){
        LockResultDTO<Object> lockResultDTO = new LockResultDTO<>();
        lockResultDTO.setCode(FAIL);
        lockResultDTO.setData(data);
        return lockResultDTO;
    }


    private String code;
    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
