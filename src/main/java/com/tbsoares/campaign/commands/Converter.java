package com.tbsoares.campaign.commands;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;


@Component
public class Converter {
    private final ConversionService conversionService;

    public Converter(@Qualifier("mvcConversionService") ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public <T> T convert(Object var1, Class<T> var2) {
        return conversionService.convert(var1, var2);
    }
}
