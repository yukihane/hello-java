package com.example.oauth2server.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/authenticate")
public class FormLoginController {

    @Data
    @NoArgsConstructor
    public static class Client {
        private String client_id;
        private String redirect_uri;
        private String response_type;
        private String scope;
        private String state;
    }

    @RequestMapping("/identity")
    public String identity(final HttpServletRequest request, @ModelAttribute final Client client) {
        client.setClient_id(request.getParameter("client_id"));
        client.setRedirect_uri(request.getParameter("redirect_uri"));
        client.setResponse_type(request.getParameter("response_type"));
        client.setScope(request.getParameter("scope"));
        client.setState(request.getParameter("state"));

        return "/v1/authenticate/login";
    }
}
