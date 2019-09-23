package com.jdlservice.accountservice.entity;

public class Constant {

    static public class ErrorCode {
        public static final String SUCCESS = "00";

        public static final String GENERAL_FAILED = "100";
        public static final String GENERAL_BACKEND_FAILED = "300";

        public static final String TIMEOUT = "690";
        public static final String BACKEND_FAILED = "698";
        public static final String VALIDATION_FAILED = "699";

        static public class MyTransactionService {
            public static final String EBANK_STATUS_NOT_ACTIVE = "508";
            public static final String FLAG_FIN_N = "509";
        }
    }
    static public class ErrorMessage{
        public static final String SUCCESS = "Success";
        public static final String GENERAL_FAILED = "Transaction Cannot be Done";
        public static final String GENERAL_BACKEND_FAILED = "Transaction Cannot be Done";
    }

    static public class PrivilegeType{
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }
}
