package mx.com.alvin.sqsmessagingstarter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SQSConfiguration {

    @Value("${aws.sqs.access_key_id}")
    private String awsAccessKeyId;

    @Value("${aws.sqs.secret_key_id}")
    private String awsSecretKeyId;

    @Value("${aws.sqs.region.static}")
    private String region;

    @Bean
    public SqsClient sqsClient() {
        AwsBasicCredentials creds = AwsBasicCredentials.create(awsAccessKeyId, awsSecretKeyId);
        AwsCredentialsProvider provider = StaticCredentialsProvider.create(creds);

        return SqsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(provider)
                .build();
    }


}

