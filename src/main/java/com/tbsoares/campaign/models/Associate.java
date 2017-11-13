package com.tbsoares.campaign.models;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Associate {
    @Id
    @GeneratedValue
    private Long id;

    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "associates")
    private List<Campaign> campaigns;

    @CreatedDate
    private LocalDateTime createdAt;
}
