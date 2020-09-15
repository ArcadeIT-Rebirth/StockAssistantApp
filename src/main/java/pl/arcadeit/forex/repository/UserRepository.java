package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
