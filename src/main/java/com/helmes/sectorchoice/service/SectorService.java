package com.helmes.sectorchoice.service;

import com.helmes.sectorchoice.model.Sector;
import com.helmes.sectorchoice.repository.SectorRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public List<Sector> getSectorHierarchy() {
        List<Sector> allSectors = sectorRepository.findAll();
        return buildSectorHierarchy(allSectors);
    }

    private List<Sector> buildSectorHierarchy(List<Sector> allSectors) {
        Map<Long, List<Sector>> childrenMap = new HashMap<>();
        List<Sector> roots = new ArrayList<>();

        for (Sector sector : allSectors) {
            Long parentId = sector.getParentSector() != null
                    ? sector.getParentSector().getSectorId()
                    : null;
            childrenMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(sector);
            if (parentId == null) {
                roots.add(sector);
            }
        }

        List<Sector> result = new ArrayList<>();
        for (Sector root : roots) {
            walkHierarchy(root, childrenMap, result, 0);
        }

        return result;
    }

    private void walkHierarchy(Sector current, Map<Long, List<Sector>> childrenMap,
                               List<Sector> result, int level) {
        current.setLevel(level);
        result.add(current);

        List<Sector> children = childrenMap.get(current.getSectorId());
        if (children != null) {
            children.sort(Comparator.comparing(Sector::getSectorName));
            for (Sector child : children) {
                walkHierarchy(child, childrenMap, result, level + 1);
            }
        }
    }
}
