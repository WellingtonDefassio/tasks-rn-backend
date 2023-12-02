package wdefassio.io.tasksbackend.api.dtos.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import wdefassio.io.tasksbackend.core.models.Tasks;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Data
public class CreateTaskResponse {
    private UUID id;
    private String description;
    private LocalDate estimatedAt;
    private LocalDate doneAt;

    public static CreateTaskResponse fromModel(Tasks tasks) {
        return new CreateTaskResponse(tasks.getId(), tasks.getDescription(), tasks.getEstimatedAt(), tasks.getDoneAt());
    }
}
