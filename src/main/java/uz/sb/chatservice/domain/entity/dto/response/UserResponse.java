package uz.sb.chatservice.domain.entity.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse extends BaseResponse {
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
}
