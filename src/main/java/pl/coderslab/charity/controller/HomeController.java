package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }


    @GetMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("donatedBagsCount", donationService.getDonatedBagsCount());
        model.addAttribute("donationsCount", donationService.getDonationsCount());
        return "index";
    }

    @ModelAttribute("allInstitutions")
    public List<Institution> getInstitutions() {
        return institutionService.getAll();
    }
}
