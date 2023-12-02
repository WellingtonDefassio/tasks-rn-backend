package wdefassio.io.tasksbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.api.dtos.tasks.*;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;


    public ResponseEntity<List<FindTasksResponse>> getByDate(String tokenizedUser, LocalDate estimateAt) {
        LocalDate findDate = estimateAt != null ? estimateAt : LocalDate.now();
        List<Tasks> tasksList = taskRepository.getAllByUsers_IdAndEstimatedAtIsLessThanEqual(UUID.fromString(tokenizedUser), findDate);
        List<FindTasksResponse> findTasksResponses = FindTasksResponse.fromModel(tasksList);
        log.info("{}", findTasksResponses);
        return ResponseEntity.ok(findTasksResponses);
    }

    public ResponseEntity<CreateTaskResponse> createTask(String userId, CreateTasksRequest createTasksRequest) {
        Tasks tasks = taskRepository.save(createTasksRequest.toModel(userId));
        CreateTaskResponse createTaskResponse = CreateTaskResponse.fromModel(tasks);
        return new ResponseEntity<>(createTaskResponse, HttpStatus.CREATED);
    }


    public ResponseEntity deleteTask(String name, UUID id) {
        try {
            taskRepository.deleteTask(id, UUID.fromString(name));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity doneTask(String name, DoneTaskRequest doneTaskRequest) {
        try {
            Tasks task = taskRepository.getTask(doneTaskRequest.getId(), UUID.fromString(name));
            Optional<Tasks> opTask = Optional.of(task);
            Tasks tasks = opTask.orElseThrow(ChangeSetPersister.NotFoundException::new);
            if (Objects.isNull(tasks.getDoneAt())) {
                tasks.setDoneAt(LocalDate.now());
                taskRepository.save(tasks);
            } else {
                tasks.setDoneAt(null);
                taskRepository.save(tasks);
            }
            return ResponseEntity.ok(DoneTaskResponse.fromModel(tasks));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

}
