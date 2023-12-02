package wdefassio.io.tasksbackend.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdefassio.io.tasksbackend.config.JwtUtil;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class ValidateTokenController {

    private final JwtUtil jwtUtil;

    @GetMapping("/valid")
    public Boolean isValidToken(HttpServletRequest request) {
        return jwtUtil.validateToken(request);
    }

}
