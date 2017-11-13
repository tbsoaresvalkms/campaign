package com.tbsoares.campaign.controllers;

import com.tbsoares.campaign.models.Inscription;
import com.tbsoares.campaign.models.ResponseWrapper;
import com.tbsoares.campaign.resources.CampaignResource;
import com.tbsoares.campaign.services.AssociateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "associate")
public class AssociateController extends ApiController {

    private final AssociateService associateService;

    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping
    public ResponseWrapper register(@Valid @RequestBody Inscription inscription) {
        associateService.inscriptionCampaign(inscription);
        return ResponseWrapper.status(HttpStatus.CREATED).message("Successfully subscribed");
    }

    @GetMapping(value = "/campaign", params = {"email"})
    public List<CampaignResource> show(@RequestParam String email) {
        return associateService.getCampaignsByEmail(email);
    }
}
