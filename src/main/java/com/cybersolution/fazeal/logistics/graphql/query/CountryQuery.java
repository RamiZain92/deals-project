package com.cybersolution.fazeal.logistics.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.cybersolution.fazeal.logistics.dto.CountryDTO;
import com.cybersolution.fazeal.logistics.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CountryQuery implements GraphQLQueryResolver {

    @Autowired
    private CountryService countryService;

    public List<CountryDTO> listAllCountries(){
        return countryService.listAllCountries();
    }

}
