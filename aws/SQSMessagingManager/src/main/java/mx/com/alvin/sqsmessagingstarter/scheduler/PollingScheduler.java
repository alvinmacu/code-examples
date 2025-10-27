package mx.com.alvin.sqsmessagingstarter.scheduler;

import mx.com.alvin.sqsmessagingstarter.service.MessageReceiverService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PollingScheduler {
    private final MessageReceiverService receiver;

    public PollingScheduler(MessageReceiverService receiver) {
        this.receiver = receiver;
    }

    @Scheduled(fixedDelay = 3000)
    public void pollQueue() {
        System.out.println("POLLING_SCHEDULER");
        receiver.receiveAndDeleteMessages();
    }
}
