package wdefassio.io.tasksbackend.api.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdefassio.io.tasksbackend.api.dtos.UserLoginRequest;
import wdefassio.io.tasksbackend.api.dtos.UserLoginResponse;
import wdefassio.io.tasksbackend.config.JwtUtil;
import wdefassio.io.tasksbackend.core.models.Users;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
        try {

            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            String email = authentication.getName();

            Users user = new Users(null, "test", email, null, null);
            String token = jwtUtil.createToken(user);
            UserLoginResponse userLoginResponse = new UserLoginResponse(email, token);
            return ResponseEntity.ok(userLoginResponse);

        }catch (BadCredentialsException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    }

}
