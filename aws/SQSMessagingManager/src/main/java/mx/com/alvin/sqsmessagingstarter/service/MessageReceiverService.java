package mx.com.alvin.sqsmessagingstarter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.List;

@Service
public class MessageReceiverService {
    private final SqsClient sqsClient;

    @Value("${app.sqs.queue-url}")
    private String queueUrl;

    public MessageReceiverService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public List<Message> receiveAndDeleteMessages() {
        System.out.println( "SEARCHING_MESSAGES" );
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(10)      // up to 10
                .waitTimeSeconds(20)          // long polling
                .build();

        ReceiveMessageResponse response = sqsClient.receiveMessage(receiveRequest);
        List<Message> messages = response.messages();

        for (Message msg : messages) {
            // 1) process the message
            System.out.println("RECEIVED: " + msg.body());

            // 2) delete it to acknowledge
            DeleteMessageRequest deleteReq = DeleteMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .receiptHandle(msg.receiptHandle())
                    .build();
            sqsClient.deleteMessage(deleteReq);
        }

        return messages;
    }
}
