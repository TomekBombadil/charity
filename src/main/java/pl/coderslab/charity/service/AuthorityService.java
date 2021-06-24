package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.repository.AuthorityRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority findRoleUser(){
        return authorityRepository.findRoleUser().orElseThrow(()->{throw new EntityNotFoundException("No ROLE_USER in Authority");});
    }
}
