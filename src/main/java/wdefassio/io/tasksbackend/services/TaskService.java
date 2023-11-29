package wdefassio.io.tasksbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.api.dtos.tasks.CreateTasksRequest;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public ResponseEntity<List<Tasks>> getByDate(String tokenizedUser, LocalDate estimateAt) {
        LocalDate findDate = estimateAt != null ? estimateAt : LocalDate.now();
        List<Tasks> tasksList = taskRepository.getAllByUsers_IdAndEstimateAtIsLessThanEqual(UUID.fromString(tokenizedUser), findDate);
        return ResponseEntity.ok(tasksList);
    }

    public ResponseEntity<Tasks> createTask(String userId, CreateTasksRequest createTasksRequest) {
        Tasks tasks = taskRepository.save(createTasksRequest.toModel(userId));
        return new ResponseEntity<>(tasks, HttpStatus.CREATED);
    }
}
