package ru.otus;

import lombok.RequiredArgsConstructor;
import ru.otus.model.Cash;
import ru.otus.service.CashService;

import java.util.List;

@RequiredArgsConstructor
public class AtmServiceImpl implements AtmService {
    private final CashService cashService;

    @Override
    public void put(List<Cash> cash) {
        cashService.put(cash);
    }

    @Override
    public List<Cash> get(long sum) {
        return cashService.get(sum);
    }

    @Override
    public List<Cash> findAll() {
        return cashService.findAll();
    }
}
