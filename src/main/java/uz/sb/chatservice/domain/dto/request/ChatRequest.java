package uz.sb.chatservice.domain.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatRequest {
    private Long user1Id;
    private Long user2Id;
}
