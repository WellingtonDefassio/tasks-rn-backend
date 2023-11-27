package wdefassio.io.tasksbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wdefassio.io.tasksbackend.core.models.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByEmail(String email);
}
