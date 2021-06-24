package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Authority;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("select a from Authority a where a.user=true")
    public Optional<Authority> findRoleUser();
}
