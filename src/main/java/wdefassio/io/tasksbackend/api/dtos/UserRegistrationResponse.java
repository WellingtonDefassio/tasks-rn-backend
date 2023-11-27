package wdefassio.io.tasksbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import wdefassio.io.tasksbackend.core.models.Users;

@Data
@AllArgsConstructor
public class UserRegistrationResponse {
    private Long id;
    private String name;
    private String email;

    public static UserRegistrationResponse fromModel(Users user) {
        return new UserRegistrationResponse(user.getId(), user.getName(), user.getEmail());
    }
}
