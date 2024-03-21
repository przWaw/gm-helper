package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Campaign.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CampaignMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    public CampaignDto createCampaign(CampaignDto campaignDto) {
        Campaign campaign = campaignMapper.dtoToCampaign(campaignDto);
        return campaignMapper.campaignToDto(campaignRepository.save(campaign));
    }

    @Transactional
    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    public CampaignDto updateCampaignData(CampaignDto campaignDto) throws CampaignNotExistsException {
        Optional<Campaign> campaign = campaignRepository.findById(campaignDto.getId());
        if(campaign.isEmpty()) {
            throw new CampaignNotExistsException("Wrong id was provided during update operation, campaign does not exists");
        }

        Campaign campaignToUpdate = campaignMapper.dtoToCampaign(campaignDto);
        return campaignMapper.campaignToDto(campaignRepository.save(campaignToUpdate));
    }

    public CampaignDto getCampaign(Long id) throws CampaignNotExistsException {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isEmpty()) {
            throw new CampaignNotExistsException("Wrong id was provided during get operation, campaign does not exists");
        }
        return campaignMapper.campaignToDto(campaign.get());
    }

    public Page<CampaignDto> getAllCampaigns(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return campaignRepository.findAll(pageable).map(campaignMapper::campaignToDto);
    }

}