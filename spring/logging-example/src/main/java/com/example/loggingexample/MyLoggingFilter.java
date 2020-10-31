package com.example.loggingexample;

import static net.logstash.logback.argument.StructuredArguments.v;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
        final FilterChain filterChain)
        throws ServletException, IOException {
        final SecurityContext sec = SecurityContextHolder.getContext();
        final String userName = Optional.ofNullable(sec.getAuthentication())
            .map(Authentication::getName).orElse("(null)");

        MDC.put("user", userName);
        MDC.put("requestId", UUID.randomUUID().toString());

        final String path = request.getPathInfo();
        log.info("Request", v("path", path));

        try {
            filterChain.doFilter(request, response);

            final int status = response.getStatus();
            log.info("Response", v("httpstatus", status));

        } finally {
            MDC.clear();
        }
    }
}
