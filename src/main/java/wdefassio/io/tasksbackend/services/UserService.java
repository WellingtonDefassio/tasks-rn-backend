package wdefassio.io.tasksbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.api.dtos.UserRegistrationRequest;
import wdefassio.io.tasksbackend.api.dtos.UserRegistrationResponse;
import wdefassio.io.tasksbackend.core.models.Users;
import wdefassio.io.tasksbackend.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BcryptService bcryptService;

    public UserRegistrationResponse registerUser(UserRegistrationRequest userDTO) {
        String encodePassword = bcryptService.encodePassword(userDTO.getPassword());
        Users save = userRepository.save(new Users(null, userDTO.getName(), userDTO.getEmail(), encodePassword, null));
        return UserRegistrationResponse.fromModel(save);
    }

}
