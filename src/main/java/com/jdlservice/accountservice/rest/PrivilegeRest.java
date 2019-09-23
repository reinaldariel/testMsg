package com.jdlservice.accountservice.rest;

import com.google.gson.Gson;
import com.jdlservice.accountservice.entity.BaseResp;
import com.jdlservice.accountservice.entity.PrivilegeId;
import com.jdlservice.accountservice.entity.privilege.PrivilegeReq;
import com.jdlservice.accountservice.entity.privilege.PrivilegeResp;
import com.jdlservice.accountservice.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/privilege")
public class PrivilegeRest {
    @Autowired
    PrivilegeService privilegeService;
    private BaseResp baseResp = new BaseResp();

    @PostMapping(value = "/getPrivilege")
    public @ResponseBody
    PrivilegeResp loginWeb(
            @RequestBody(required=true) PrivilegeId input,
            HttpServletRequest request) throws Exception {
        PrivilegeResp resp = new PrivilegeResp();
        resp = privilegeService.getPrivilege(input);
        return resp;
    }
    @PostMapping(value = "/grantAdmin")
    public @ResponseBody
    BaseResp grantAdmin(
            @RequestBody(required=true) PrivilegeId input,
            HttpServletRequest request) throws Exception {

        baseResp = privilegeService.grantAdmin(input);
        return baseResp;
    }
    @PostMapping(value = "/grantUser")
    public @ResponseBody
    BaseResp grantUser(
            @RequestBody(required=true) PrivilegeId input,
            HttpServletRequest request) throws Exception {

        baseResp = privilegeService.grantUser(input);
        return baseResp;
    }
}
