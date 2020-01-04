package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.dto.MsgDto;
import com.blbz.fundooapi.service.Publisher;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublisherImpl implements Publisher {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;
    @Override
    public void produceMsg(MsgDto msgDto){
        amqpTemplate.convertAndSend(exchange, routingKey, msgDto);
        System.out.println("Send msg = " + msgDto);
    }
}