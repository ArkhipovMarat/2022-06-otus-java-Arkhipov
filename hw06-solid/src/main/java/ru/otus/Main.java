package ru.otus;

import ru.otus.model.Cash;
import ru.otus.model.CurrencyValue;
import ru.otus.repository.CashRepository;
import ru.otus.repository.CashRepositoryImpl;
import ru.otus.service.CashService;
import ru.otus.service.CashServiceImpl;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            CashRepository cashRepository = new CashRepositoryImpl();
            CashService cashService = new CashServiceImpl(cashRepository);
            AtmService atmService = new AtmServiceImpl(cashService);

            List<Cash> all = atmService.findAll();
            List<Cash> cash = atmService.get(11000);
            List<Cash> all1 = atmService.findAll();
            System.out.println();
        } catch (Exception e) {
            System.out.println();
        }
        System.out.println();


    }
}