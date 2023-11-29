package wdefassio.io.tasksbackend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.repositories.TaskRepository;
import wdefassio.io.tasksbackend.services.dto.TokenizedUser;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public ResponseEntity<List<Tasks>> getByDate(TokenizedUser tokenizedUser, LocalDate estimateAt) {
        LocalDate findDate = estimateAt != null ? estimateAt : LocalDate.now();
        List<Tasks> tasksList = taskRepository.getAllByUsersAndEstimateAtIsLessThanEqual(tokenizedUser.getId(), findDate);
        return ResponseEntity.ok(tasksList);
    }
}
