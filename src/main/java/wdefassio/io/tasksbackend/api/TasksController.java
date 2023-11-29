package wdefassio.io.tasksbackend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdefassio.io.tasksbackend.api.dtos.tasks.CreateTasksRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.GetTasksRequest;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.services.TaskService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;


    @PostMapping("/find")
    public ResponseEntity<List<Tasks>> find(Principal principal, @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) GetTasksRequest getTasksRequest) {
        return taskService.getByDate(principal.getName(), getTasksRequest.getEstimateAt());
    }

    @PostMapping("/create")
    public ResponseEntity create(Principal principal, @RequestBody CreateTasksRequest createTasksRequest) {
        return taskService.createTask(principal.getName(), createTasksRequest);
    }
}
