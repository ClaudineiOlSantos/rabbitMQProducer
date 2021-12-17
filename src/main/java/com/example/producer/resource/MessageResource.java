package com.example.producer.resource;

import com.example.producer.producer.Message;
import com.example.producer.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageResource {

    private final MessageService messageService;

    @PostMapping
    public void sendMessage(@RequestBody final Message message) {
        Random random = new Random();

        int num = random.nextInt();
        messageService.sendMessage(message, num);
    }
}
