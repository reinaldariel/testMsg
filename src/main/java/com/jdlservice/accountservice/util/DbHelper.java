package com.jdlservice.accountservice.util;


import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.net.Socket;
import java.util.List;

@Service
public class DbHelper {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Object[]> callableProcedure(String spName, String[] param){
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(spName);
        for (int i=1;i<=param.length;i++) {
            storedProcedure.registerStoredProcedureParameter(i, String.class, ParameterMode.IN);
            storedProcedure.setParameter(1, param[i-1]);
        }
        storedProcedure.execute();
        // Call the stored procedure.
        List<Object[]> storedProcedureResults = storedProcedure.getResultList();
        return  storedProcedureResults;
    }

    public List<Object[]> callableQuery(String query, String[] value){
        System.out.println(query);
        Query myQuery = entityManager.createNativeQuery(query);
        for (int i=1;i<=value.length;i++) {
            myQuery.setParameter(i, value[i-1]);
        }
        System.out.println(myQuery);
        List<Object[]> storedProcedureResults = myQuery.getResultList();

        return  storedProcedureResults;
    }

    public List<Object[]> callableQuery(String query, List<String> value){
        System.out.println(query);
        Query myQuery = entityManager.createNativeQuery(query);
        for (int i=1;i<=value.size();i++) {
            myQuery.setParameter(i, value.get(i-1));
        }
        System.out.println(myQuery);
        List<Object[]> storedProcedureResults = myQuery.getResultList();

        return  storedProcedureResults;
    }

    public List<Object[]> callableQuery(String query, String [] key ,String[] value) throws  Exception{
        Query myQuery = entityManager.createNativeQuery(query);
        if(key.length == value.length) {
            for (int i = 0; i < value.length; i++) {
                myQuery.setParameter(key[i], value[i]);
            }
            List<Object[]> storedProcedureResults = myQuery.getResultList();
            return  storedProcedureResults;
        }else{
            throw new Exception("key and value size not match");
        }
    }
    public static String  serverListening(String host, int port)
    {
        Socket s = null;
        try
        {
            s = new Socket(host, port);
            return "true";
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        finally
        {
            if(s != null)
                try {s.close();}
                catch(Exception e){}
        }
    }
}
