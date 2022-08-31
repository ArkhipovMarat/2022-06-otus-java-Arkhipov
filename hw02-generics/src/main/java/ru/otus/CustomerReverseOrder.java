package ru.otus;

import java.util.LinkedList;

public class CustomerReverseOrder {
    private final LinkedList<Customer> customerList = new LinkedList<>();

    public void add(Customer customer) {
        customerList.push(customer);
    }

    public Customer take() {
        return customerList.pop();
    }
}
