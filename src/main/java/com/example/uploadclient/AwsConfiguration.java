package com.example.uploadclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class AwsConfiguration {

    @Bean
    public S3Client s3Client() throws URISyntaxException {
        URI endpointOverride = new URI("http://localhost:4566");
        return S3Client.builder()
                .endpointOverride(endpointOverride)
                .build();
    }
}
