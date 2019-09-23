package com.jdlservice.accountservice.entity.privilege;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jdlservice.accountservice.entity.BaseResp;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PrivilegeResp extends BaseResp {
    String hakAkses;
    String nip;
    String appId;
}
