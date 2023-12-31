package wdefassio.io.tasksbackend.api.dtos.tasks;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.core.models.Users;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateTasksRequest {
    @NotBlank
    private String description;

    @NotNull
    private LocalDate estimatedAt;


    public Tasks toModel(String userId) {
        return new Tasks(null, description, estimatedAt, null, new Users(UUID.fromString(userId)));
    }
}
