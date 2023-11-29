package wdefassio.io.tasksbackend.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BcryptService {

    private final PasswordEncoder passwordEncoder;

    public String encodePassword(String cleanPassword) {
        return passwordEncoder.encode(cleanPassword);
    }

}

