package uz.sb.chatservice.domain.entity.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatResponse extends BaseResponse {
    private Long user1Id;
    private Long user2Id;
}
