package wdefassio.io.tasksbackend.api.dtos.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import wdefassio.io.tasksbackend.core.models.Tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class FindTasksResponse {
    private UUID id;
    private String description;
    private LocalDate estimateAt;
    private LocalDate doneAt;

    public static List<FindTasksResponse> fromModel(List<Tasks> tasks) {
       return tasks.stream().map(task -> new FindTasksResponse(task.getId(), task.getDescription(), task.getEstimateAt(), task.getDoneAt())).toList();
    }
}
