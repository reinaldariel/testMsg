package com.jdlservice.accountservice.entity.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdatePassReq {
    private String nip;
    private String passLama;
    private String passBaru;
}
