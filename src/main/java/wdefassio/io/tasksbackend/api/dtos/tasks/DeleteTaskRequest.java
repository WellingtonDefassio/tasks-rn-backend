package wdefassio.io.tasksbackend.api.dtos.tasks;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteTaskRequest {
    private UUID id;
}
