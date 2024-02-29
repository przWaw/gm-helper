package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CampaignMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    public CampaignDto createCampaign(CampaignDto campaignDto, MultipartFile image) throws IOException {
        Campaign campaign = campaignMapper.dtoToCampaign(campaignDto);
        if (!image.isEmpty()) {
            setImageFromMultipartFile(image, campaign);
        }
        return campaignMapper.campaignToDto(campaignRepository.save(campaign));
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    public CampaignDto updateCampaignData(CampaignDto campaignDto, MultipartFile image) throws CampaignNotExistsException, IOException {
        Optional<Campaign> campaign = campaignRepository.findById(campaignDto.getId());
        if(campaign.isEmpty()) {
            throw new CampaignNotExistsException();
        }
        Campaign campaignToUpdate = campaignMapper.dtoToCampaign(campaignDto);
        if(image.isEmpty()) {
            campaignToUpdate.setCampaignImage(campaign.get().getCampaignImage());
        }
        if(!image.isEmpty()) {
            setImageFromMultipartFile(image, campaignToUpdate);
        }
        return campaignMapper.campaignToDto(campaignRepository.save(campaignToUpdate));
    }

    public CampaignDto getCampaign(Long id) throws CampaignNotExistsException {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isEmpty()) {
            throw new CampaignNotExistsException();
        }
        return campaignMapper.campaignToDto(campaign.get());
    }

    public List<CampaignDto> getAllCampaigns() {
        return campaignMapper.campaignToDto(campaignRepository.findAll());
    }

    private void setImageFromMultipartFile(MultipartFile image, Campaign campaign) throws IOException {
            campaign.getCampaignImage().builder()
                    .name(image.getName())
                    .fileType(image.getContentType())
                    .imageData(image.getBytes())
                    .build();
    }
}
