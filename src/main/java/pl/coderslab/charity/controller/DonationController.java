package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
@RequestMapping(value = "/donation", produces = "text/html;charset=utf-8")
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public DonationController(DonationService donationService
            , InstitutionService institutionService
            , CategoryService categoryService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping(value = "/new")
    public String showDonationForm(Model model) {
        model.addAttribute("newDonation", new Donation());
        return "form";
    }

    @PostMapping(value="/new")
    public String processDonationForm(@ModelAttribute("newDonation") Donation donation, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return"form";
        }
        donationService.save(donation);
        return "redirect:/donation/confirm";
    }

    @GetMapping(value = "/confirm")
    public String showDonationConfirmationPage(){
        return "form-confirmation";
    }

    @ModelAttribute("allCategories")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @ModelAttribute("allInstitutions")
    public List<Institution> getInstitutions() {
        return institutionService.getAll();
    }
}
