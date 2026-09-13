package com.interviewportal.smartportal;

import com.interviewportal.smartportal.database.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartportalApplication {

    public static void main(String[] args) {

        SpringApplication.run(SmartportalApplication.class, args);

        DBConnection.getConnection();
    }

}