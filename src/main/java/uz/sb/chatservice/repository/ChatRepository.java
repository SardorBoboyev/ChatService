package uz.sb.chatservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.sb.chatservice.domain.entity.ChatEntity;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    boolean existsByUser1IdAndUser2Id(Long user1Id, Long user2Id);

    @Query("SELECT c FROM chats c WHERE " +
            "((c.user1Id = :userId AND c.isDeletedByUser1 = false) OR " +
            "(c.user2Id = :userId AND c.isDeletedByUser2 = false))")
    List<ChatEntity> findAllByUser(@Param("userId") Long userId);

}
