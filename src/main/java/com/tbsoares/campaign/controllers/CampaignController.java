package com.tbsoares.campaign.controllers;

import com.tbsoares.campaign.models.ResponseWrapper;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.services.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "campaign")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<CampaignResource> index() {
        return campaignService.allCampaignActive();
    }

    @GetMapping(value = "/team/{team}")
    public List<CampaignResource> indexByTeam(@PathVariable String team) {
        return campaignService.allCampaignActiveByTeam(team);
    }

    @PostMapping
    public ResponseEntity<CampaignResource> create(@Valid @RequestBody CampaignResource campaignResource) {
        campaignResource.setId(-1L);
        CampaignResource campaignCreated = campaignService.create(campaignResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(campaignCreated);
    }

    @GetMapping(value = "/{id}")
    public CampaignResource show(@PathVariable Long id) {
        return campaignService.getCampaign(id);
    }

    @PutMapping(value = "/{id}")
    public CampaignResource update(@PathVariable Long id, @Valid @RequestBody CampaignResource campaignResource) {
        campaignResource.setId(id);
        return campaignService.update(campaignResource);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper delete(@PathVariable Long id) {
        campaignService.delete(id);
        return ResponseWrapper.status(HttpStatus.OK).message("Campaign deleted with success");
    }
}
