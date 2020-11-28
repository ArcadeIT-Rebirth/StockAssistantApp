package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.Asset;

public interface AssetRepository extends JpaRepository<Asset, String> {
}
