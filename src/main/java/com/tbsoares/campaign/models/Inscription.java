package com.tbsoares.campaign.models;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inscription {
    @NotNull
    private Long campaignId;

    @Email
    private String email;

    public void toLowerCaseEmail() {
        this.email = this.email.toLowerCase();
    }

}