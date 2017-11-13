package com.tbsoares.campaign.validators;

import com.tbsoares.campaign.commands.ExistsInscription;
import com.tbsoares.campaign.exceptions.AlreadyRegisteredException;
import com.tbsoares.campaign.models.Inscription;
import org.springframework.stereotype.Component;


@Component
public class InscriptionValidator {

    private final ExistsInscription existsInscription;

    public InscriptionValidator(ExistsInscription existsInscription) {
        this.existsInscription = existsInscription;
    }


    public Inscription validate(Inscription inscription) {
        lowerCaseEmail(inscription);
        alreadyRegisteredInCampaign(inscription);
        return inscription;
    }

    private void lowerCaseEmail(Inscription inscription) {
        inscription.toLowerCaseEmail();
    }

    private void alreadyRegisteredInCampaign(Inscription inscription) {
        Long campaignId = inscription.getCampaignId();
        String email = inscription.getEmail();
        Boolean alreadyRegistered = existsInscription.execute(campaignId, email);
        if (alreadyRegistered) throw new AlreadyRegisteredException();
    }
}
