package com.alipapa.smp.utils;

import java.util.function.Function;

/**
 * 统一返回结果
 *
 * @param <T>
 */
public class WebApiResponse<T> {
    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = 1;
    private int code;
    private String error;
    private Long totalCount;

    private T data;

    public WebApiResponse() {
    }


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public static <T> WebApiResponse<T> success(T data) {
        WebApiResponse<T> response = new WebApiResponse();
        response.setCode(0);
        response.setData(data);
        return response;
    }

    /**
     * 分页使用
     *
     * @param data
     * @param totalCount
     * @param <T>
     * @return
     */
    public static <T> WebApiResponse<T> success(T data, long totalCount) {
        WebApiResponse<T> response = WebApiResponse.success(data);
        response.setTotalCount(totalCount);
        return response;
    }


    public static <T> WebApiResponse<T> error(String errorMessage) {
        return error(errorMessage, 1);
    }

    public static <T> WebApiResponse<T> error(String errorMessage, int errorCode) {
        WebApiResponse<T> response = new WebApiResponse();
        response.setCode(errorCode);
        response.setError(errorMessage);
        return response;
    }

    public static <T> WebApiResponse<T> asProcess(WebApiResponse.Procedure<T> procedure) {
        return asProcess(procedure, Throwable::toString);
    }

    public static <T> WebApiResponse<T> asProcess(WebApiResponse.Procedure<T> procedure, Function<Exception, String> exceptionHandler) {
        try {
            return success(procedure.apply());
        } catch (Exception var3) {
            return error((String) exceptionHandler.apply(var3));
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            WebApiResponse<?> that = (WebApiResponse) o;
            if (this.code != that.code) {
                return false;
            } else {
                if (this.error != null) {
                    if (!this.error.equals(that.error)) {
                        return false;
                    }
                } else if (that.error != null) {
                    return false;
                }

                return this.data.equals(that.data);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.code;
        result = 31 * result + (this.error != null ? this.error.hashCode() : 0);
        result = 31 * result + this.data.hashCode();
        return result;
    }

    public String toString() {
        return "WebApiResponse{code=" + this.code + ", error='" + this.error + '\'' + ", data=" + this.data + '}';
    }

    @FunctionalInterface
    public interface Procedure<T> {
        T apply() throws Exception;
    }
}

