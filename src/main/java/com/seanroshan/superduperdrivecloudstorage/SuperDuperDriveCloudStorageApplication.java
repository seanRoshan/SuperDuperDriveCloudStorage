package com.seanroshan.superduperdrivecloudstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;

@SpringBootApplication
@FlywayDataSource
public class SuperDuperDriveCloudStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperDuperDriveCloudStorageApplication.class, args);
    }

}
