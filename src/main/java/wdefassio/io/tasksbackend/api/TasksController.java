package wdefassio.io.tasksbackend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wdefassio.io.tasksbackend.api.dtos.users.GetTasksRequest;
import wdefassio.io.tasksbackend.services.TaskService;
import wdefassio.io.tasksbackend.services.dto.TokenizedUser;

import java.security.Principal;
import java.time.LocalDate;

@RestController("/api/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;
    private final ObjectMapper mapper;


    @GetMapping()
    public ResponseEntity getTask(Principal principal, @RequestBody GetTasksRequest getTasksRequest) throws JsonProcessingException {
        TokenizedUser tokenizedUser = mapper.readValue(principal.getName(), TokenizedUser.class);
        taskService.getByDate(tokenizedUser, getTasksRequest.getEstimateAt());

        return ResponseEntity.ok().build();
    }
}
