package pl.arcadeit.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arcadeit.forex.domain.Asset;
import pl.arcadeit.forex.service.AssetService;
import pl.arcadeit.forex.service.spring.data.AssetServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssetController {

    AssetService service;

    @Autowired
    public AssetController(AssetServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/assets")
    public List<Asset> getAllAssets(){
        return service.findAllAssets();
    }

    @GetMapping("/assets/id/{assetId}")
    public Asset getAsset(@PathVariable int assetId) {
        Asset asset = service.findAssetById(assetId);
        if (asset == null) {
            throw new RuntimeException("no such Id in database");
        }
        return asset;
    }

    @GetMapping("/assets/name/{assetName}")
    public Asset getAsset(@PathVariable String assetName) {
        return  service.findAssetByName(assetName);
    }

    @PostMapping("/assets")
    public Asset addAsset(@RequestBody Asset asset) {
        asset.setId(0);
        service.saveAsset(asset);
        return asset;
    }

    @PutMapping("/assets")
    public Asset updateAsset(@RequestBody Asset asset) {
        service.saveAsset(asset);
        return asset;
    }

    @DeleteMapping("/asset/{assetId}")
    public String deleteAsset(@PathVariable int asseetId) {
        Asset asset = service.findAssetById(asseetId);
        if (asset == null) {
            throw new RuntimeException("No such Id in database");
        }
        service.deleteAssetById(asseetId);
        return "Asset successfully deleted";
    }

}
