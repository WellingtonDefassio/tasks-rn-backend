package wdefassio.io.tasksbackend.core.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Tasks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String description;
    @NotBlank
    @Column(nullable = false)
    private LocalDate estimateAt;
    private LocalDate doneAt;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;


}
