package wdefassio.io.tasksbackend.core.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    @NotBlank
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate estimateAt;
    private LocalDate doneAt;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;


}
