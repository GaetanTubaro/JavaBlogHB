package com.hb.blog;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;


@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetPosts() throws Exception {
        mockMvc.perform(get("/private/post")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testAddPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/private/post/new")
                            .param("title", "tester")
                            .param("content", "tester")
                            .with(csrf())
                        )
                .andExpect(MockMvcResultMatchers.redirectedUrl("/private/post"))
                .andExpect(status().isOk());
    }
}
