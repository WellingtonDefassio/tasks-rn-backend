package wdefassio.io.tasksbackend.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.core.models.Users;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenizedUser {
    private UUID id;
    private String name;
    private String email;
    private List<Tasks> tasks;


    public static TokenizedUser tokenize(Users user) {
        return new TokenizedUser(user.getId(), user.getName(), user.getEmail(), user.getTasks());
    }

}
