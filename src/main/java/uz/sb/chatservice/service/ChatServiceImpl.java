package uz.sb.chatservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sb.chatservice.clients.AuthServiceClient;
import uz.sb.chatservice.domain.dto.request.ChatRequest;
import uz.sb.chatservice.domain.dto.request.DeletedChatRequest;
import uz.sb.chatservice.domain.entity.ChatEntity;
import uz.sb.chatservice.repository.ChatRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            throw new RuntimeException("chat already exists");
        }

        if (Objects.isNull(authServiceClient.findById(user1Id)) || Objects.isNull(authServiceClient.findById(user2Id))) {
            throw new RuntimeException("users not found");
        }
        return chatRepository.save(ChatEntity.builder()
                .user1Id(user1Id)
                .user2Id(user2Id)
                .build());
    }


    @Override
    public ChatEntity findById(Long chatId) {
        return chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("chat not found"));
    }


    @Override
    public List<ChatEntity> findAllByUser(Long userId) {
        return chatRepository.findAllByUser(userId);
    }


    @Override
    public void delete(DeletedChatRequest deletedChatRequest) {
        ChatEntity chat = findById(deletedChatRequest.getChatId());

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




}
