package wdefassio.io.tasksbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.api.dtos.UserLoginRequest;
import wdefassio.io.tasksbackend.api.dtos.UserLoginResponse;
import wdefassio.io.tasksbackend.api.dtos.UserRegistrationRequest;
import wdefassio.io.tasksbackend.api.dtos.UserRegistrationResponse;
import wdefassio.io.tasksbackend.config.JwtUtil;
import wdefassio.io.tasksbackend.core.models.Users;
import wdefassio.io.tasksbackend.repositories.UserRepository;

@Service
@RequiredArgsConstructor
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error to create a user");
        }
    }

    public ResponseEntity<?> login(UserLoginRequest loginRequest) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            String email = authentication.getName();

            Users user = new Users(null, "test", email, null, null);
            String token = jwtUtil.createToken(user);
            UserLoginResponse userLoginResponse = new UserLoginResponse(email, token);

            return ResponseEntity.ok(userLoginResponse);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
