package no.idata1002.group19.domain.repository;

import no.idata1002.group19.domain.entity.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ValueRepository extends CrudRepository<Value, Long> {
}
