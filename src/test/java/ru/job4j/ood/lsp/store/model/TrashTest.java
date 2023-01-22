package ru.job4j.ood.lsp.store.model;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.store.product.Food;
import ru.job4j.ood.lsp.store.product.Meat;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {

    @Test
    void whenFoodIsGoneAndAddIntoTrash() {

        LocalDate expiryDate = LocalDate.now().minusDays(1);
        LocalDate createDate = LocalDate.now().minusDays(2);

        Food pork = new Meat("pork", expiryDate, createDate, 15, 300);
        Store trash = new Trash();
        trash.add(pork);

        assertThat(trash.getAllFood()).isEqualTo(List.of(pork));
    }

}