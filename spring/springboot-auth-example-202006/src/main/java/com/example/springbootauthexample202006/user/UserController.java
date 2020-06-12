package com.example.springbootauthexample202006.user;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Data
    public static class UserForm {
        private String username;
        private String password;
    }

    private final ApplicationUserRepository applicationUserRepository;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody final UserForm form) {

        final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        final ApplicationUser user = new ApplicationUser(
            form.getUsername(),
            passwordEncoder.encode(form.getPassword()));

        final ApplicationUser saved = applicationUserRepository.save(user);

        log.info("User sign-upped: {}", saved);
    }

    @GetMapping("")
    public List<ApplicationUser> users() {
        return applicationUserRepository.findAll();
    }
}
