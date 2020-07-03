package com.seanroshan.superduperdrivecloudstorage.services.api;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import org.springframework.stereotype.Service;

@Service
public class S3Service {

    String bucketName = "super.duper.drive.cloud.storage";

    AWSCredentials credentials = new BasicAWSCredentials(
            "",
            ""
    );

    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_WEST_2)
            .build();

    public ObjectListing listObjects(String folderKey) {
        return s3client.listObjects(bucketName, folderKey);
    }


}
