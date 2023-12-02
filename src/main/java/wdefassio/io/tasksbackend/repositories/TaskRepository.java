package wdefassio.io.tasksbackend.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import wdefassio.io.tasksbackend.core.models.Tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Tasks, UUID> {

    List<Tasks> getAllByUsers_IdAndEstimatedAtIsLessThanEqual(UUID id, LocalDate date);
    @Modifying
    @Transactional
    @Query("delete from Tasks t where t.id = :id and t.users.id = :userId")
    void deleteTask(UUID id, UUID userId);

    @Query("select t from Tasks t where t.id = :id and t.users.id = :userId")
    Tasks getTask(UUID id, UUID userId);

}
