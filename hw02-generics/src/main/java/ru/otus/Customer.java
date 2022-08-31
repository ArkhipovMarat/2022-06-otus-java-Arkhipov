package ru.otus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Data
@AllArgsConstructor
public class Customer {
    private final long id;
    private String name;
    private long scores;

    public Customer(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getScores());
    }
}
