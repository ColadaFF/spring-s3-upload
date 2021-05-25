package com.example.uploadclient;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileStorage {
    private final S3Client s3Client;

    public FileStorage(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    public void uploadFile(String filename, InputStream inputStream, long size) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("some-bucket")
                .key(filename)
                .build();
        RequestBody requestBody = RequestBody.fromInputStream(inputStream, size);
        s3Client.putObject(request, requestBody);
    }

    public List<String> listFiles() {
        ListObjectsV2Request build = ListObjectsV2Request.builder()
                .bucket("some-bucket")
                .build();
        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(build);
        return listObjectsV2Response.contents()
                .stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }
}
