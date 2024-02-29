package com.pWaw.gmhelper.DataManipulation.Controller;

import com.pWaw.gmhelper.DataManipulation.Controller.Swagger.CampaignController;
import com.pWaw.gmhelper.DataManipulation.DTO.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CampaignControllerImpl implements CampaignController {

    private final CampaignService campaignService;
    @Override
    public ResponseEntity<CampaignDto> createCampaign(CampaignDto campaignDto, MultipartFile image) throws IOException {
        return new ResponseEntity(campaignService.createCampaign(campaignDto, image), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CampaignDto> updateCampaignData(CampaignDto campaignDto, MultipartFile image) throws IOException, CampaignNotExistsException {
        return new ResponseEntity<>(campaignService.updateCampaignData(campaignDto, image), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CampaignDto> getCampaignById(Long id) throws CampaignNotExistsException {
        return ResponseEntity.ok().body(campaignService.getCampaign(id));
    }

    @Override
    public ResponseEntity<List<CampaignDto>> getAllCampaigns() {
        return ResponseEntity.ok().body(campaignService.getAllCampaigns());
    }

    @Override
    public ResponseEntity<Void> deleteCampaign(Long id) {
        return ResponseEntity.noContent().build();
    }
}
