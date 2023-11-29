package wdefassio.io.tasksbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.api.dtos.tasks.UserLoginRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.UserLoginResponse;
import wdefassio.io.tasksbackend.api.dtos.tasks.UserRegistrationRequest;
import wdefassio.io.tasksbackend.api.dtos.tasks.UserRegistrationResponse;
import wdefassio.io.tasksbackend.config.JwtUtil;
import wdefassio.io.tasksbackend.core.models.Users;
import wdefassio.io.tasksbackend.repositories.UserRepository;
import wdefassio.io.tasksbackend.services.dto.TokenizedUser;
import wdefassio.io.tasksbackend.services.security.BcryptService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BcryptService bcryptService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> registerUser(UserRegistrationRequest userDTO) {
        try {
            String encodePassword = bcryptService.encodePassword(userDTO.getPassword());
            Users save = userRepository.save(new Users(null, userDTO.getName(), userDTO.getEmail(), encodePassword, null));
            return ResponseEntity.ok(UserRegistrationResponse.fromModel(save));
        } catch (Exception e) {
            log.error("Error to create a user with email {} with error {}", userDTO.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error to create a user");
        }
    }

    public ResponseEntity<?> login(UserLoginRequest loginRequest) {
        try {

            //Atenticação é realizada aqui!
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            String email = authentication.getName();
            Optional<Users> usersByEmail = userRepository.findUsersByEmail(email);
            Users user = usersByEmail.orElseThrow(() -> new BadCredentialsException("user not found"));
            TokenizedUser tokenize = TokenizedUser.tokenize(user);
            String token = jwtUtil.createToken(tokenize);
            UserLoginResponse userLoginResponse = new UserLoginResponse(email, token);

            return ResponseEntity.ok(userLoginResponse);

        } catch (BadCredentialsException e) {
            log.error("Error BadCredential with {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        } catch (Exception e) {
            log.error("Error to login a user with email {} with error {}", loginRequest.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
