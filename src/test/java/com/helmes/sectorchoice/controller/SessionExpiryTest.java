package com.helmes.sectorchoice.controller;

import com.helmes.sectorchoice.dto.UserSelectionDTO;
import com.helmes.sectorchoice.service.SectorService;
import com.helmes.sectorchoice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SessionExpiryTest {

    private UserFormController controller;
    private SectorService sectorService;
    private UserService userService;
    private Model model;

    @BeforeEach
    void setUp() {
        sectorService = mock(SectorService.class);
        userService = mock(UserService.class);
        model = mock(Model.class);

        when(sectorService.getSectorHierarchy()).thenReturn(Collections.emptyList());
        controller = new UserFormController(sectorService, userService);
    }

    @Test
    void testShowFormWithNoSessionUserId() {
        MockHttpSession session = new MockHttpSession();

        String view = controller.showForm(model, session);

        assertEquals("index", view);
        verify(model).addAttribute(eq("userForm"), any(UserSelectionDTO.class));
    }
}