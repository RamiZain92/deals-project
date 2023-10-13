package com.deals.service.impl;
import com.deals.configurations.Messages;
import com.deals.constants.AppConstants;
import com.deals.dto.DealDTO;
import com.deals.dto.MessageResponse;
import com.deals.entity.DealEntity;
import com.deals.repository.DealRepository;
import com.deals.service.DealService;
import com.deals.service.mapper.DealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DealServiceImpl implements DealService {
	@Autowired
	private DealMapper dealMapper;
	@Autowired
	private Messages messages;
	@Autowired
	private DealRepository dealRepository;

	public MessageResponse saveDeal(DealDTO dealDTO) {
		Optional<DealEntity> existingDeal = dealRepository.findByDealUniqueId(dealDTO.getDealUniqueId());
		if (!existingDeal.isPresent()) {
			dealRepository.save(dealMapper.saveDtoToEntity(dealDTO));
			return MessageResponse.builder().message(AppConstants.DEAL_IMPORTED_SUCCESSFULLY).build();
		}
		return MessageResponse.builder().message(AppConstants.DEAL_ALREADY_EXISTS).build();
	}
	
}