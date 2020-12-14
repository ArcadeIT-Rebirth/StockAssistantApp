package pl.arcadeit.forex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import pl.arcadeit.forex.domain.Asset;
import pl.arcadeit.forex.service.spring.data.AssetServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AssetControllerTest {

    private final Asset firstAsset = new Asset(100, "Asset_One");
    private final Asset secondAsset = new Asset(101, "Asset_Two");
    private final Asset thirdAsset = new Asset(102, "Asset_Three");

    private final List<Asset> assets = List.of(firstAsset, secondAsset, thirdAsset);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private AssetController assetController;

    @Mock
    private AssetServiceSpringData assetService;

    @BeforeEach
    void setUp() {
        assetController = new AssetController(assetService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(assetController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllAssets_shouldReturnAssets() throws Exception {
        //given
        when(assetService.findAllAssets()).thenReturn(assets);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/asset/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<Asset> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Asset>>() {
                });
        assertEquals(assets, result);
    }

    @Test
    void getAssetById_shouldReturnFirstAsset() throws Exception {
        //given
        when(assetService.findAssetById(100)).thenReturn(firstAsset);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/asset/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final Asset result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Asset>() {
                });
        assertEquals(firstAsset, result);
    }

    @Test
    void getAssetByNameTest_shouldReturnFirstAsset() throws Exception {
        //given
        when(assetService.findAssetByName("Asset_One")).thenReturn(firstAsset);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/asset/name/Asset_One")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final Asset result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Asset>() {
                });
        assertEquals(firstAsset, result);
    }

    @Test
    void addAssetTest_shouldInvokePostSaveAssetOnce() throws Exception {
        //given
        doNothing().when(assetService).saveAsset(any(Asset.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/asset/register")
                        .content(objectMapper.writeValueAsString(firstAsset))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(assetService, times(1)).saveAsset(any(Asset.class));
    }

    @Test
    void updateAsset_shouldInvokePutSaveAssetOnce() throws Exception {
        //given
        doNothing().when(assetService).saveAsset(any(Asset.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/asset/register")
                        .content(objectMapper.writeValueAsString(firstAsset))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(assetService, times(1)).saveAsset(any(Asset.class));
    }

    @Test
    void deleteAssetTest_shouldInvokeDeleteAssetByIdOnce() throws Exception {
        //given
        doNothing().when(assetService).deleteAssetById(anyInt());
        when(assetService.findAssetById(anyInt())).thenReturn(firstAsset);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/asset/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(assetService, times(1)).deleteAssetById(anyInt());
    }
}
