package wdefassio.io.tasksbackend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdefassio.io.tasksbackend.api.dtos.tasks.CreateTasksRequest;
import wdefassio.io.tasksbackend.api.dtos.users.GetTasksRequest;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.services.TaskService;
import wdefassio.io.tasksbackend.services.dto.TokenizedUser;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;
    private final ObjectMapper mapper;


    @PostMapping("/get")
    public ResponseEntity<List<Tasks>> get(Principal principal, @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) GetTasksRequest getTasksRequest) throws JsonProcessingException {
        return taskService.getByDate(principal.getName(), getTasksRequest.getEstimateAt());
    }

    @PostMapping("/create")
    public ResponseEntity create(Principal principal, @RequestBody CreateTasksRequest createTasksRequest) throws JsonProcessingException {
        return taskService.createTask(principal.getName(), createTasksRequest);
    }
}
