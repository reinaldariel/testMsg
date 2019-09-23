package com.jdlservice.accountservice.service;

import com.jdlservice.accountservice.dao.UserDao;
import com.jdlservice.accountservice.entity.BaseResp;
import com.jdlservice.accountservice.entity.User;
import com.jdlservice.accountservice.entity.account.*;
import com.jdlservice.accountservice.util.LogHelper;
import com.jdlservice.accountservice.entity.Constant;
import com.jdlservice.accountservice.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

//import com.jdlservice.ireimburse.entity.LogTrx;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import javax.xml.bind.DatatypeConverter;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.Timestamp;
//import java.util.List;


@Service
public class AccountService {
private LoginResp loginResp = new LoginResp();
private BaseResp baseResp = new BaseResp();
@Autowired
private UserDao userDao;
@Autowired
private LogHelper logHelper;
    @Autowired
    private MessageSource messageSource;
    private String language = "in";

    public AccountResp getUserByNip (AccountReq account){
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        AccountResp resp = new AccountResp();
        try {
            account.setNip(account.getNip().toUpperCase());
            if (account.getNip().startsWith("U")) {
                if (userDao.existsById(account.getNip())) {
                    Optional<User> user = userDao.findById(account.getNip());
                    UserResp userResp = new UserResp(user.get().getNip(), user.get().getEmail(), user.get().getNama());
                    resp.setUser(userResp);
                    logHelper.printLog("DEBUG", "send", account);
                    logHelper.addLog(account.getNip(), "test", Constant.ErrorCode.SUCCESS, messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
                    logHelper.printLog("DEBUG", "receive", resp);
                    resp.setTimestamp(timestamp);
                    resp.setErrorCode(Constant.ErrorCode.SUCCESS);
                    resp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
                } else {
                    resp.setTimestamp(timestamp);
                    resp.setErrorCode("102");
                    resp.setErrorMessage(messageSource.getMessage("102", null, Locale.forLanguageTag(language)));
                }
            } else {
                resp.setErrorMessage(messageSource.getMessage("112", null, Locale.forLanguageTag(language)));
                resp.setErrorCode("112");
                resp.setTimestamp(timestamp);
            }
            return resp;
        }catch(Exception e){
            resp.setTimestamp(timestamp);
            resp.setErrorCode("100");
            resp.setErrorMessage(e.getMessage());
            return resp;
        }
    }

    public LoginResp login (LoginReq user){
        logHelper.printLog("DEBUG","send",user);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        try {
            user.setNip(user.getNip().toUpperCase());
            if (user.getNip().startsWith("U")) {
                if (userDao.existsById(user.getNip())) {
                    Optional<User> current = userDao.findById(user.getNip());
                    if (!"".equals(user.getPassword()) && null != user.getPassword()) {
                        if (user.getPassword().length() == 32) {
                            if (current.get().getPassword().toUpperCase().equals(user.getPassword().toUpperCase())) {
                                loginResp.setErrorCode(Constant.ErrorCode.SUCCESS);
                                loginResp.setErrorMessage(messageSource.getMessage("00", null, Locale.forLanguageTag(language)));
                                loginResp.setTimestamp(timestamp);
                                loginResp.setNama(current.get().getNama());
                                loginResp.setEmail(current.get().getEmail());
                                loginResp.setNip(current.get().getNip());
                            } else {
                                loginResp.setErrorCode("104");
                                loginResp.setErrorMessage(messageSource.getMessage("104", null, Locale.forLanguageTag(language)));
                                loginResp.setTimestamp(timestamp);
                            }
                        }else {
                            loginResp.setErrorMessage(messageSource.getMessage("114", null, Locale.forLanguageTag(language)));
                            loginResp.setErrorCode("114");
                            loginResp.setTimestamp(timestamp);
                        }
                    }else {
                        loginResp.setErrorMessage(messageSource.getMessage("113", null, Locale.forLanguageTag(language)));
                        loginResp.setErrorCode("113");
                        loginResp.setTimestamp(timestamp);
                    }
                }
                else {
                    loginResp.setErrorCode("102");
                    loginResp.setErrorMessage(messageSource.getMessage("102",null,Locale.forLanguageTag(language)));
                    loginResp.setTimestamp(timestamp);
                }
            } else {
                loginResp.setErrorMessage(messageSource.getMessage("112", null, Locale.forLanguageTag(language)));
                loginResp.setErrorCode("112");
                loginResp.setTimestamp(timestamp);
            }
            logHelper.printLog("DEBUG","receive",loginResp);
            logHelper.addLog(user.getNip(),"LOGIN", loginResp.getErrorCode(), loginResp.getErrorMessage());
        }catch (Exception e){
            e.printStackTrace();
            loginResp.setErrorCode("100");
            loginResp.setErrorMessage(e.getMessage());
            loginResp.setTimestamp(timestamp);
            logHelper.addLog(user.getNip(),"LOGIN", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }
        return loginResp;
    }

    public BaseResp addAccount(User user){
        logHelper.printLog("DEBUG","send",user);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        try {
            user.setNip(user.getNip().toUpperCase());
            if (user.getNip().startsWith("U")) {
                if (!"".equals(user.getPassword()) && null != user.getPassword()) {
                    if (user.getPassword().length() == 32) {
                        if (!userDao.existsById(user.getNip())) {
                            userDao.save(user);
                            baseResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
                            baseResp.setErrorCode(Constant.ErrorCode.SUCCESS);
                            baseResp.setTimestamp(timestamp);
                        } else {
                            baseResp.setErrorMessage(messageSource.getMessage("101", null, Locale.forLanguageTag(language)));
                            baseResp.setErrorCode("101");
                            baseResp.setTimestamp(timestamp);
                        }
                    } else {
                        baseResp.setErrorMessage(messageSource.getMessage("114", null, Locale.forLanguageTag(language)));
                        baseResp.setErrorCode("114");
                        baseResp.setTimestamp(timestamp);
                    }
                }else {
                    baseResp.setErrorMessage(messageSource.getMessage("113", null, Locale.forLanguageTag(language)));
                    baseResp.setErrorCode("113");
                    baseResp.setTimestamp(timestamp);
                }
            }else {
                baseResp.setErrorMessage(messageSource.getMessage("112", null, Locale.forLanguageTag(language)));
                baseResp.setErrorCode("112");
                baseResp.setTimestamp(timestamp);
            }
            logHelper.printLog("DEBUG", "receive", baseResp);
            logHelper.addLog(user.getNip(), "ADD ACCOUNT", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }catch (Exception e){
            e.printStackTrace();
            baseResp.setErrorMessage(e.getMessage());
            baseResp.setErrorCode("100");
            baseResp.setTimestamp(timestamp);
            logHelper.addLog(user.getNip(),"ADD ACCOUNT", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }
        return baseResp;
    }

    public BaseResp editAccount(EditUserReq user){
        logHelper.printLog("DEBUG","send",user);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        try {
            user.setNip(user.getNip().toUpperCase());
            if (user.getNip().startsWith("U")) {
                if (userDao.existsById(user.getNip())) {
                    Optional<User> current = userDao.findById(user.getNip());
                    if (user.getNama() != null && !"".equals(user.getNama())) {
                        current.get().setNama(user.getNama());
                    }
                    if (user.getEmail() != null && !"".equals(user.getEmail())) {
                        current.get().setEmail(user.getEmail());
                    }
                    userDao.save(current.get());
                    baseResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
                    baseResp.setErrorCode(Constant.ErrorCode.SUCCESS);
                    baseResp.setTimestamp(timestamp);
                } else {
                    //todo : properties error
                    baseResp.setErrorMessage(messageSource.getMessage("102", null, Locale.forLanguageTag(language)));
                    baseResp.setErrorCode("102");
                    baseResp.setTimestamp(timestamp);
                }
            }else {
                baseResp.setErrorMessage(messageSource.getMessage("112", null, Locale.forLanguageTag(language)));
                baseResp.setErrorCode("112");
                baseResp.setTimestamp(timestamp);
            }
            logHelper.printLog("DEBUG","receive",baseResp);
            logHelper.addLog(user.getNip(),"EDIT ACCOUNT", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }catch (Exception e){
            e.printStackTrace();
            baseResp.setErrorMessage(e.getMessage());
            baseResp.setErrorCode("100");
            baseResp.setTimestamp(timestamp);
            logHelper.addLog(user.getNip(),"EDIT ACCOUNT", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }
        return baseResp;
    }

    public BaseResp resetPassword(ResetPassReq user){
        logHelper.printLog("DEBUG","send",user);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Md5 md5 = new Md5();
        String nip = md5.encryptPass(user.getNipReset() + "123#");
        try {
            user.setNipReset(user.getNipReset().toUpperCase());
            if (user.getNipReset().startsWith("U")) {
                if (userDao.existsById(user.getNipReset())) {
                    //ambil data lama
                    Optional<User> current = userDao.findById(user.getNipReset());
                    current.get().setPassword(nip);
                    userDao.save(current.get());
                    baseResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
                    baseResp.setErrorCode(Constant.ErrorCode.SUCCESS);
                    baseResp.setTimestamp(timestamp);
                } else {
                    baseResp.setErrorMessage(messageSource.getMessage("102", null, Locale.forLanguageTag(language)));
                    baseResp.setErrorCode("102");
                    baseResp.setTimestamp(timestamp);
                }
            }else {
                baseResp.setErrorMessage(messageSource.getMessage("112", null, Locale.forLanguageTag(language)));
                baseResp.setErrorCode("112");
                baseResp.setTimestamp(timestamp);
            }
            logHelper.printLog("DEBUG","receive", baseResp);
            logHelper.addLog(user.getNipOperator(),"RESET PASSWORD for " + user.getNipReset(), baseResp.getErrorCode(), baseResp.getErrorMessage());
        }catch (Exception e){
            e.printStackTrace();
            baseResp.setErrorMessage(e.getMessage());
            baseResp.setErrorCode("100");
            baseResp.setTimestamp(timestamp);
            logHelper.addLog(user.getNipOperator(),"RESET PASSWORD for " + user.getNipReset(), baseResp.getErrorCode(), baseResp.getErrorMessage());
        }
        return baseResp;
    }

    public BaseResp updatePassword(UpdatePassReq user){
        logHelper.printLog("DEBUG","send",user);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        try {
            user.setNip(user.getNip().toUpperCase());
            if (user.getNip().startsWith("U")) {
                if (userDao.existsById(user.getNip())) {
                    if (!"".equals(user.getPassBaru()) && null != user.getPassBaru()) {
                        if (user.getPassBaru().length() == 32) {
                            //cek sama atau gak dengan yang lama
                            if (!user.getPassLama().equals(user.getPassBaru())) {
                                //ambil data lama
                                Optional<User> current = userDao.findById(user.getNip());
                                //cek password baru
                                if (current.get().getPassword().equals(user.getPassLama())) {
                                    current.get().setPassword(user.getPassBaru());
                                    userDao.save(current.get());
                                    baseResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS, null, Locale.forLanguageTag(language)));
                                    baseResp.setErrorCode(Constant.ErrorCode.SUCCESS);
                                    baseResp.setTimestamp(timestamp);
                                } else {
                                    baseResp.setErrorMessage(messageSource.getMessage("105", null, Locale.forLanguageTag(language)));
                                    baseResp.setErrorCode("105");
                                    baseResp.setTimestamp(timestamp);
                                }
                            } else {
                                //todo : properties error
                                baseResp.setErrorMessage(messageSource.getMessage("106", null, Locale.forLanguageTag(language)));
                                baseResp.setErrorCode("106");
                                baseResp.setTimestamp(timestamp);
                            }
                        } else {
                            baseResp.setErrorMessage(messageSource.getMessage("114", null, Locale.forLanguageTag(language)));
                            baseResp.setErrorCode("114");
                            baseResp.setTimestamp(timestamp);
                        }
                    }else {
                        baseResp.setErrorMessage(messageSource.getMessage("113", null, Locale.forLanguageTag(language)));
                        baseResp.setErrorCode("113");
                        baseResp.setTimestamp(timestamp);
                    }

                }else {
                    baseResp.setErrorMessage(messageSource.getMessage("102",null,Locale.forLanguageTag(language)));
                    baseResp.setErrorCode("102");
                    baseResp.setTimestamp(timestamp);
                }
            }else {
                baseResp.setErrorMessage(messageSource.getMessage("112", null, Locale.forLanguageTag(language)));
                baseResp.setErrorCode("112");
                baseResp.setTimestamp(timestamp);
            }
            logHelper.printLog("DEBUG","receive",baseResp);
            logHelper.addLog(user.getNip(),"EDIT PASSWORD", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }catch (Exception e){
            e.printStackTrace();
            baseResp.setErrorMessage(e.getMessage());
            baseResp.setErrorCode("100");
            baseResp.setTimestamp(timestamp);
            logHelper.addLog(user.getNip(),"EDIT PASSWORD", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }
        return baseResp;
    }

    public BaseResp deleteAccount(AccountReq user){
        logHelper.printLog("DEBUG","receive",user);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        try {
            user.setNip(user.getNip().toUpperCase());
            if (user.getNip().startsWith("U")) {
                if (userDao.existsById(user.getNip())) {
                    userDao.deleteById(user.getNip());
                    baseResp.setErrorMessage(messageSource.getMessage(Constant.ErrorCode.SUCCESS,null,Locale.forLanguageTag(language)));
                    baseResp.setErrorCode(Constant.ErrorCode.SUCCESS);
                    baseResp.setTimestamp(timestamp);
                }else {
                    //todo : properties error
                    baseResp.setErrorMessage(messageSource.getMessage("102",null,Locale.forLanguageTag(language)));
                    baseResp.setErrorCode("102");
                    baseResp.setTimestamp(timestamp);
                }
            }else {
                baseResp.setErrorMessage(messageSource.getMessage("112", null, Locale.forLanguageTag(language)));
                baseResp.setErrorCode("112");
                baseResp.setTimestamp(timestamp);
            }
            logHelper.printLog("DEBUG","receive",baseResp);
            logHelper.addLog(user.getNip(),"DELETE ACCOUNT", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }catch (Exception e){
            e.printStackTrace();
            baseResp.setErrorMessage(e.getMessage());
            baseResp.setErrorCode("100");
            baseResp.setTimestamp(timestamp);
            logHelper.addLog(user.getNip(),"DELETE ACCOUNT", baseResp.getErrorCode(), baseResp.getErrorMessage());
        }
        return baseResp;
    }

}
