package wdefassio.io.tasksbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wdefassio.io.tasksbackend.core.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
