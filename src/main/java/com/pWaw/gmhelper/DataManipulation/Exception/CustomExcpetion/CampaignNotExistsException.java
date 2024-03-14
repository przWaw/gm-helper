package com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CampaignNotExistsException extends Exception {

    public CampaignNotExistsException(String message) {
        super(message);
    }

}
