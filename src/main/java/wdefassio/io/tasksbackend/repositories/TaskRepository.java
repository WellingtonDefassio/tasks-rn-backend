package wdefassio.io.tasksbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wdefassio.io.tasksbackend.core.models.Tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Tasks, UUID> {

    List<Tasks> getAllByUsers_IdAndEstimateAtIsLessThanEqual(UUID id, LocalDate date);
    List<Tasks> getAllByUsers_Id(UUID id);
    void deleteById(UUID id);

}
