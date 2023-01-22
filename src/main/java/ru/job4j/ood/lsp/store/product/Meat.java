package ru.job4j.ood.lsp.store.product;

import java.time.LocalDate;

public class Meat extends Food {

    public Meat(String name, LocalDate expiryDate, LocalDate createDate, double discount,
                double price) {
        super(name, expiryDate, createDate, discount, price);
    }
}
