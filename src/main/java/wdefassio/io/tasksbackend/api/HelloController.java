package wdefassio.io.tasksbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class HelloController {
    @GetMapping("/hello")
    public String helloController(Principal principal) {
        return "Hello " + principal.getName();
    }

}
