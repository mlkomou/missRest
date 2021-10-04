package com.miss.api;

import com.miss.api.auth.entity.User;
import com.miss.api.auth.repository.UserRepository;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Main implements CommandLineRunner {

    private final ConfigurableListableBeanFactory configurableListableBeanFactory;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public Main(ConfigurableListableBeanFactory configurableListableBeanFactory,
                PasswordEncoder passwordEncoder,
                UserRepository userRepository) {
        this.configurableListableBeanFactory = configurableListableBeanFactory;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsername("komou35@gmail.com") != null) return;
        User user = User
                .builder()
                .username("komou35@gmail.com")
                .password(passwordEncoder.encode("77114120"))
                .dateCreation(new Date())
                .dateModification(new Date())
                .active(true)
                .admin(true)
                .build();
        userRepository.save(user);
    }
}
