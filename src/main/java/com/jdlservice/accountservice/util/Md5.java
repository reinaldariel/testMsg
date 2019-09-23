package com.jdlservice.accountservice.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class Md5 {
    public String encryptPass(String password){
        String myHash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return myHash;
    }
}
