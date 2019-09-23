package com.jdlservice.accountservice.util;

import com.google.gson.Gson;
import com.jdlservice.accountservice.dao.LogDao;
import com.jdlservice.accountservice.entity.LogTrx;
import com.jdlservice.accountservice.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LogHelper {
    private static final Logger logger = LogManager.getLogger(AccountService.class);
    private Gson gson = new Gson();

    private LogTrx log = new LogTrx();

    @Autowired
    LogDao logDao;
    public void addLog(String nip, String activity, String errorCode, String errorMessage){
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        log.setNip(nip);
        log.setErrorCode(errorCode);
        log.setErrorMessage(errorMessage);
        log.setActivity(activity);
        log.setCreatedDate(timestamp);
        System.out.println(gson.toJson(log));
        logDao.save(log);
    }

    public void printLog(String level, String message, Object object){
        if(level.toUpperCase().equals("DEBUG")){
            logger.debug(message+"|"+gson.toJson(object));
        } else if (level.toUpperCase().equals("INFO")){
            logger.info(message+"|"+gson.toJson(object));
        } else if (level.toUpperCase().equals("ERROR")){
            logger.error(message+"|"+gson.toJson(object));
        } else {
            logger.debug(message+"|"+gson.toJson(object));
        }
    }
    public void printLog(String level, String message){
        if(level.toUpperCase().equals("DEBUG")){
            logger.debug(message);
        } else if (level.toUpperCase().equals("INFO")){
            logger.info(message);
        } else if (level.toUpperCase().equals("ERROR")){
            logger.error(message);
        } else {
            logger.debug(message);
        }
    }
}
