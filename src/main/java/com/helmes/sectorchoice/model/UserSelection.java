package com.helmes.sectorchoice.model;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
public class UserSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSelectionId;

    @Column(nullable = false)
    private String userName;

    @ManyToMany
    @JoinTable(
            name = "user_selection_sector",
            joinColumns = @JoinColumn(name = "user_selection_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id")
    )
    private Set<Sector> selectedSectors;

    @Column(nullable = false)
    private boolean agreeToTerms;

    public UserSelection() {}

}