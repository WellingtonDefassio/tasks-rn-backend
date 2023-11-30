package wdefassio.io.tasksbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.api.dtos.DeleteTaskRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.CreateTaskResponse;
import wdefassio.io.tasksbackend.api.dtos.tasks.CreateTasksRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.FindTasksResponse;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public ResponseEntity<List<FindTasksResponse>> getByDate(String tokenizedUser, LocalDate estimateAt) {
        LocalDate findDate = estimateAt != null ? estimateAt : LocalDate.now();
        List<Tasks> tasksList = taskRepository.getAllByUsers_IdAndEstimateAtIsLessThanEqual(UUID.fromString(tokenizedUser), findDate);
        List<FindTasksResponse> findTasksResponses = FindTasksResponse.fromModel(tasksList);
        return ResponseEntity.ok(findTasksResponses);
    }

    public ResponseEntity<CreateTaskResponse> createTask(String userId, CreateTasksRequest createTasksRequest) {
        Tasks tasks = taskRepository.save(createTasksRequest.toModel(userId));
        CreateTaskResponse createTaskResponse = CreateTaskResponse.fromModel(tasks);
        return new ResponseEntity<>(createTaskResponse, HttpStatus.CREATED);
    }

    public ResponseEntity deleteTask(String name, DeleteTaskRequest deleteTaskRequest) {
        try {
            List<Tasks> allByUsersId = taskRepository.getAllByUsers_Id(UUID.fromString(name));
            boolean isValidTask = allByUsersId.stream().anyMatch(tasks -> tasks.getId().equals(deleteTaskRequest.getId()));
            if (isValidTask) {
                taskRepository.deleteById(deleteTaskRequest.getId());
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
