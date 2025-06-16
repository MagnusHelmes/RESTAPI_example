package com.helmes.sectorchoice.dto;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserSelectionDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDTO() {
        UserSelectionDTO dto = new UserSelectionDTO();
        dto.setUserName("Alice");
        dto.setSectorIds(Collections.singletonList(1L));
        dto.setAgreeToTerms(true);

        Set<ConstraintViolation<UserSelectionDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidDTO() {
        UserSelectionDTO dto = new UserSelectionDTO(); // all fields missing

        Set<ConstraintViolation<UserSelectionDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size());
    }
}