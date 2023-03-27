package no.idata1002.group19.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.idata1002.group19.domain.entity.Account;
import no.idata1002.group19.domain.entity.User;

@RepositoryRestResource
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAid(@Param("aid") long aid);
    
    List<Account> findByUser(@Param("user") User user);
}
