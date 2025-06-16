package com.helmes.sectorchoice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "sector")
public class Sector {

    @Id
    @ToString.Include
    private Long sectorId;

    @Column(nullable = false)
    @ToString.Include
    private String sectorName;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Sector parentSector;

    @OneToMany(mappedBy = "parentSector")
    private Set<Sector> childSectors;

    private Integer hierarchyLevel;

    @Transient
    private int uiIndentLevel;

    public Sector() {
    }

    public void setLevel(int level) {
        this.uiIndentLevel = level;
    }

    public Sector(Long sectorId, String sectorName, Sector parentSector) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.parentSector = parentSector;
    }

    public String getIndentedName() {
        return "&nbsp;&nbsp;&nbsp;&nbsp;".repeat(uiIndentLevel) + this.sectorName;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "sectorId=" + sectorId +
                ", sectorName='" + sectorName + '\'' +
                ", parentSector=" + (parentSector != null ? parentSector.getSectorId() : "null") +
                '}';
    }
}