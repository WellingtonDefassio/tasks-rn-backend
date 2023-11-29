package wdefassio.io.tasksbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wdefassio.io.tasksbackend.core.models.Users;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findUsersByEmail(String email);
}
