package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.Mappers.CampaignMapper;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;



}
