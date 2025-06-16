package com.helmes.sectorchoice.controller;

import com.helmes.sectorchoice.dto.UserSelectionDTO;
import com.helmes.sectorchoice.model.Sector;
import com.helmes.sectorchoice.model.UserSelection;
import com.helmes.sectorchoice.service.SectorService;
import com.helmes.sectorchoice.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import java.util.*;

@Controller
public class UserFormController {

    @Value("${app.logging.enabled:true}")
    private boolean loggingEnabled;

    private static final Logger logger = LoggerFactory.getLogger(UserFormController.class);

    private final SectorService sectorService;
    private final UserService userService;

    public UserFormController(SectorService sectorService, UserService userService) {
        this.sectorService = sectorService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showForm(Model model, HttpSession session) {
        if (loggingEnabled) {
            logger.info("GET / - Showing form");
        }

        List<Sector> sortedSectors = sectorService.getSectorHierarchy();
        model.addAttribute("sectors", sortedSectors);

        Object savedUserId = session.getAttribute("savedUserId");
        if (savedUserId instanceof Long id) {
            Optional<UserSelectionDTO> form = userService.loadUserForm(id);
            if (form.isPresent()) {
                model.addAttribute("userForm", form.get());
                logger.debug("Loaded user form for ID {}", id);
                return "index";
            }
        }

        model.addAttribute("userForm", new UserSelectionDTO());
        return "index";
    }

    @PostMapping("/")
    public String handleForm(
            @Valid @ModelAttribute("userForm") UserSelectionDTO userSelectionDTO,
            BindingResult result,
            Model model,
            HttpSession session
    ) {
        if (loggingEnabled) {
            logger.info("POST / - Processing form for {}", userSelectionDTO.getUserName());
        }

        List<Sector> sortedSectors = sectorService.getSectorHierarchy();
        model.addAttribute("sectors", sortedSectors);

        if (result.hasErrors()) {
            logger.warn("Validation errors: {}", result.getAllErrors());
            return "index";
        }

        Long savedUserId = (session.getAttribute("savedUserId") instanceof Long id) ? id : null;
        UserSelection saved = userService.saveUserSelection(userSelectionDTO, savedUserId);

        session.setAttribute("savedUserId", saved.getUserSelectionId());

        logger.info("Saved user selection with ID {}", saved.getUserSelectionId());
        model.addAttribute("successMessage", "Your data has been saved.");
        return "redirect:/";
    }
}
