package com.jdlservice.accountservice;

import com.jdlservice.accountservice.entity.BaseResp;
import com.jdlservice.accountservice.entity.User;
import com.jdlservice.accountservice.entity.account.*;
import com.jdlservice.accountservice.rest.AccountRest;
import com.jdlservice.accountservice.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserTest {
    @Autowired
    AccountRest accountRest;
    HttpServletRequest httpServletRequest;
    @Autowired
    AccountService accountService;
    @Test
        public void loginSuccess() {
 LoginReq loginReq=new LoginReq();
             loginReq.setNip("u067320");
             loginReq.setPassword("test");
             LoginResp loginResp=new LoginResp();
             try {
                 loginResp= accountRest.loginWeb(loginReq, httpServletRequest);
                 System.out.println(loginResp.getErrorMessage());
                 assertEquals("00",loginResp.getErrorCode());
             }catch (Exception e){
                 e.printStackTrace();

             }

        }
    @Test
    public void loginPassFailed() {
        LoginReq loginReq=new LoginReq();
        loginReq.setNip("u067320");
        loginReq.setPassword("test11");
        LoginResp loginResp=new LoginResp();
        try {
            loginResp= accountRest.loginWeb(loginReq, httpServletRequest);
            System.out.println(loginResp.getErrorMessage());
            assertEquals("104",loginResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }

    }
    @Test
    public void loginUserNotFound() {
        LoginReq loginReq=new LoginReq();
        loginReq.setNip("111");
        loginReq.setPassword("test11");
        LoginResp loginResp=new LoginResp();
        try {
            loginResp= accountRest.loginWeb(loginReq, httpServletRequest);
            System.out.println(loginResp.getErrorMessage());
            assertEquals("102",loginResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }
//    @Test
//    public void resetPassSuccess() {
//        ResetPassReq resetPassReq=new ResetPassReq();
//        resetPassReq.setPassLama("myPassword");
//        resetPassReq.setPassBaru("myPASSWORD");
//        resetPassReq.setNip("u011111");
//
//
//        try {
//            BaseResp resetPasswordResp = accountService.resetPassword(resetPassReq);
//            System.out.println(resetPasswordResp.getErrorMessage());
//            assertEquals("00",resetPasswordResp.getErrorCode());
//            // kembalikan passnya
//            resetPassReq.setPassLama("myPASSWORD");
//            resetPassReq.setPassBaru("myPassword");
//            resetPassReq.setNip("u011111");
//            resetPasswordResp = accountService.resetPassword(resetPassReq);
//            assertEquals("00",resetPasswordResp.getErrorCode());
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//    }
//
//    @Test
//    public void resetPassFailedCauseByOldPass() { // old Pass doesnt match
//        ResetPassReq resetPassReq=new ResetPassReq();
//        resetPassReq.setPassLama("myPassworD");
//        resetPassReq.setPassBaru("myPASSWORD");
//        resetPassReq.setNip("u011111");
//
//
//        try {
//            BaseResp resetPasswordResp = accountService.resetPassword(resetPassReq);
//            System.out.println(resetPasswordResp.getErrorMessage());
//            assertEquals("105",resetPasswordResp.getErrorCode());
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//    }
//
//    @Test
//    public void resetPassFailedCauseBySamePass() {
//        ResetPassReq resetPassReq=new ResetPassReq();
//        resetPassReq.setPassLama("myPassword");
//        resetPassReq.setPassBaru("myPassword");
//        resetPassReq.setNip("u011111");
//
//
//        try {
//            BaseResp resetPasswordResp = accountService.resetPassword(resetPassReq);
//            System.out.println(resetPasswordResp.getErrorMessage());
//            assertEquals("106",resetPasswordResp.getErrorCode());
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//    }
//    @Test
//    public void resetPassFailedCauseByUserNotExist() {
//        ResetPassReq resetPassReq=new ResetPassReq();
//        resetPassReq.setPassLama("myPassword");
//        resetPassReq.setPassBaru("myPASSWORD");
//        resetPassReq.setNip("u0111");
//
//
//        try {
//            BaseResp resetPasswordResp = accountService.resetPassword(resetPassReq);
//            System.out.println(resetPasswordResp.getErrorMessage());
//            assertEquals("102",resetPasswordResp.getErrorCode());
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//    }

    @Test
    public void addDeleteSuccess() {
        User addAccountReq=new User();
        addAccountReq.setEmail("test@test.com");
        addAccountReq.setNama("User Test");
        addAccountReq.setNip("u012345");
        addAccountReq.setPassword("thePassword");

        AccountReq delAccountReq=new AccountReq();
        delAccountReq.setNip("u012345");

        try {
            BaseResp addAccountResp = accountRest.addAccount(addAccountReq,httpServletRequest);
            System.out.println(addAccountResp.getErrorMessage());
            assertEquals("00",addAccountResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }



    @Test
    public void addUserFailedCausedUserExist() {
        User addAccountReq=new User();
        addAccountReq.setEmail("test@test.com");
        addAccountReq.setNama("User Test");
        addAccountReq.setNip("u012345");
        addAccountReq.setPassword("thePassword");

        try {
            BaseResp addAccountResp = accountRest.addAccount(addAccountReq,httpServletRequest);
            System.out.println(addAccountResp.getErrorMessage());
            assertEquals("101",addAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void DeleteUserSuccess() {
        AccountReq delAccountReq=new AccountReq();
        delAccountReq.setNip("u012345");

        try {
            BaseResp delAccountResp = accountRest.deleteAccount(delAccountReq,httpServletRequest);
            System.out.println(delAccountResp.getErrorMessage());
            assertEquals("00",delAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void DeleteUserFailed() {
        AccountReq delAccountReq=new AccountReq();
        delAccountReq.setNip("u011111");

        try {
            BaseResp delAccountResp = accountRest.deleteAccount(delAccountReq,httpServletRequest);
            System.out.println(delAccountResp.getErrorMessage());
            assertEquals("102",delAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void EditUserSuccess() {
        EditUserReq editAccountReq=new EditUserReq();
        editAccountReq.setNip("u011111");
        editAccountReq.setNama("Unit Test User");



        try {
            BaseResp editAccountResp = accountRest.editAccount(editAccountReq,httpServletRequest);
            System.out.println(editAccountResp.getErrorMessage());
            assertEquals("00",editAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }
    @Test
    public void EditUserFailed() {
        EditUserReq editAccountReq=new EditUserReq();
        editAccountReq.setNip("u01");
        editAccountReq.setNama("Unit Test User");
         try {
            BaseResp editAccountResp = accountRest.editAccount(editAccountReq,httpServletRequest);
            System.out.println(editAccountResp.getErrorMessage());
            assertEquals("102",editAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    }
