package com.cader.govtech.selection.controllers;

import com.cader.govtech.selection.Message;
import lombok.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SelectionWSController {

    @MessageMapping("/send/{id}")
    @SendTo("/topic/{id}/public")
    public Message sendMessage(@PathVariable String id, @Payload Message message){
        return message;
    }
}
