package com.helmes.sectorchoice.repository;

import com.helmes.sectorchoice.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    List<Sector> findByParentSectorIsNull();
    List<Sector> findByParentSectorSectorId(Long parentId);

}
