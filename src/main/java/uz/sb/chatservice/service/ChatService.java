package uz.sb.chatservice.service;

import uz.sb.chatservice.domain.entity.ChatEntity;
import uz.sb.chatservice.domain.entity.dto.request.ChatRequest;
import uz.sb.chatservice.domain.entity.dto.request.DeletedChatRequest;
import uz.sb.chatservice.domain.views.ChatInfoResponse;
import uz.sb.chatservice.domain.views.ChatInfoResponseImpl;
import uz.sb.domain.dto.request.ChatRequest;
import uz.sb.domain.dto.request.DeletedChatRequest;

import java.util.List;

public interface ChatService {

    ChatEntity create(ChatRequest chatRequest);

    ChatEntity findById(Long chatId);

    void delete(DeletedChatRequest deletedChatRequest);

    List<ChatInfoResponse> findAllChatInfoByUser(Long user);

}
