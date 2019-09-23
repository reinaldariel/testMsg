package com.jdlservice.accountservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseResp {
    protected String errorMessage;
    protected String errorCode;
    protected String timestamp;
}
