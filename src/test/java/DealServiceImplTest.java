import com.deals.configurations.Messages;
import com.deals.constants.AppConstants;
import com.deals.dto.DealDTO;
import com.deals.dto.MessageResponse;
import com.deals.entity.DealEntity;
import com.deals.repository.DealRepository;
import com.deals.service.impl.DealServiceImpl;
import com.deals.service.mapper.DealMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DealServiceImplTest {
    @InjectMocks
    private DealServiceImpl dealService;

    @Mock
    private DealMapper dealMapper;

    @Mock
    private Messages messages;

    @Mock
    private DealRepository dealRepository;

    @Test
    public void testSaveDealWhenDealDoesNotExist() {
        // Arrange
        DealDTO dealDTO = new DealDTO();
        dealDTO.setDealUniqueId("unique123");

        when(dealRepository.findByDealUniqueId("unique123")).thenReturn(Optional.empty());
        when(dealMapper.saveDtoToEntity(dealDTO)).thenReturn(new DealEntity());

        // Act
        MessageResponse response = dealService.saveDeal(dealDTO);

        // Assert
        assertEquals(AppConstants.DEAL_IMPORTED_SUCCESSFULLY, response.getMessage());
    }

    @Test
    public void testSaveDealWhenDealAlreadyExists() {
        // Arrange
        DealDTO dealDTO = new DealDTO();
        dealDTO.setDealUniqueId("unique123");
        DealEntity existingDeal = new DealEntity();

        when(dealRepository.findByDealUniqueId("unique123")).thenReturn(Optional.of(existingDeal));

        // Act
        MessageResponse response = dealService.saveDeal(dealDTO);

        // Assert
        assertEquals(AppConstants.DEAL_ALREADY_EXISTS, response.getMessage());
    }
}
