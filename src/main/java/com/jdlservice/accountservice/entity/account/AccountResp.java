package com.jdlservice.accountservice.entity.account;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jdlservice.accountservice.entity.BaseResp;
import lombok.Data;

//@Component

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccountResp extends BaseResp {
 private UserResp user;
    public AccountResp() {
    }
}
