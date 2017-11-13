package com.tbsoares.campaign.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tbsoares.campaign.models.Associate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class CampaignResource {
    @Setter
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String team;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @JsonIgnore
    private final List<Associate> associates = new ArrayList<>();

    public void toUpperCaseTeam() {
        team = team.toUpperCase();
    }

    public void addAssociate(Associate associate) {
        associates.add(associate);
    }
}
