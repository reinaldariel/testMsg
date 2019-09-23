package com.jdlservice.accountservice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class LogTrx {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String pkId;

    private Timestamp createdDate;
    private String nip;
    private String activity;
    @Nullable
    private String errorCode;
    @Nullable
    private String errorMessage;
}
