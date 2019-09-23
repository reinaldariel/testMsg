package com.jdlservice.accountservice.entity.account;

import lombok.Data;

@Data
public class UserResp {
    private String nip;
    private String email;
    private String nama;

    public UserResp(String nip, String email, String nama) {
        this.nip = nip;
        this.email = email;
        this.nama = nama;
    }
}
