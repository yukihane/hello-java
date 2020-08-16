package com.example.autotestingexample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @see https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications-testing-autoconfigured-mvc-tests
 */
@WebMvcTest(MyController.class)
class MyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MyService myService;

    @MockBean
    private MyMapper myMapper;

    @WithMockUser
    @Test
    void ログイン済み() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void 未ログイン() throws Exception {
        mvc.perform(get("/")).andExpect(status().isUnauthorized());
    }
}
