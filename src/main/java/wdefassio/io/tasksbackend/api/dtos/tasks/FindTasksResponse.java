package wdefassio.io.tasksbackend.api.dtos.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import wdefassio.io.tasksbackend.core.models.Tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
public class FindTasksResponse {
    private UUID id;
    private String description;
    private LocalDate estimatedAt;
    private LocalDate doneAt;

    public static List<FindTasksResponse> fromModel(List<Tasks> tasks) {
       return tasks.stream().map(task -> new FindTasksResponse(task.getId(), task.getDescription(), task.getEstimatedAt(), task.getDoneAt())).toList();
    }
}
