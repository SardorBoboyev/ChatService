package uz.sb.chatservice.service;

import uz.sb.chatservice.domain.dto.request.ChatRequest;
import uz.sb.chatservice.domain.dto.request.DeletedChatRequest;
import uz.sb.chatservice.domain.entity.ChatEntity;

import java.util.List;

public interface ChatService {

    ChatEntity create(ChatRequest chatRequest);

    ChatEntity findById(Long chatId);

    List<ChatEntity> findAllByUser(Long user);

    void delete(DeletedChatRequest deletedChatRequest);

}
