package uz.sb.chatservice.domain.views;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChatInfoResponseImpl implements ChatInfoResponse {
    private Long id;
    private String chatName;
    private Long secondUserId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getChatName() {
        return chatName;
    }

    @Override
    public Long getSecondUserId() {
        return secondUserId;
    }
}
