package com.pWaw.mghelper.DataManipulation.Service;

import com.pWaw.mghelper.DataManipulation.Mappers.CampaignMapper;
import com.pWaw.mghelper.DataManipulation.Repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;



}
