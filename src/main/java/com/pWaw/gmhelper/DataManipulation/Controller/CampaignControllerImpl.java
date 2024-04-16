package com.pWaw.gmhelper.DataManipulation.Controller;

import com.pWaw.gmhelper.DataManipulation.Controller.Swagger.CampaignController;
import com.pWaw.gmhelper.DataManipulation.DTO.Campaign.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CampaignControllerImpl implements CampaignController {

    private final CampaignService campaignService;
    @Override
    public ResponseEntity<CampaignDto> createCampaign(CampaignDto campaignDto) {
        CampaignDto dto = campaignService.createCampaign(campaignDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(DataPath.ROOT + "/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @Override
    public ResponseEntity<CampaignDto> updateCampaignData(CampaignDto campaignDto) throws CampaignNotExistsException {
        return ResponseEntity.ok().body(campaignService.updateCampaignData(campaignDto));
    }

    @Override
    public ResponseEntity<CampaignDto> getCampaignById(Long id) throws CampaignNotExistsException {
        return ResponseEntity.ok().body(campaignService.getCampaign(id));
    }

    @Override
    public ResponseEntity<Page<CampaignDto>> getAllCampaigns(Integer page, Integer pageSize) {
        return ResponseEntity.ok().body(campaignService.getAllCampaigns(page, pageSize));
    }

    @Override
    public ResponseEntity<Void> deleteCampaign(Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
}
