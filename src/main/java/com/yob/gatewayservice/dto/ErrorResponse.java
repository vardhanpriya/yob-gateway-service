package com.yob.gatewayservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse extends  BaseResponse{
    private String statusCode;
    private List<ErrorMessage> errors;
}
