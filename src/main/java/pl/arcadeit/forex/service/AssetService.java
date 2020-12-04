package pl.arcadeit.forex.service;

import pl.arcadeit.forex.domain.Asset;

import java.util.List;

public interface AssetService {
    public List<Asset> findAllAssets();

    public Asset findAssetById(int id);

    public Asset findAssetByName(String name);

    public void saveAsset(Asset asset);

    public void deleteAssetById(int id);
}
