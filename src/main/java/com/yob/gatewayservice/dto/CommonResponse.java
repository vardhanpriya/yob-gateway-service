package com.yob.gatewayservice.dto;

import com.yob.gatewayservice.Constants.Constants;
import lombok.Data;

@Data
public class CommonResponse {
    private static final long serialVersionUID = 1L;

    private Status status = Status.SUCCESS;
    private String message;
    private String responseCd = Constants.STATUS_CODE_SUCCESS;
}
