package com.helmes.sectorchoice.service;

import com.helmes.sectorchoice.model.UserSelection;
import com.helmes.sectorchoice.repository.SectorRepository;
import com.helmes.sectorchoice.repository.UserSelectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private SectorRepository sectorRepository;
    private UserSelectionRepository userSelectionRepository;

    @BeforeEach
    void setUp() {
        sectorRepository = mock(SectorRepository.class);
        userSelectionRepository = mock(UserSelectionRepository.class);
        userService = new UserService(sectorRepository, userSelectionRepository);
    }

    @Test
    void saveSelection_shouldHandleException() {
        UserSelection selection = new UserSelection();
        doThrow(new RuntimeException("DB error")).when(userSelectionRepository).save(selection);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.saveSelection(selection);
        });

        assertEquals("DB error", thrown.getMessage());
    }
}