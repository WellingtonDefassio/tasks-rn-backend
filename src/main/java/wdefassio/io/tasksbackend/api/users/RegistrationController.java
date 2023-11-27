package wdefassio.io.tasksbackend.api.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdefassio.io.tasksbackend.api.dtos.UserRegistrationRequest;
import wdefassio.io.tasksbackend.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> registration(@RequestBody @Validated UserRegistrationRequest user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }


}
