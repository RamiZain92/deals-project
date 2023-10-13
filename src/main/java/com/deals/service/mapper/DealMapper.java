package com.deals.service.mapper;
import com.deals.dto.DealDTO;
import com.deals.entity.DealEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DealMapper {

    public DealEntity saveDtoToEntity(DealDTO dealDTO) {
        DealEntity dealEntity = new DealEntity();
        dealEntity.setDealUniqueId(dealDTO.getDealUniqueId());
        dealEntity.setFromCurrency(dealDTO.getFromCurrency().toString());
        dealEntity.setToCurrency(dealDTO.getToCurrency().toString());
        dealEntity.setDealTime(LocalDateTime.parse(dealDTO.getDealTime()));
        dealEntity.setDealAmount(dealDTO.getDealAmount());
        return dealEntity;
    }

}
