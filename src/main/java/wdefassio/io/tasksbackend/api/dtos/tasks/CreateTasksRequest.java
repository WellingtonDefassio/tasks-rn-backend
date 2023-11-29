package wdefassio.io.tasksbackend.api.dtos.tasks;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.core.models.Users;
import wdefassio.io.tasksbackend.services.dto.TokenizedUser;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateTasksRequest {
    @NotBlank
    private String description;

    private LocalDate estimateAt;


    public Tasks toModel(String userId) {
        return new Tasks(null, description, estimateAt, null, new Users(UUID.fromString(userId)));
    }
}
