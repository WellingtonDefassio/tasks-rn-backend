package wdefassio.io.tasksbackend.core.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "users")
    private List<Tasks> tasks;


    public Users(UUID id) {
        this.id = id;
    }
}
