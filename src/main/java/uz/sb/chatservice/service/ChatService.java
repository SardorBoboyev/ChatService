package uz.sb.chatservice.service;

import uz.sb.chatservice.domain.entity.ChatEntity;
import uz.sb.chatservice.domain.entity.dto.request.ChatRequest;
import uz.sb.chatservice.domain.entity.dto.request.DeletedChatRequest;
import uz.sb.chatservice.domain.views.ChatInfoResponse;
import java.util.List;

public interface ChatService {

    ChatEntity create(ChatRequest chatRequest);

    ChatEntity findById(Long chatId);

    void delete(DeletedChatRequest deletedChatRequest);

    List<ChatInfoResponse> findAllChatInfoByUser(Long user);

}
