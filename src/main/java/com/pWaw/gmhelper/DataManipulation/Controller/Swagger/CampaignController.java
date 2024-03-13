package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Campaign.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CampaignNotExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CampaignController.DataPath.ROOT)
public interface CampaignController {

    class DataPath {
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/campaigns/";
    }

    @PostMapping
    ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignDto campaignDto);

    @PutMapping
    ResponseEntity<CampaignDto> updateCampaignData(@RequestBody CampaignDto campaignDto) throws CampaignNotExistsException;

    @GetMapping("{id}")
    ResponseEntity<CampaignDto> getCampaignById(@PathVariable Long id) throws CampaignNotExistsException;

    @GetMapping()
    ResponseEntity<List<CampaignDto>> getAllCampaigns();
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCampaign(@PathVariable Long id);
}

