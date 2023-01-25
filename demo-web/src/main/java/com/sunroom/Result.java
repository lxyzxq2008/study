package com.sunroom;

import lombok.Data;

@Data
public class Result<T> {
    private Boolean status;
    private String msg;
    private String code;
    private T data;
}
