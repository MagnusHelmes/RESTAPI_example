package com.helmes.sectorchoice;

import com.helmes.sectorchoice.dto.UserSelectionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class SectorChoiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getIndex_shouldReturn200() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void postForm_shouldRedirect() throws Exception {
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Test User")
                        .param("sectorIds", "1")
                        .param("agreeToTerms", "true"))
                .andExpect(status().is3xxRedirection());  // Expect redirect if validation succeeds
    }
}