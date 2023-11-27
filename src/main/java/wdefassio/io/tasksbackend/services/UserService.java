package wdefassio.io.tasksbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.api.dtos.UserRegistrationDTO;
import wdefassio.io.tasksbackend.core.models.Users;
import wdefassio.io.tasksbackend.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BcryptService bcryptService;

    public Users registerUser(UserRegistrationDTO userDTO) {
        String encodePassword = bcryptService.encodePassword(userDTO.getPassword());
        return new Users(null, userDTO.getName(), userDTO.getEmail(), encodePassword, null);
    }

}
