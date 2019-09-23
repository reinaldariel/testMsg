package com.jdlservice.accountservice;

import com.jdlservice.accountservice.dao.UserDao;
import com.jdlservice.accountservice.entity.BaseResp;
import com.jdlservice.accountservice.entity.User;
import com.jdlservice.accountservice.entity.account.*;
import com.jdlservice.accountservice.service.AccountService;
import com.jdlservice.accountservice.util.LogHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserMockitoTest {
    @Mock
    LogHelper logHelper;
    @Spy
    MessageSource messageSource;
    @InjectMocks
    @Autowired
    AccountService accountService;
    @Mock
    UserDao userDao;

    private User user1 = new User("U067320","test1@gmail","test1","testtesttestertyuioplkjhgfdshss1");
    private User user2 = new User("U044095","test1@gmail","test1","098f6bcd4621d373cade4e832627b4f6");

    @Test
    public void getUserByNipSuccess() {
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));

        AccountReq accountReq=new AccountReq();
        accountReq.setNip("U067320");
        AccountResp accountResp = new AccountResp();
        try {
            accountResp = accountService.getUserByNip(accountReq);
            System.out.println(accountResp.getErrorMessage());
            assertEquals("00",accountResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void getUserByNipFailedCauseByUserNotExist() {
        AccountReq accountReq=new AccountReq();
        accountReq.setNip("U06732");
        AccountResp accountResp = new AccountResp();
        try {
            accountResp = accountService.getUserByNip(accountReq);
            assertEquals("102",accountResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void getUserByNipFailedCauseByNIPNotAppropriate() {
        AccountReq accountReq=new AccountReq();
        accountReq.setNip("106732");
        AccountResp accountResp = new AccountResp();
        try {
            accountResp = accountService.getUserByNip(accountReq);
            assertEquals("112",accountResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
        public void loginSuccess() {
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));

        LoginReq loginReq=new LoginReq();
             loginReq.setNip("U067320");
             loginReq.setPassword("testtesttestertyuioplkjhgfdshss1");
             LoginResp loginResp=new LoginResp();
             try {
                 loginResp= accountService.login(loginReq);
                 assertEquals("00",loginResp.getErrorCode());
             }catch (Exception e){
                 e.printStackTrace();

             }

        }

    @Test
    public void loginPassFailedCausedByNIPNotAppropriate() {
        LoginReq loginReq = new LoginReq();
        loginReq.setNip("1067320");
        loginReq.setPassword("testtesttestertyuioplkjhgfdshss1");
        LoginResp loginResp = new LoginResp();
        try {
            loginResp = accountService.login(loginReq);
            assertEquals("112", loginResp.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void loginUserNotFound() {
        LoginReq loginReq=new LoginReq();
        loginReq.setNip("U12312");
        loginReq.setPassword("testtesttestertyuioplkjhgfdshss1");
        LoginResp loginResp=new LoginResp();
        try {
            loginResp = accountService.login(loginReq);
            assertEquals("102",loginResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void loginPassFailedCausedByPasswordEmpty() {
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));
        LoginReq loginReq = new LoginReq();
        loginReq.setNip("U067320");
        loginReq.setPassword("");
        LoginResp loginResp = new LoginResp();
        try {
            loginResp = accountService.login(loginReq);
            assertEquals("113", loginResp.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void loginPassFailedCausedByPasswordNull() {
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));
        LoginReq loginReq = new LoginReq();
        loginReq.setNip("U067320");
        LoginResp loginResp = new LoginResp();
        try {
            loginResp = accountService.login(loginReq);
            assertEquals("113", loginResp.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void loginPassFailedCausedByPasswordNotEncrypted() {
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));
        LoginReq loginReq = new LoginReq();
        loginReq.setNip("U067320");
        loginReq.setPassword("qqq");
        LoginResp loginResp = new LoginResp();
        try {
            loginResp = accountService.login(loginReq);
            assertEquals("114", loginResp.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void loginPassFailedCausedByWrongPassword() {
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));
        LoginReq loginReq = new LoginReq();
        loginReq.setNip("U067320");
        loginReq.setPassword("testtesttestertyuioplkjhgfdshs23");
        LoginResp loginResp = new LoginResp();
        try {
            loginResp = accountService.login(loginReq);
            assertEquals("104", loginResp.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void updatePassSuccess() {
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));

        UpdatePassReq updatePassReq=new UpdatePassReq();
        updatePassReq.setPassLama("testtesttestertyuioplkjhgfdshss1");
        updatePassReq.setPassBaru("testtesttestertyuioplkjhgfdshs12");
        updatePassReq.setNip("U067320");


        try {
            BaseResp updatePasswordResp = accountService.updatePassword(updatePassReq);
            assertEquals("00",updatePasswordResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void updatePassFailedCauseByNIPNotAppropriate() {
        UpdatePassReq updatePassReq=new UpdatePassReq();
        updatePassReq.setPassLama("test");
        updatePassReq.setPassBaru("myPASSWORD");
        updatePassReq.setNip("1067321");


        try {
            BaseResp updatePasswordResp = accountService.updatePassword(updatePassReq);
            assertEquals("112",updatePasswordResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void updatePassFailedCauseByUserNotExist() {
        UpdatePassReq updatePassReq=new UpdatePassReq();
        updatePassReq.setPassLama("test");
        updatePassReq.setPassBaru("myPASSWORD");
        updatePassReq.setNip("U067321");


        try {
            BaseResp updatePasswordResp = accountService.updatePassword(updatePassReq);
            assertEquals("102",updatePasswordResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void updatePassFailedCauseByPasswordEmpty() {
        when(userDao.existsById("U067320")).thenReturn(true);

        UpdatePassReq updatePassReq=new UpdatePassReq();
        updatePassReq.setPassLama("");
        updatePassReq.setPassBaru("");
        updatePassReq.setNip("U067320");


        try {
            BaseResp updatePasswordResp = accountService.updatePassword(updatePassReq);
            assertEquals("113",updatePasswordResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void updatePassFailedCauseByPasswordNull() {
        when(userDao.existsById("U067320")).thenReturn(true);

        UpdatePassReq updatePassReq=new UpdatePassReq();
        updatePassReq.setNip("U067320");


        try {
            BaseResp updatePasswordResp = accountService.updatePassword(updatePassReq);
            assertEquals("113",updatePasswordResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void updatePassFailedCauseByPasswordNotEncrypted() {
        when(userDao.existsById("U067320")).thenReturn(true);

        UpdatePassReq updatePassReq=new UpdatePassReq();
        updatePassReq.setPassLama("123");
        updatePassReq.setPassBaru("123");
        updatePassReq.setNip("U067320");


        try {
            BaseResp updatePasswordResp = accountService.updatePassword(updatePassReq);
            assertEquals("114",updatePasswordResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void updatePassFailedCauseBySamePass() {
        when(userDao.existsById("U067320")).thenReturn(true);
        UpdatePassReq updatePassReq=new UpdatePassReq();
        updatePassReq.setPassLama("testtesttestertyuioplkjhgfdshss2");
        updatePassReq.setPassBaru("testtesttestertyuioplkjhgfdshss2");
        updatePassReq.setNip("U067320");


        try {
            BaseResp updatePasswordResp = accountService.updatePassword(updatePassReq);
            assertEquals("106",updatePasswordResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void updatePassFailedCauseByOldPassNotMatch() { // old Pass doesnt match
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));
        UpdatePassReq updatePassReq=new UpdatePassReq();
        updatePassReq.setPassLama("testtesttestertyuioplkjhgfdshss12");
        updatePassReq.setPassBaru("testtesttestertyuioplkjhgfdsh123");
        updatePassReq.setNip("U067320");


        try {
            BaseResp updatePasswordResp = accountService.updatePassword(updatePassReq);
            assertEquals("105",updatePasswordResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void resetPassSuccess() {
        when(userDao.existsById("U044095")).thenReturn(true);
        when(userDao.findById("U044095")).thenReturn(java.util.Optional.ofNullable(user2));
        ResetPassReq resetPassReq=new ResetPassReq();
        resetPassReq.setNipReset("U044095");
        resetPassReq.setNipOperator("U067320");

        try {
            BaseResp resetPasswordResp = accountService.resetPassword(resetPassReq);
            assertEquals("00",resetPasswordResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void resetPassFailedCauseByNipNotAppropriate() {
        ResetPassReq resetPassReq=new ResetPassReq();
        resetPassReq.setNipReset("1044095");
        resetPassReq.setNipOperator("U067320");

        try {
            BaseResp resetPasswordResp = accountService.resetPassword(resetPassReq);
            assertEquals("112",resetPasswordResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void resetPassFailedCauseByUserNotFound() {
        when(userDao.existsById("U044095")).thenReturn(false);
        ResetPassReq resetPassReq=new ResetPassReq();
        resetPassReq.setNipReset("U044095");
        resetPassReq.setNipOperator("U067320");

        try {
            BaseResp resetPasswordResp = accountService.resetPassword(resetPassReq);
            assertEquals("102",resetPasswordResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void addSuccess() {
//        when(userDao.existsById("1")).thenReturn(true);
        User addAccountReq=new User();
        addAccountReq.setEmail("test@test.com");
        addAccountReq.setNama("User Test");
        addAccountReq.setNip("u067320");
        addAccountReq.setPassword("thePasswordthePasswordthePasswor");

//        AccountReq delAccountReq=new AccountReq();
//        delAccountReq.setNip("u012345");

        try {
            BaseResp addAccountResp = accountService.addAccount(addAccountReq);
            assertEquals("00",addAccountResp.getErrorCode());
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void addUserFailedCausedByUserExist() {
        when(userDao.existsById("U067320")).thenReturn(true);
        User addAccountReq=new User();
        addAccountReq.setEmail("test@test.com");
        addAccountReq.setNama("User Test");
        addAccountReq.setNip("u067320");
        addAccountReq.setPassword("thePasswordthePasswordthePasswor");

        try {
            BaseResp addAccountResp = accountService.addAccount(addAccountReq);
            assertEquals("101",addAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }
    @Test
    public void addUserFailedCausedByNipNotSuitable() {
        User addAccountReq=new User();
        addAccountReq.setEmail("test@test.com");
        addAccountReq.setNama("User Test");
        addAccountReq.setNip("Q067320");
        addAccountReq.setPassword("thePasswordthePasswordthePasswor");

        try {
            BaseResp addAccountResp = accountService.addAccount(addAccountReq);
            assertEquals("112",addAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void addUserFailedCausedByPasswordEmptyString() {
        User addAccountReq=new User();
        addAccountReq.setEmail("test@test.com");
        addAccountReq.setNama("User Test");
        addAccountReq.setNip("U067320");
        addAccountReq.setPassword("");

        try {
            BaseResp addAccountResp = accountService.addAccount(addAccountReq);
            assertEquals("113",addAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void addUserFailedCausedByPasswordNull() {
        User addAccountReq=new User();
        addAccountReq.setEmail("test@test.com");
        addAccountReq.setNama("User Test");
        addAccountReq.setNip("U067320");

        try {
            BaseResp addAccountResp = accountService.addAccount(addAccountReq);
            assertEquals("113",addAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void addUserFailedCausedByPasswordNotEncryptedByMD5() {
        User addAccountReq=new User();
        addAccountReq.setEmail("test@test.com");
        addAccountReq.setNama("User Test");
        addAccountReq.setNip("U067320");
        addAccountReq.setPassword("test");

        try {
            BaseResp addAccountResp = accountService.addAccount(addAccountReq);
            assertEquals("114",addAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void DeleteUserSuccess() {
        when(userDao.existsById("U067320")).thenReturn(true);
        AccountReq delAccountReq=new AccountReq();
        delAccountReq.setNip("U067320");

        try {
            BaseResp delAccountResp = accountService.deleteAccount(delAccountReq);
            assertEquals("00",delAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void DeleteUserFailedCausedByUserNotFound() {
        AccountReq delAccountReq=new AccountReq();
        delAccountReq.setNip("U067332");

        try {
            BaseResp delAccountResp = accountService.deleteAccount(delAccountReq);
            assertEquals("102",delAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void DeleteUserFailedCausedByNIPNotAppropriate() {
        AccountReq delAccountReq=new AccountReq();
        delAccountReq.setNip("1067332");

        try {
            BaseResp delAccountResp = accountService.deleteAccount(delAccountReq);
            assertEquals("112",delAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void EditUserSuccess() {
        when(userDao.existsById("U067320")).thenReturn(true);
        when(userDao.findById("U067320")).thenReturn(java.util.Optional.of(user1));
        EditUserReq editAccountReq=new EditUserReq();
        editAccountReq.setNip("U067320");
        editAccountReq.setNama("Unit Test User");

        try {
            BaseResp editAccountResp = accountService.editAccount(editAccountReq);
            assertEquals("00",editAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void EditUserFailedCausedByUserNotFound() {
        EditUserReq editAccountReq=new EditUserReq();
        editAccountReq.setNip("U5");
        editAccountReq.setNama("Unit Test User");
         try {
             BaseResp editAccountResp = accountService.editAccount(editAccountReq);
            assertEquals("102",editAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void EditUserFailedCausedByNIPNotAppropriate() {
        EditUserReq editAccountReq=new EditUserReq();
        editAccountReq.setNip("5");
        editAccountReq.setNama("Unit Test User");
        try {
            BaseResp editAccountResp = accountService.editAccount(editAccountReq);
            assertEquals("112",editAccountResp.getErrorCode());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
