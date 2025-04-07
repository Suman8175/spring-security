package com.suman.springsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorMessage<T> {
    private String status;
    private long timestamp;
    private T message;
}
