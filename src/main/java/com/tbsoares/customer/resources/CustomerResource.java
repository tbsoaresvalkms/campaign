package com.tbsoares.customer.resources;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResource implements Serializable{

    private Long id;

    @Email
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    private String team;

    private LocalDate birthDate;

}
