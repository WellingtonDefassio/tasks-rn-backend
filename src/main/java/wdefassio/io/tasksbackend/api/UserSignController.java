package wdefassio.io.tasksbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdefassio.io.tasksbackend.api.dtos.users.UserLoginRequest;
import wdefassio.io.tasksbackend.api.dtos.users.UserRegistrationRequest;
import wdefassio.io.tasksbackend.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sign")
public class UserSignController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody @Validated UserRegistrationRequest user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
