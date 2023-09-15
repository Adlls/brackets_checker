package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.adls.beauty_brackets.controller.CheckerController;
import com.adls.beauty_brackets.model.BracketsCheckerRq;
import com.adls.beauty_brackets.model.BracketsCheckerRs;
import com.adls.beauty_brackets.service.BracketsTextCheckerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.SneakyThrows;

@WebMvcTest
@WebAppConfiguration
@ContextConfiguration(classes = { CheckerController.class })
@MockBean(BracketsTextCheckerService.class)
public class BracketsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BracketsTextCheckerService bracketsCheckerService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void setup() {
        Mockito.when(bracketsCheckerService.check(Mockito.anyString()))
                .thenReturn(BracketsCheckerRs.builder().isCorrect(true).build());

    }

    @Test
    @SneakyThrows
    void chekcBrackets_happyPath_returnOkStatus() {
        final BracketsCheckerRq bracketsCheckerRq = BracketsCheckerRq.builder().text("someText").build();

        mockMvc.perform(post("/v1/api/checker/text/bracket")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(bracketsCheckerRq)))
                .andExpect(MockMvcResultMatchers.jsonPath("isCorrect", Matchers.is(true)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void chekcBrackets_textIsBlank_returnBadRequest() {
        final BracketsCheckerRq bracketsCheckerRq = BracketsCheckerRq.builder().text(null).build();

        mockMvc.perform(post("/v1/api/checker/text/bracket")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(bracketsCheckerRq)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void chekcBrackets_textSizeIsTooBig_returnBadRequest() {
        final BracketsCheckerRq bracketsCheckerRq = BracketsCheckerRq.builder().text("text".repeat(255)).build();

        mockMvc.perform(post("/v1/api/checker/text/bracket")
                .contentType("application/json")
                .content(MAPPER.writeValueAsString(bracketsCheckerRq)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}
