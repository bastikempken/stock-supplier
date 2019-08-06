package de.kempkensebastian.stocksupplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StocksupplierApplication.class)
@ActiveProfiles("test")
public class StockSupplierIntegrationTest {
    @Test
    void shouldGatherData() {
        assertTrue(true);

    }
}
