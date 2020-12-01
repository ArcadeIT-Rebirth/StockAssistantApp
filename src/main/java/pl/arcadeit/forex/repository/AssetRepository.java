package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.Asset;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    public List<Asset> findAllByOrderByIdAsc();
}
