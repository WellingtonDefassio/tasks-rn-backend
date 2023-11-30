package wdefassio.io.tasksbackend.api.dtos.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wdefassio.io.tasksbackend.core.models.Tasks;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoneTaskResponse {


    private UUID id;
    private String description;
    private LocalDate estimateAt;
    private LocalDate doneAt;

    public static DoneTaskResponse fromModel(Tasks tasks) {
        return new DoneTaskResponse(tasks.getId(), tasks.getDescription(), tasks.getEstimateAt(), tasks.getDoneAt());
    }
}
