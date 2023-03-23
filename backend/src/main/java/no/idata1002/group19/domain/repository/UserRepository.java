package no.idata1002.group19.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.idata1002.group19.domain.entity.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long>{
    
    Optional<User> findByUsername(String username);
}
