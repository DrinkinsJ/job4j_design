package ru.job4j.ood.lsp.store.model;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.store.product.Food;
import ru.job4j.ood.lsp.store.product.Meat;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {

    @Test
    void whenFoodAddIntoShop() {

        LocalDate expiryDate = LocalDate.now().plusDays(20);
        LocalDate createDate = LocalDate.now().minusDays(10);

        Food pork = new Meat("pork", expiryDate, createDate, 15, 300);

        Store shop = new Shop();
        shop.add(pork);

        assertThat(shop.getAllFood()).isEqualTo(List.of(pork));
    }

    @Test
    void whenFoodAddIntoShopWithDiscount() {

        LocalDate expiryDate = LocalDate.now().plusDays(1);
        LocalDate createDate = LocalDate.now().minusDays(10);

        Food pork = new Meat("pork", expiryDate, createDate, 15, 100);
        Store shop = new Shop();
        shop.add(pork);
        pork.setPrice(85.0D);

        assertThat(shop.getAllFood()).isEqualTo(List.of(pork));
    }

}