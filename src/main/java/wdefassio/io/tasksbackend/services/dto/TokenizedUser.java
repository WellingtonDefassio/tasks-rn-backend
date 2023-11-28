package wdefassio.io.tasksbackend.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import wdefassio.io.tasksbackend.core.models.Tasks;
import wdefassio.io.tasksbackend.core.models.Users;

import java.util.List;

@Data
@AllArgsConstructor
public class TokenizedUser {
    private Long id;
    private String name;
    private String email;
    private List<Tasks> tasks;


    public static TokenizedUser tokenize(Users user) {
        return new TokenizedUser(user.getId(), user.getName(), user.getEmail(), user.getTasks());
    }

}
