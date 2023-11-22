package pl.patrykjava.mychatroom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.patrykjava.mychatroom.model.ChatMessage;
import pl.patrykjava.mychatroom.utils.ApplicationUtils;

import java.util.Objects;

@Controller
public class ChatController {

    private final SimpMessagingTemplate template;

    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage (@Payload ChatMessage chatMessage) {
        chatMessage.setTime(ApplicationUtils.getTime());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser (@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in WebSocket session
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendAdhocMessage() {
        // Send the updated user count to the chat.
        template.convertAndSend("/topic/stats", ApplicationStats.getUserCount());
    }
}
