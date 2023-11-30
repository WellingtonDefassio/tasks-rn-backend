package wdefassio.io.tasksbackend.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class DeleteTaskRequest {
    private UUID id;
}
