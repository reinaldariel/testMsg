package com.jdlservice.accountservice.service;

import com.google.gson.Gson;
import com.jdlservice.accountservice.dao.PrivilegeDao;
import com.jdlservice.accountservice.dao.UserDao;
import com.jdlservice.accountservice.entity.BaseResp;
import com.jdlservice.accountservice.entity.Constant;
import com.jdlservice.accountservice.entity.Privilege;
import com.jdlservice.accountservice.entity.PrivilegeId;
import com.jdlservice.accountservice.entity.privilege.PrivilegeReq;
import com.jdlservice.accountservice.entity.privilege.PrivilegeResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Service
public class PrivilegeService {
    @Autowired
    private MessageSource messageSource;
    private String language = "in";

    @Autowired
    PrivilegeDao privilegeDao;
    @Autowired
    UserDao userDao;

    private PrivilegeResp privilegeResp = new PrivilegeResp();
    private BaseResp baseResp = new BaseResp();

    public PrivilegeResp getPrivilege(PrivilegeId privilegeId){
        privilegeId.setAppId(privilegeId.getAppId().toUpperCase());
        privilegeId.setNip(privilegeId.getNip().toUpperCase());
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (userDao.existsById(privilegeId.getNip())) {
            if (privilegeDao.existsById(privilegeId)) {
                Optional<Privilege> privilege = privilegeDao.findById(privilegeId);
                privilegeResp.setAppId(privilege.get().getAppId().toUpperCase());
//            if (privilege.get().getHakAkses() != null && !"".equals(privilege.get().getHakAkses())) {
                privilegeResp.setHakAkses(privilege.get().getHakAkses().toUpperCase());
                privilegeResp.setNip(privilege.get().getNip().toUpperCase());
                privilegeResp.setErrorCode(Constant.ErrorCode.SUCCESS);
                privilegeResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
                privilegeResp.setTimestamp(timestamp);
            } else {
                privilegeResp.setAppId(privilegeId.getAppId().toUpperCase());
                privilegeResp.setHakAkses(Constant.PrivilegeType.USER);
                privilegeResp.setNip(privilegeId.getNip().toUpperCase());
                privilegeResp.setErrorCode(Constant.ErrorCode.SUCCESS);
                privilegeResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
                privilegeResp.setTimestamp(timestamp);
            }
        }else {
            privilegeResp.setErrorCode("102");
            privilegeResp.setErrorMessage(messageSource.getMessage("102", null, Locale.forLanguageTag(language)));
            privilegeResp.setTimestamp(timestamp);
        }
        return privilegeResp;
    }

    public BaseResp grantAdmin(PrivilegeId privilegeId){
        privilegeId.setAppId(privilegeId.getAppId().toUpperCase());
        privilegeId.setNip(privilegeId.getNip().toUpperCase());
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (userDao.existsById(privilegeId.getNip())) {
            Privilege privilege = new Privilege(privilegeId.getNip(), privilegeId.getAppId(), Constant.PrivilegeType.ADMIN);
            privilegeDao.save(privilege);
            baseResp.setErrorCode(Constant.ErrorCode.SUCCESS);
            baseResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
            baseResp.setTimestamp(timestamp);
        }else {
            baseResp.setErrorCode("102");
            baseResp.setErrorMessage(messageSource.getMessage("102", null, Locale.forLanguageTag(language)));
            baseResp.setTimestamp(timestamp);
        }
        return baseResp;
    }

    public BaseResp grantUser(PrivilegeId privilegeId){
        privilegeId.setAppId(privilegeId.getAppId().toUpperCase());
        privilegeId.setNip(privilegeId.getNip().toUpperCase());
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (userDao.existsById(privilegeId.getNip())) {
            Privilege privilege = new Privilege(privilegeId.getNip(), privilegeId.getAppId(), Constant.PrivilegeType.USER);
            privilegeDao.save(privilege);
            baseResp.setErrorCode(Constant.ErrorCode.SUCCESS);
            baseResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
            baseResp.setTimestamp(timestamp);
        }else {
            baseResp.setErrorCode("102");
            baseResp.setErrorMessage(messageSource.getMessage("102", null, Locale.forLanguageTag(language)));
            baseResp.setTimestamp(timestamp);
        }
        return baseResp;
    }
}
