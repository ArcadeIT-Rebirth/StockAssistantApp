package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.arcadeit.forex.model.User;

@RepositoryRestResource(path = "slave")
public interface UserRepository extends JpaRepository<User, Long> {

}
