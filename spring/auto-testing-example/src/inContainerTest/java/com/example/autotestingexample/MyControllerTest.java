package com.example.autotestingexample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
        when(myService.getNow()).thenReturn("2020-01-01T10:10:10");
        final MyTable obj = new MyTable();
        obj.setId(10L);
        obj.setMessage("2020-02-02T02:02:02");
        when(myMapper.select()).thenReturn(obj);

        final MvcResult result = mvc.perform(get("/"))
            .andExpect(status().isOk()).andReturn();
        final String content = result.getResponse().getContentAsString();

        assertThat(content).contains("MyTable(id=10, message=2020-02-02T02:02:02)");
        assertThat(content).contains("2020-01-01T10:10:10");
    }

    @Test
    void 未ログイン() throws Exception {
        mvc.perform(get("/")).andExpect(status().isUnauthorized());
    }
}
