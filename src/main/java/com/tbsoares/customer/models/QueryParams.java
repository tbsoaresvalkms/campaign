package com.tbsoares.customer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QueryParams {
    private String email;
}
