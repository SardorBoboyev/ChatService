package uz.sb.chatservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.sb.chatservice.domain.entity.ChatEntity;
import uz.sb.chatservice.service.ChatService;
import uz.sb.domain.dto.request.ChatRequest;
import uz.sb.domain.dto.request.DeletedChatRequest;


import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/new-chat")
    public ChatEntity create(@RequestBody ChatRequest chatRequest) {
        return chatService.create(chatRequest);
    }

    @GetMapping("/find-by-user/{userId}")
    public List<ChatEntity> findAllByUser(@PathVariable long userId) {
        return chatService.findAllByUser(userId);
    }

    @GetMapping("/find-by-id/{id}")
    public ChatEntity findById(@PathVariable long id) {
        return chatService.findById(id);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody DeletedChatRequest deletedChatRequest) {
        chatService.delete(deletedChatRequest);
    }
}
