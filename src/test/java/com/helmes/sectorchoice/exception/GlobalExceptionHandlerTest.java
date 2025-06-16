package com.helmes.sectorchoice.exception;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleGeneralException_shouldReturnErrorView() {
        Exception ex = new RuntimeException("Something went wrong");
        Model model = new ConcurrentModel();
        String result = handler.handleGeneralException(ex, model);
        assertEquals("error", result);
        assertEquals("An unexpected error occurred.", model.getAttribute("errorMessage"));
    }

    @Test
    void handleNotFound_shouldReturnErrorView() {
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/notfound", null);
        Model model = new ConcurrentModel();
        String result = handler.handleNotFound(ex, model);
        assertEquals("error", result);
        assertEquals("Page not found.", model.getAttribute("errorMessage"));
    }
}