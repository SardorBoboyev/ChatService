package uz.sb.chatservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.sb.chatservice.domain.entity.ChatEntity;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    boolean existsByUser1IdAndUser2Id(Long user1Id, Long user2Id);

    @Query("SELECT c FROM chats c WHERE c.user1Id = :userId OR c.user2Id = :userId")
    List<ChatEntity> findAllChatsByUserId(@Param("userId") Long userId);

}
