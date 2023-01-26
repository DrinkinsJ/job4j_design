package ru.job4j.ood.lsp.store.engine;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.store.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.store.calculator.LocalDateExpirationCalculator;
import ru.job4j.ood.lsp.store.model.Shop;
import ru.job4j.ood.lsp.store.model.Store;
import ru.job4j.ood.lsp.store.model.Trash;
import ru.job4j.ood.lsp.store.model.Warehouse;
import ru.job4j.ood.lsp.store.product.Food;
import ru.job4j.ood.lsp.store.product.Meat;
import ru.job4j.ood.lsp.store.product.Milk;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    @Test
    void whenFoodsAddIntoStore() {
        Food pork = new Meat("pork", LocalDate.now().plusDays(20), LocalDate.now().minusDays(10), 15, 300);
        Food milk = new Milk("milk", LocalDate.now().minusDays(1), LocalDate.now().minusDays(2), 15, 300);
        Food kefir = new Milk("kefir", LocalDate.now().plusDays(10), LocalDate.now().minusDays(1), 15, 300);
        ExpirationCalculator<LocalDate> expirationCalculator = new LocalDateExpirationCalculator();
        Store shop = new Shop(expirationCalculator);
        Store warehouse = new Warehouse(expirationCalculator);
        Store trash = new Trash(expirationCalculator);
        ControlQuality controlQuality = new ControlQuality(List.of(shop, trash, warehouse));
        controlQuality.addToStorage(pork);
        controlQuality.addToStorage(milk);
        controlQuality.addToStorage(kefir);
        assertThat(shop.getAllFood()).isEqualTo(List.of(pork));
        assertThat(warehouse.getAllFood()).isEqualTo(List.of(kefir));
        assertThat(trash.getAllFood()).isEqualTo(List.of(milk));
    }
}