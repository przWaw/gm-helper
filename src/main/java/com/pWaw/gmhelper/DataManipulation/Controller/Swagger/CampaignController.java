package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Campaign.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CampaignNotExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(CampaignController.DataPath.ROOT)
@Tag(name = "Campaign Controller", description = "Class for campaign data manipulation.")
public interface CampaignController {

    class DataPath {
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/campaigns/";
    }

    @Operation(summary = "Create campaign", description = "Post with body describing campaign to create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Campaign created")
    })
    @PostMapping
    ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignDto campaignDto);

    @Operation(summary = "Update campaign", description = "Put with body containing updated form of campaign")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campaign successfully updated"),
            @ApiResponse(responseCode = "404", description = "Campaign with given id was not found in database")
    })
    @PutMapping
    ResponseEntity<CampaignDto> updateCampaignData(@RequestBody CampaignDto campaignDto) throws CampaignNotExistsException;

    @Operation(summary = "Get campaign", description = "Get campaign with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campaign successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Campaign with given id was not found in database")
    })
    @GetMapping("{id}")
    ResponseEntity<CampaignDto> getCampaignById(@PathVariable Long id) throws CampaignNotExistsException;

    @Operation(summary = "Get all campaigns", description = "Get list of all present campaigns in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all campaigns successfully retrieved"),
    })
    @GetMapping
    ResponseEntity<Page<CampaignDto>> getAllCampaigns(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize);

    @Operation(summary = "Delete campaign", description = "Delete campaign with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Campaign was successfully deleted")
    })
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCampaign(@PathVariable Long id);
}

