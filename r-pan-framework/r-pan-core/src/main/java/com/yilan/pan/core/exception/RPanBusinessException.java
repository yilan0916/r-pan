package com.yilan.pan.core.exception;

import com.yilan.pan.core.response.ResponseStatus;
import lombok.Data;

/**
 * 自定义全局业务异常类
 *
 * @author yilan0916
 * @date 2024/7/24
 */
@Data
public class RPanBusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    public RPanBusinessException(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getDescription();
    }
    public RPanBusinessException(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    public RPanBusinessException(String message) {
        this.code = ResponseStatus.ERROR_PARAM.getCode();
        this.message = message;
    }

    public RPanBusinessException() {
        this.code = ResponseStatus.ERROR_PARAM.getCode();
        this.message = ResponseStatus.ERROR_PARAM.getDescription();
    }
}
