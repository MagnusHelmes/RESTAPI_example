package com.helmes.sectorchoice.service;

import com.helmes.sectorchoice.dto.UserSelectionDTO;
import com.helmes.sectorchoice.model.Sector;
import com.helmes.sectorchoice.model.UserSelection;
import com.helmes.sectorchoice.repository.SectorRepository;
import com.helmes.sectorchoice.repository.UserSelectionRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final SectorRepository sectorRepository;
    private final UserSelectionRepository userSelectionRepository;

    public UserService(SectorRepository sectorRepository, UserSelectionRepository userSelectionRepository) {
        this.sectorRepository = sectorRepository;
        this.userSelectionRepository = userSelectionRepository;
    }

    public Optional<UserSelectionDTO> loadUserForm(Long id) {
        return userSelectionRepository.findById(id).map(selection -> {
            UserSelectionDTO form = new UserSelectionDTO();
            form.setUserName(selection.getUserName());
            form.setAgreeToTerms(selection.isAgreeToTerms());
            form.setSectorIds(
                    selection.getSelectedSectors()
                            .stream()
                            .map(Sector::getSectorId)
                            .collect(Collectors.toList())
            );
            return form;
        });
    }

    public UserSelection saveUserSelection(UserSelectionDTO form, Long existingId) {
        Set<Sector> sectors = new HashSet<>(sectorRepository.findAllById(form.getSectorIds()));
        UserSelection selection;

        if (existingId != null) {
            selection = userSelectionRepository.findById(existingId).orElse(new UserSelection());
            selection.setUserSelectionId(existingId);
        } else {
            selection = new UserSelection();
        }

        selection.setUserName(form.getUserName());
        selection.setAgreeToTerms(form.isAgreeToTerms());
        selection.setSelectedSectors(sectors);

        return userSelectionRepository.save(selection);
    }

    public Set<Sector> resolveSelectedSectors(List<Long> ids) {
        return new HashSet<>(sectorRepository.findAllById(ids));
    }

    // for testing
    public void saveSelection(UserSelection selection) {
        userSelectionRepository.save(selection);
    }

    // for testing
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }
}
