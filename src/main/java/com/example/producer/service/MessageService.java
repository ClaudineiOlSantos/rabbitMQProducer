package com.example.producer.service;

import com.example.producer.producer.Message;
import com.example.producer.producer.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Publisher publisher;

    public void sendMessage(final Message message,int num){
        publisher.sendMessage(message,num);
    }
}
