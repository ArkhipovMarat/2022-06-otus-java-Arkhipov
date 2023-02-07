package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Cash;
import ru.otus.model.Nominal;
import ru.otus.repository.CashRepository;
import ru.otus.repository.CashRepositoryImpl;
import ru.otus.service.CashService;
import ru.otus.service.CashServiceImpl;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashServiceTest {
    private CashService cashService;

    @BeforeEach
    public void beforeEach() {
        Map<Nominal, Long> cashMap = new EnumMap<>(Nominal.class);
        cashMap.put(Nominal.R100, 1L);
        cashMap.put(Nominal.R200, 1L);
        cashMap.put(Nominal.R500, 1L);
        cashMap.put(Nominal.R1000, 1L);
        cashMap.put(Nominal.R5000, 1L);

        CashRepository cashRepository = new CashRepositoryImpl(cashMap);
        cashService = new CashServiceImpl(cashRepository);
    }

    @DisplayName("Тест метода внести наличные - упешно")
    @Test
    public void putCash_ShouldPassSuccess() {
        // given
        List<Cash> cash = List.of(
                new Cash(Nominal.R100, 1L),
                new Cash(Nominal.R200, 1L),
                new Cash(Nominal.R500, 1L),
                new Cash(Nominal.R1000, 1L),
                new Cash(Nominal.R5000, 1L));

        cashService.put(cash);

        List<Cash> availableCash = cashService.getAvailable();
        availableCash.forEach(v -> assertEquals(2L, v.getCount()));
        assertEquals(13600, toSum(availableCash));
    }

    @DisplayName("Тест метода получить наличные - упешно")
    @Test
    public void getCash_ShouldPassSuccess() {
        // given
        long sum = 6800;

        cashService.get(sum);

        List<Cash> availableCash = cashService.getAvailable();
        availableCash.forEach(v -> assertEquals(0L, v.getCount()));
        assertEquals(0, toSum(availableCash));
    }

    @DisplayName("Тест метода узнать информацию о количестве оставшихся наличных - упешно")
    @Test
    public void getAvailableCash_ShouldPassSuccess() {
        List<Cash> availableCash = cashService.getAvailable();

        assertEquals(5, availableCash.size());
        availableCash.forEach(v -> assertEquals(1L, v.getCount()));
        assertEquals(6800, toSum(availableCash));
    }

    private long toSum(List<Cash> cashList) {
       return cashList.stream()
                .flatMapToLong(cash -> LongStream.of(cash.getNominal().getValue() * cash.getCount()))
                .sum();
    }
}
