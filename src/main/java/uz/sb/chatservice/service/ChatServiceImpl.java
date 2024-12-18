package uz.sb.chatservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sb.chatservice.clients.AuthServiceClient;
import uz.sb.chatservice.domain.dto.response.ChatServiceResponse;
import uz.sb.chatservice.domain.entity.ChatEntity;
import uz.sb.chatservice.domain.dto.request.ChatRequest;
import uz.sb.chatservice.domain.dto.request.DeletedChatRequest;
import uz.sb.chatservice.domain.dto.response.UserResponse;
import uz.sb.chatservice.exception.DataNotFoundException;
import uz.sb.chatservice.repository.ChatRepository;
import uz.sb.chatservice.domain.views.ChatInfoResponse;
import uz.sb.chatservice.domain.views.ChatInfoResponseImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final AuthServiceClient authServiceClient;

    @Override
    public ChatEntity create(ChatRequest chatRequest) {

        Long user1Id = chatRequest.getUser1Id();
        Long user2Id = chatRequest.getUser2Id();

        if (chatRepository.existsByUser1IdAndUser2Id(user1Id, user2Id)) {
            throw new DataNotFoundException("chat already exists");
        }

        if (Objects.isNull(authServiceClient.findById(user1Id)) || Objects.isNull(authServiceClient.findById(user2Id))) {
            throw new DataNotFoundException("users not found with this id " + user1Id + " and " + user2Id );
        }
        return chatRepository.save(ChatEntity.builder()
                .user1Id(user1Id)
                .user2Id(user2Id)
                .build());
    }


    @Override
    public ChatServiceResponse findById(Long chatId) {
        ChatEntity chat = chatRepository.findById(chatId).orElseThrow(() -> new DataNotFoundException("chat not found"));
        ChatServiceResponse chatServiceResponse = new ChatServiceResponse();
        chatServiceResponse.setUser1Id(chat.getUser1Id());
        chatServiceResponse.setUser2Id(chat.getUser2Id());
        chatServiceResponse.setId(chat.getId());
        chatServiceResponse.setCreatedAt(chat.getCreatedAt());
        chatServiceResponse.setUpdatedAt(chat.getUpdatedAt());
        chatServiceResponse.setDeletedByUser1(chat.isDeletedByUser1());
        chatServiceResponse.setDeletedByUser2(chat.isDeletedByUser2());
        return chatServiceResponse;
    }


    @Override
    public void delete(DeletedChatRequest deletedChatRequest) {
        ChatEntity chat = chatRepository.findById(deletedChatRequest.getChatId()).orElseThrow(() ->
                new DataNotFoundException("chat not found"));

        if (Objects.equals(chat.getUser1Id(), deletedChatRequest.getUserId())) {
            chat.setDeletedByUser1(true);
        } else if (Objects.equals(chat.getUser2Id(), deletedChatRequest.getUserId())) {
            chat.setDeletedByUser2(true);
        }

        if (chat.isDeletedByUser1() && chat.isDeletedByUser2()) {
            chatRepository.delete(chat);
        } else {
            chatRepository.save(chat);
        }
    }

    @Override
    public List<ChatInfoResponse> findAllChatInfoByUser(Long user) {
        List<ChatEntity> chats = chatRepository.findAllByUser(user);
        List<ChatInfoResponse> responses = new ArrayList<>();

        for (ChatEntity chat : chats) {
            Long secondUserId = chat.getUser1Id().equals(user) ? chat.getUser2Id() : chat.getUser1Id();
            UserResponse secondUser = authServiceClient.findById(secondUserId);
            responses.add(new ChatInfoResponseImpl(
                    chat.getId(),
                    secondUser.getUsername(),
                    secondUserId
            ));
        }
        return responses;
    }


}
