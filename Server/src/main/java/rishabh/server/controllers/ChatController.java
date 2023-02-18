package rishabh.server.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import rishabh.server.models.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {

    @MessageMapping("/message") // recieve from /chat-ws/message
    @SendTo("/topic/messages")
    public Message send(Message message) throws Exception {
        return message;
    }
}
