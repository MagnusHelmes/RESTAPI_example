package com.helmes.sectorchoice.service;

import com.helmes.sectorchoice.model.Sector;
import com.helmes.sectorchoice.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SectorServiceTest {

    private SectorRepository sectorRepository;
    private SectorService sectorService;

    @BeforeEach
    void setUp() {
        sectorRepository = mock(SectorRepository.class);
        sectorService = new SectorService(sectorRepository);
    }

    @Test
    void testHierarchyBuildsCorrectly() {
        Sector parent = new Sector(1L, "Parent", null);
        Sector child = new Sector(2L, "Child", parent);
        when(sectorRepository.findAll()).thenReturn(Arrays.asList(parent, child));

        List<Sector> hierarchy = sectorService.getSectorHierarchy();

        assertEquals(2, hierarchy.size());
        assertEquals(0, hierarchy.get(0).getUiIndentLevel());
        assertEquals(1, hierarchy.get(1).getUiIndentLevel());
    }

    @Test
    void testEmptyHierarchy() {
        when(sectorRepository.findAll()).thenReturn(Collections.emptyList());

        List<Sector> hierarchy = sectorService.getSectorHierarchy();

        assertTrue(hierarchy.isEmpty());
    }
}
