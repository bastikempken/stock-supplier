package de.kempkensebastian.stocksupplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        StocksupplierApplication.class,
        MongoTestConfiguration.class
})
@ActiveProfiles("test")
class StockSupplierIntegrationTest {

    @Autowired
    private SchedulingService schedulingService;
    @Autowired
    private StockRepository stockRepository;

    @Test
    void shouldGatherData() {
        //When
        schedulingService.process(Arrays.asList("adidas-aktie"));
        //Then
        List<StockEntity> stockEntities = stockRepository.findAll();
        assertThat(stockEntities).hasSize(1);
    }
}
