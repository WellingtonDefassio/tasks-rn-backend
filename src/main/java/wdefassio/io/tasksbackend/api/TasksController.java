package wdefassio.io.tasksbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdefassio.io.tasksbackend.api.dtos.tasks.DeleteTaskRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.CreateTasksRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.DoneTaskRequest;
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
        return taskService.getByDate(principal.getName(), getTasksRequest.getEstimatedAt());
    }

    @PostMapping("/create")
    public ResponseEntity create(Principal principal, @RequestBody CreateTasksRequest createTasksRequest) {
        return taskService.createTask(principal.getName(), createTasksRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(Principal principal, @RequestBody DeleteTaskRequest deleteTaskRequest) {
        return taskService.deleteTask(principal.getName(), deleteTaskRequest);
    }

    @PutMapping("/done")
    public ResponseEntity done(Principal principal, @RequestBody DoneTaskRequest doneTaskRequest) {
        return taskService.doneTask(principal.getName(), doneTaskRequest);
    }
}
