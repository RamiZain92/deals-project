package com.deals.service;
import com.deals.dto.DealDTO;
import com.deals.dto.MessageResponse;

public interface DealService {

    public MessageResponse saveDeal(DealDTO dealDTO);

}
