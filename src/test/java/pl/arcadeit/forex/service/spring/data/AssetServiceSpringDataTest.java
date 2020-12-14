package pl.arcadeit.forex.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.arcadeit.forex.domain.Asset;
import pl.arcadeit.forex.repository.AssetRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssetServiceSpringDataTest {

    private final Asset firstAsset = new Asset(100, "Asset_One");
    private final Asset secondAsset = new Asset(101, "Asset_Two");
    private final Asset thirdAsset = new Asset(102, "Asset_Three");

    private final List<Asset> assets = List.of(firstAsset, secondAsset, thirdAsset);

    @InjectMocks
    AssetServiceSpringData service;

    @Mock
    AssetRepository repository;


    @Test
    void findAllAssets_shouldReturnAssets() {
        //given
        when(repository.findAllByOrderByIdAsc()).thenReturn(assets);
        //when
        List<Asset> result = service.findAllAssets();
        //then
        assertEquals(assets, result);
        verify(repository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void findAssetById_shouldReturnFirstAsset() {
        //given
        when(repository.findById(100)).thenReturn(Optional.of(firstAsset));
        //when
        Asset result = service.findAssetById(100);
        //then
        assertEquals(firstAsset, result);
        verify(repository, times(1)).findById(100);
    }

    @Test
    void findAssetByName_shouldReturnFirstAsset() {
        //given
        when(repository.findAssetByName("Asset_One")).thenReturn(firstAsset);
        //when
        Asset result = service.findAssetByName("Asset_One");
        //then
        assertEquals(firstAsset, result);
        verify(repository, times(1)).findAssetByName("Asset_One");
    }

    @Test
    void saveAsset_shouldInvokeRepositorySave() {
        //given
        //when
        service.saveAsset(firstAsset);
        service.saveAsset(secondAsset);
        //then
        verify(repository, times(1)).save(firstAsset);
        verify(repository, times(2)).save(any(Asset.class));
    }

    @Test
    void deleteAssetById_shouldInvokeRepositoryDelete() {
        //given
        //when
        service.deleteAssetById(1);
        service.deleteAssetById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(anyInt());
    }
}
