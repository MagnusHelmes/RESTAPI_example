package com.helmes.sectorchoice.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserSelectionDTO {

    @NotBlank(message = "Name is required")
    private String userName;

    @NotEmpty(message = "Please select at least one sector")
    private List<Long> sectorIds;

    @AssertTrue(message = "You must agree to the terms")
    private boolean agreeToTerms;

}