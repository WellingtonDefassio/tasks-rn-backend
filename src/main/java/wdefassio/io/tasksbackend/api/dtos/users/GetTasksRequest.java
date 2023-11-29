package wdefassio.io.tasksbackend.api.dtos.users;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetTasksRequest {

    private LocalDate estimateAt;

}
