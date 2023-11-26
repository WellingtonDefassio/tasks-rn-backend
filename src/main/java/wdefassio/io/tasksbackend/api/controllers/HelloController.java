package wdefassio.io.tasksbackend.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HelloController {

    @GetMapping
    public String helloController() {
        return "Hello Controller";
    }

    @GetMapping("/Hello")
    public String helloController2() {
        return "Hello Controller";
    }

}
