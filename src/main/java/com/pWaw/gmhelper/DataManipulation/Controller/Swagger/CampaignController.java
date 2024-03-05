package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Campaign.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Exception.EmptyFileSendException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping(CampaignController.DataPath.ROOT)
public interface CampaignController {

    class DataPath {
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/campaign/";
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<CampaignDto> createCampaign(@RequestPart CampaignDto campaignDto, @RequestPart(required = false) MultipartFile image) throws EmptyFileSendException;

    @PutMapping()
    ResponseEntity<CampaignDto> updateCampaignData(@RequestBody CampaignDto campaignDto) throws CampaignNotExistsException;

    @GetMapping("{id}")
    ResponseEntity<CampaignDto> getCampaignById(@PathVariable Long id) throws CampaignNotExistsException;

    @GetMapping("all")
    ResponseEntity<List<CampaignDto>> getAllCampaigns();
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCampaign(@PathVariable Long id);
}

