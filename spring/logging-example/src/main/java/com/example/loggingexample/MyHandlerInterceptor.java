package com.example.loggingexample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

// https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-handlermapping-interceptor
@RequiredArgsConstructor
@Slf4j
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
        throws Exception {

        log.info("Begin Controllor: {}", handler.toString());
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
        final Object handler, @Nullable final Exception ex) throws Exception {

        log.info("End Controllor: {}", handler.toString());
    }

}
