package uz.sb.chatservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity(name = "chats")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Long user1Id;

    private Long user2Id;

    private boolean isDeletedByUser1 = false;

    private boolean isDeletedByUser2 = false;
}
