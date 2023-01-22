package ru.job4j.ood.lsp.store.model;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.store.product.Food;
import ru.job4j.ood.lsp.store.product.Meat;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseTest {

    @Test
    void whenFoodAddIntoWarehouse() {

        LocalDate expiryDate = LocalDate.now().plusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(1);

        Food pork = new Meat("pork", expiryDate, createDate, 15, 300);

        Store warehouse = new Warehouse();
        warehouse.add(pork);

        assertThat(warehouse.getAllFood()).isEqualTo(List.of(pork));
    }
}