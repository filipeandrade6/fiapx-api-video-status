package org.jfm.bootloader;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@ApplicationScoped
public class SqsClientProducer {
  
  @Produces
    public SqsClient sqsClient() {
      return SqsClient.builder()
          .region(Region.of("us-east-1")) 
          .credentialsProvider(DefaultCredentialsProvider.create()) 
          .build();
    }
}
