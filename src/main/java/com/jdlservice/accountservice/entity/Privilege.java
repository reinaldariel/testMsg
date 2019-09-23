package com.jdlservice.accountservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity
@DynamicUpdate
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@IdClass(PrivilegeId.class)
public class Privilege {
        @Id
        private String nip;
        @Id
        private String appId;
        private String hakAkses;

        public Privilege(){
        }
        public Privilege(String nip, String appId, String hakAkses){
            this.nip = nip;
            this.appId = appId;
            this.hakAkses = hakAkses;
        }
}

