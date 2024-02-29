package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CampaignNotExistsException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping(CampaignController.DataPath.ROOT)
public interface CampaignController {

    class DataPath {
        private DataPath() {};
        public static final String ROOT = "api/gm-helper/data/campaign/";
    }

    @PostMapping(path = DataPath.ROOT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<CampaignDto> createCampaign(@RequestPart CampaignDto campaignDto, @RequestPart(required = false) MultipartFile image) throws IOException;

    @PutMapping(path = DataPath.ROOT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<CampaignDto> updateCampaignData(@RequestPart CampaignDto campaignDto, @RequestPart(required = false) MultipartFile image) throws IOException, CampaignNotExistsException;

    @GetMapping(DataPath.ROOT + "/{id}")
    ResponseEntity<CampaignDto> getCampaignById(@PathVariable Long id) throws CampaignNotExistsException;

    @GetMapping(DataPath.ROOT + "all")
    ResponseEntity<List<CampaignDto>> getAllCampaigns();
    @DeleteMapping(DataPath.ROOT + "/{id}")
    ResponseEntity<?> deleteCampaign(@PathVariable Long id);
}

