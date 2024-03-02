package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.DTO.ImageDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Exception.EmptyFileSendException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CampaignMapper;
import com.pWaw.gmhelper.DataManipulation.Mappers.ImageMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import com.pWaw.gmhelper.DataManipulation.Model.Image;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public CampaignDto createCampaign(CampaignDto campaignDto, MultipartFile image) throws EmptyFileSendException {
        Campaign campaign = campaignMapper.dtoToCampaign(campaignDto);
        if (image.isEmpty()) {
            campaign.setCampaignImage(null);
        } else {
            campaign.setCampaignImage(imageMapper.dtoToImage(ImageDto.readFromMultipart(image)));
        }
        return campaignMapper.campaignToDto(campaignRepository.save(campaign));
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    public CampaignDto updateCampaignData(CampaignDto campaignDto) throws CampaignNotExistsException {
        Optional<Campaign> campaign = campaignRepository.findById(campaignDto.getId());
        if(campaign.isEmpty()) {
            throw new CampaignNotExistsException();
        }
        Image image;
        if(campaignDto.getImageId() == null) {
            Long imageId = campaign.get().getCampaignImage().getId();
            image = imageRepository.findById(imageId).orElse(null);
        } else {
            image = imageRepository.findById(campaignDto.getImageId()).orElse(null);
        }
        Campaign campaignToUpdate = campaignMapper.dtoToCampaign(campaignDto);
        campaignToUpdate.setCampaignImage(image);
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

}
