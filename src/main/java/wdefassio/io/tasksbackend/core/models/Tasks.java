package wdefassio.io.tasksbackend.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate estimatedAt;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate doneAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;


}
