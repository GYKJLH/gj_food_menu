package org.example.entity;

import lombok.Data;

@Data
public class Response<T> {

    private Integer code;

    private String message;

    private Object data;

    public Response() {}

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Response(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        Response<T> r = new Response<>();
        r.code = 200;
        r.data = data;
        r.message = "success";
        return r;
    }

    public static <T> Response<T> success(T data, String message) {
        Response<T> r = new Response<>();
        r.code = 200;
        r.data = data;
        r.message = message;
        return r;
    }

    public static <T> Response<T> fail(String message) {
        Response<T> r = new Response<>();
        r.code = 400;
        r.message = message;
        return r;
    }

}
