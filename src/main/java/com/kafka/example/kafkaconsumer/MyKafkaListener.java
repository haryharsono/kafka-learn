package com.kafka.example.kafkaconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener implements CommandLineRunner {

//    @KafkaListener(topics = "TEST-TOPIC-partisi-3")
//    public void listen(String message) {
//        System.out.println("Received message: " + message);
//        // do something with the message
//    }
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MyKafkaListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${myapp.message}")
    private String message;



    @Scheduled(fixedDelay = 60000)
    public void sendMessage(int a) {
            kafkaTemplate.send("TEST-TOPIC-partisi-3", "message from "+message+" -> -"+a);
        }

    @Override
    public void run(String... args) throws Exception {
        for (int a=0; a<=1000; a++){
            sendMessage(a);
            System.out.print("message -> "+a);
            Thread.sleep(3000);
        }
    }
}
