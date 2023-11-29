package wdefassio.io.tasksbackend.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wdefassio.io.tasksbackend.core.models.Users;
import wdefassio.io.tasksbackend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userByEmail = userRepository.findUsersByEmail(username).get();
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return User.builder()
                .username(userByEmail.getEmail())
                .password(userByEmail.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();

    }
}
