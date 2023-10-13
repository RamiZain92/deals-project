package com.deals.repository;
import com.deals.entity.DealEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface DealRepository extends PagingAndSortingRepository<DealEntity, Long>{

    public Optional<DealEntity> findByDealUniqueId(String dealUniqueId);
}
