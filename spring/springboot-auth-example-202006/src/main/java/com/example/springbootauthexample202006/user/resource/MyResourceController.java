package com.example.springbootauthexample202006.user.resource;

import com.example.springbootauthexample202006.security.ApplicationUserAuthentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class MyResourceController {

    @GetMapping("")
    public MyResource resources(@AuthenticationPrincipal final ApplicationUserAuthentication.Principal principal) {
        final String username = principal.getUsername();
        return new MyResource("Hello, " + username + "!");
    }
}
