package wdefassio.io.tasksbackend.api.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {
    private String email;
    private String token;
}
