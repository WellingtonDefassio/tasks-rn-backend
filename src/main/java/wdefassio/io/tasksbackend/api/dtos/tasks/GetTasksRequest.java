package wdefassio.io.tasksbackend.api.dtos.tasks;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetTasksRequest {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate estimatedAt;

}
