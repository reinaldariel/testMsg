package com.jdlservice.accountservice;

import com.jdlservice.accountservice.dao.PrivilegeDao;
import com.jdlservice.accountservice.dao.UserDao;
import com.jdlservice.accountservice.entity.BaseResp;
import com.jdlservice.accountservice.entity.Privilege;
import com.jdlservice.accountservice.entity.PrivilegeId;
import com.jdlservice.accountservice.entity.User;
import com.jdlservice.accountservice.entity.account.*;
import com.jdlservice.accountservice.entity.privilege.PrivilegeResp;
import com.jdlservice.accountservice.service.AccountService;
import com.jdlservice.accountservice.service.PrivilegeService;
import com.jdlservice.accountservice.util.LogHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PrivilegeMockitoTest {
    @Mock
    LogHelper logHelper;
    @Mock
    MessageSource messageSource;
    @InjectMocks
    @Autowired
    PrivilegeService privilegeService;
    @Mock
    UserDao userDao;
    @Mock
    PrivilegeDao privilegeDao;

    private User user1 = new User("1","test1@gmail","test1","test");
    private Privilege privilege1 = new Privilege("1","IREIMBURSE","ADMIN");
    private PrivilegeId privilegeId1 = new PrivilegeId("1","IREIMBURSE");
    private PrivilegeId privilegeId2 = new PrivilegeId("2","IREIMBURSE");

    @Test
    public void getPrivilegeSuccessAdmin() {
        when(userDao.existsById("1")).thenReturn(true);
        when(privilegeDao.existsById(privilegeId1)).thenReturn(true);
        when(privilegeDao.findById(privilegeId1)).thenReturn(java.util.Optional.of(privilege1));

        PrivilegeResp privilegeResp = new PrivilegeResp();
        try {
            privilegeResp = privilegeService.getPrivilege(privilegeId1);
            assertEquals("ADMIN",privilegeResp.getHakAkses());
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Test
    public void getPrivilegeSuccessUser() {
        when(userDao.existsById("2")).thenReturn(true);
        when(privilegeDao.existsById(privilegeId2)).thenReturn(false);

        PrivilegeResp privilegeResp = new PrivilegeResp();
        try {
            privilegeResp = privilegeService.getPrivilege(privilegeId2);
            assertEquals("USER",privilegeResp.getHakAkses());
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Test
    public void getPrivilegeFailedCauseByNotFound() {
        when(userDao.existsById("2")).thenReturn(false);

        PrivilegeResp privilegeResp = new PrivilegeResp();
        try {
            privilegeResp = privilegeService.getPrivilege(privilegeId2);
            assertEquals("102",privilegeResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }

    }
    @Test
    public void grantAdminFailedCauseByNotFound() {
        when(userDao.existsById("2")).thenReturn(false);

        BaseResp privilegeResp = new BaseResp();
        try {
            privilegeResp = privilegeService.grantAdmin(privilegeId2);
            assertEquals("102",privilegeResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }

    }
    @Test
    public void grantUserFailedCauseByNotFound() {
        when(userDao.existsById("2")).thenReturn(false);

        BaseResp privilegeResp = new BaseResp();
        try {
            privilegeResp = privilegeService.grantUser(privilegeId2);
            assertEquals("102",privilegeResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Test
    public void grantAdminSuccess() {
        when(userDao.existsById("1")).thenReturn(true);

        BaseResp privilegeResp = new BaseResp();
        try {
            privilegeResp = privilegeService.grantAdmin(privilegeId1);
            assertEquals("00",privilegeResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @Test
    public void grantUserSuccess() {
        when(userDao.existsById("1")).thenReturn(true);

        BaseResp privilegeResp = new BaseResp();
        try {
            privilegeResp = privilegeService.grantUser(privilegeId1);
            assertEquals("00",privilegeResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
