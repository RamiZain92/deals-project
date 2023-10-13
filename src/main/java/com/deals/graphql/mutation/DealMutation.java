package com.deals.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.deals.dto.DealDTO;
import com.deals.dto.MessageResponse;
import com.deals.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DealMutation implements GraphQLMutationResolver {

    @Autowired
    private DealService dealService;

    public MessageResponse saveDeal(DealDTO dealDTO) {
        return dealService.saveDeal(dealDTO);
    }
}
