package com.jdlservice.accountservice.util;

import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class AppConfig {
    @Bean
    public ResourceBundleMessageSource messageSource() {

        var source = new ResourceBundleMessageSource();
        source.setBasenames("https://github.com/reinaldariel/testMsg/blob/master/messages/label_in.properties");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    public static String environment;

    static{
        try{
            load();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void reload() throws IOException{
        load();
    }


    public static void load() throws IOException{

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";
        try (InputStream input = new FileInputStream(appConfigPath)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            environment = prop.getProperty("environment");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getEnvironment(){
        return environment;
    }
}
