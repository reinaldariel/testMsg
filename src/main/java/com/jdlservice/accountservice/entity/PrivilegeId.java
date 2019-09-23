package com.jdlservice.accountservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PrivilegeId implements Serializable {
    private String nip;
    private String appId;

    public PrivilegeId(){}
    public PrivilegeId(String nip, String appId) {
        this.nip = nip;
        this.appId = appId;
    }
}
