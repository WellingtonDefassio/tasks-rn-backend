package wdefassio.io.tasksbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class HelloController {
    @GetMapping("/hello")
    public String helloController() {
        return "Hello";
    }

}
