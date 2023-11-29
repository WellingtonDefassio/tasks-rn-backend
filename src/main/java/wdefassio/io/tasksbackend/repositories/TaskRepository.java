package wdefassio.io.tasksbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wdefassio.io.tasksbackend.core.models.Tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> getAllByUsersAndEstimateAtIsLessThanEqual(Long id, LocalDate date);

}
