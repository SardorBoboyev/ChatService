package uz.sb.chatservice.domain.entity.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseResponse {
    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}