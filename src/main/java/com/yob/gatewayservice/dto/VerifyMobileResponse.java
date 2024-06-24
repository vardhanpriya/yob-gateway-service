package com.yob.gatewayservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VerifyMobileResponse extends  CommonResponse{
    private String refNum;
    private String mobileNumber;
    private boolean isPost = false;     // will check later
    private boolean isPost72 = false;
    private boolean eligibleForResume = false;
    private boolean isPostAccount = false;
}
