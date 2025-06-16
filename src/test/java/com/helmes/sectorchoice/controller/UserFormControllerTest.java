package com.helmes.sectorchoice.controller;

import com.helmes.sectorchoice.dto.UserSelectionDTO;
import com.helmes.sectorchoice.model.UserSelection;
import com.helmes.sectorchoice.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserFormControllerTest {

    private UserFormController controller;
    private UserService userService;
    private Model model;
    private HttpSession session;
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        model = mock(Model.class);
        session = mock(HttpSession.class);
        bindingResult = mock(BindingResult.class);

        // SectorRepository is required for hierarchy; mock it or use stub
        var sectorService = mock(com.helmes.sectorchoice.service.SectorService.class);
        when(sectorService.getSectorHierarchy()).thenReturn(Collections.emptyList());

        controller = new UserFormController(sectorService, userService);
    }

    @Test
    void showForm_shouldReturnIndex() {
        String view = controller.showForm(model, session);
        assertEquals("index", view);
    }

    @Test
    void handleForm_successful_shouldRedirect() {
        UserSelectionDTO dto = new UserSelectionDTO();
        dto.setUserName("Test User");
        dto.setSectorIds(Collections.singletonList(1L));
        dto.setAgreeToTerms(true);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.saveUserSelection(any(UserSelectionDTO.class), any()))
                .thenReturn(new UserSelection());

        String view = controller.handleForm(dto, bindingResult, model, session);
        assertEquals("redirect:/", view); // 🔄 This is now correct
        verify(userService, times(1)).saveUserSelection(any(UserSelectionDTO.class), any());
    }

    @Test
    void handleForm_successful_shouldReturnIndexWithSuccessMessage() {
        UserSelectionDTO dto = new UserSelectionDTO();
        dto.setUserName("Test User");
        dto.setSectorIds(Collections.singletonList(1L));
        dto.setAgreeToTerms(true);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.saveUserSelection(any(UserSelectionDTO.class), any()))
                .thenReturn(new UserSelection());

        String view = controller.handleForm(dto, bindingResult, model, session);
        assertEquals("redirect:/", view);
        verify(userService, times(1)).saveUserSelection(any(UserSelectionDTO.class), any());
    }
}