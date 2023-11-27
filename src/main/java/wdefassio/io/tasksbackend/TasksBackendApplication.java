package wdefassio.io.tasksbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wdefassio.io.tasksbackend.services.BcryptService;

@SpringBootApplication
public class TasksBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasksBackendApplication.class, args);
    }

}
