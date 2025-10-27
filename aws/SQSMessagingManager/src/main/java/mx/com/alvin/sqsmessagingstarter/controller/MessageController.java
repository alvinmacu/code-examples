package mx.com.alvin.sqsmessagingstarter.controller;

import mx.com.alvin.sqsmessagingstarter.service.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageSenderService messageSenderService;

    @PostMapping("/send")
    public void sendMessage(@RequestParam String message) {
        messageSenderService.send(message);
    }
}
