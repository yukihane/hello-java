package com.example.oauth2server.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/v1/authenticate")
public class FormLoginController {

    @GetMapping("/identity")
    public String identity(final HttpServletRequest request, final HttpServletResponse response, final Model model) {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/v1/authenticate/signin");
        // https://docs.spring.io/spring-security/site/docs/5.1.5.RELEASE/reference/html/overall-architecture.html#exceptiontranslationfilter
        // https://stackoverflow.com/a/44170176/4506703
        final SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        if (savedRequest != null) {
            final Map<String, String[]> params = savedRequest.getParameterMap();
            params.entrySet().stream().forEach(e -> builder.queryParam(e.getKey(), e.getValue()));
        }
        final String uri = builder.build().toUriString();

        model.addAttribute("link", uri);
        return "/v1/authenticate/login";
    }
}
