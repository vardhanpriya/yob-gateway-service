package com.yob.gatewayservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class ErrorResponse extends  BaseResponse{
    private String statusCode;
    private List<ErrorMessage> errors;
}
