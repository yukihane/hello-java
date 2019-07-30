package com.example.oauth2server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/authenticate")
@Slf4j
public class FormLoginController {

    @GetMapping("/identity")
    public String identity(final HttpServletRequest request, final HttpServletResponse response) {
        // https://docs.spring.io/spring-security/site/docs/5.1.5.RELEASE/reference/html/overall-architecture.html#exceptiontranslationfilter
        // https://stackoverflow.com/a/44170176/4506703
        final SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        log.info("parameters: " + savedRequest.getParameterMap());
        return "login.html";
    }
}
