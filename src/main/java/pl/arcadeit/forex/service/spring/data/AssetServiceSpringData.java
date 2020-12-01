package pl.arcadeit.forex.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import pl.arcadeit.forex.domain.Asset;
import pl.arcadeit.forex.repository.AssetRepository;
import pl.arcadeit.forex.service.AssetService;

import java.util.List;
import java.util.Optional;

public class AssetServiceSpringData implements AssetService {

    AssetRepository assetRepository;

    @Autowired
    public AssetServiceSpringData(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> findAllAssets() {
        return assetRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Asset findAssetById(int id) {
        Optional<Asset> result = assetRepository.findById(id);
        Asset asset = null;
        if (result.isPresent()) {
            asset = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return asset;
    }

    @Override
    public void saveAsset(Asset asset) {
        assetRepository.save(asset);
    }

    @Override
    public void deleteAssetById(int id) {
        assetRepository.deleteById(id);
    }
}
