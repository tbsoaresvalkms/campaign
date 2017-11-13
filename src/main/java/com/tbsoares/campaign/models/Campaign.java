package com.tbsoares.campaign.models;

import com.sun.org.apache.xpath.internal.operations.Equals;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Campaign {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String team;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable
    private final List<Associate> associates = new ArrayList<>();

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public LocalDate addOneDayToEndDate() {
        this.endDate = endDate.plusDays(1);
        return this.endDate;
    }

    public void clearAssociate() {
        associates.clear();
    }

    public Campaign update(Campaign campaign) {
        if (!this.team.equals(campaign.team)) clearAssociate();

        this.name = campaign.name;
        this.team = campaign.team;
        this.startDate = campaign.startDate;
        this.endDate = campaign.endDate;
        this.associates.addAll(campaign.associates);

        return this;
    }

    public void addAssociate(Associate associate) {
        this.associates.add(associate);
    }

    @Override
    public boolean equals(Object t) {
        return this == t || t instanceof Campaign && (this.id == ((Campaign) t).id);
    }
}
