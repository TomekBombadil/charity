package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationService {

    private DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Long getDonatedBagsCount() {
        return donationRepository.getSumQuantities().orElse(0L);
    }

    public Long getDonationsCount() {
        return donationRepository.getCountDonations().orElse(0L);
    }
}
