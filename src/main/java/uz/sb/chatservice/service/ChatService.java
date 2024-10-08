package uz.sb.chatservice.service;

import uz.sb.chatservice.domain.entity.ChatEntity;
import uz.sb.domain.dto.request.ChatRequest;
import uz.sb.domain.dto.request.DeletedChatRequest;

import java.util.List;

public interface ChatService {

    ChatEntity create(ChatRequest chatRequest);

    ChatEntity findById(Long chatId);

    List<ChatEntity> findAllByUser(Long user);

    void delete(DeletedChatRequest deletedChatRequest);

}
