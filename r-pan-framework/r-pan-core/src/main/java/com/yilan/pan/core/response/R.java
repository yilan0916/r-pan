package com.yilan.pan.core.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 公用返回对象
 *
 * @author yilan0916
 * @date 2024/7/23
 */
@Getter
// 保证json序列化的时候，如果属性为null，key也就一起消失
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态说明文案
     */
    private String message;

    /**
     * 返回承载
     */
    private T data;

    private R(Integer code) {
        this.code = code;
    }

    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return Objects.equals(this.code, ResponseStatus.SUCCESS.getCode());
    }

    public static <T> R<T> success() {
        return new R<T>(ResponseStatus.SUCCESS.getCode());
    }

//    public static <T> R<T> success(String message) {
//        return new R<T>(ResponseStatus.SUCCESS.getCode(), message);
//    }

    public static <T> R<T> success(T data) {
        return new R<T>(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getDescription(), data);
    }

    public static <T> R<T> fail() {
        return new R<T>(ResponseStatus.ERROR.getCode());
    }

    public static <T> R<T> fail(String message) {
        return new R<T>(ResponseStatus.ERROR.getCode(), message);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return new R<T>(code, message);
    }

    public static <T> R<T> fail(ResponseStatus responseStatus) {
        return new R<T>(responseStatus.getCode(), responseStatus.getDescription());
    }
}
