package wdefassio.io.tasksbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdefassio.io.tasksbackend.api.dtos.DeleteTaskRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.CreateTasksRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.FindTasksResponse;
import wdefassio.io.tasksbackend.api.dtos.tasks.GetTasksRequest;
import wdefassio.io.tasksbackend.services.TaskService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;


    @PostMapping("/find")
    public ResponseEntity<List<FindTasksResponse>> find(Principal principal, @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) GetTasksRequest getTasksRequest) {
        return taskService.getByDate(principal.getName(), getTasksRequest.getEstimateAt());
    }

    @PostMapping("/create")
    public ResponseEntity create(Principal principal, @RequestBody CreateTasksRequest createTasksRequest) {
        return taskService.createTask(principal.getName(), createTasksRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(Principal principal, @RequestBody DeleteTaskRequest deleteTaskRequest) {
        return taskService.deleteTask(principal.getName(), deleteTaskRequest);
    }
}
