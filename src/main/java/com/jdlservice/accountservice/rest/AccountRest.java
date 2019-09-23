package com.jdlservice.accountservice.rest;

import com.jdlservice.accountservice.entity.BaseResp;
import com.jdlservice.accountservice.entity.User;
import com.jdlservice.accountservice.entity.account.*;
import com.jdlservice.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AccountRest {

@Autowired
AccountService service;
//public AccountService as;
    @PostMapping(value = "/login")
    public @ResponseBody
    LoginResp loginWeb(
            @RequestBody(required=true) LoginReq input,
            HttpServletRequest request) throws Exception {
        LoginResp ar = new LoginResp();
        ar = service.login(input);
        return ar;
    }

    @PostMapping(value = "/getByNip")
    public @ResponseBody
    AccountResp getUser(@RequestBody(required=true) AccountReq input,
            HttpServletRequest request) throws Exception {
        AccountResp ar = new AccountResp();
        ar = service.getUserByNip(input);
        return ar;
    }

    @PostMapping(value = "/signUp")
    public @ResponseBody
    BaseResp addAccount(@RequestBody(required=true) User input,
                        HttpServletRequest request) throws Exception {
        BaseResp respon = new BaseResp();
        respon = service.addAccount(input);
        return respon;
    }

    @PutMapping(value = "/updateUser")
    public @ResponseBody
    BaseResp editAccount(@RequestBody(required=true) EditUserReq input,
                        HttpServletRequest request) throws Exception {
        BaseResp respon = new BaseResp();
        respon = service.editAccount(input);
        return respon;
    }

    @PutMapping(value = "/updatePassword")
    public @ResponseBody
    BaseResp updatePassword(@RequestBody(required=true) UpdatePassReq input,
                         HttpServletRequest request) throws Exception {
        BaseResp respon = new BaseResp();
        respon = service.updatePassword(input);
        return respon;
    }

    @PutMapping(value = "/resetPassword")
    public @ResponseBody
    BaseResp resetPassword(@RequestBody(required=true) ResetPassReq input,
                            HttpServletRequest request) throws Exception {
        BaseResp respon = new BaseResp();
        respon = service.resetPassword(input);
        return respon;
    }

    @DeleteMapping(value = "/deleteUser")
    public @ResponseBody
    BaseResp deleteAccount(
            @RequestBody(required=true) AccountReq input,
            HttpServletRequest request) throws Exception {
        BaseResp respon = new BaseResp();
        respon = service.deleteAccount(input);
        return respon;
    }
}
