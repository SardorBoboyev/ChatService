package uz.sb.chatservice.domain.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatServiceResponse extends BaseResponse {
    private Long user1Id;
    private Long user2Id;
    private boolean isDeletedByUser1;
    private boolean isDeletedByUser2;
}
