package mx.com.alvin.sqsmessagingstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class MessageSenderService {
    private final SqsClient sqsClient;

    @Value("${app.sqs.queue-url}")
    private String queueUrl;

    @Autowired
    public MessageSenderService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public String send(String body) {
        System.out.println("MESSAGE_SENDED: " + body);
        var request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(body)
                .build();
        return sqsClient.sendMessage(request)
                .messageId();
    }
}
