package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.converter.CashBoxConverter;
import ru.otus.converter.GreedyCashBoxConverter;
import ru.otus.exception.CashBoxConverterException;
import ru.otus.model.CashBox;
import ru.otus.model.Nominal;
import ru.otus.repository.CashBoxRepository;
import ru.otus.repository.CashBoxRepositoryImpl;
import ru.otus.service.CashService;
import ru.otus.service.CashServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CashBoxServiceTest {
    private CashService cashService;

    @BeforeEach
    public void beforeEach() {
        List<CashBox> cashBoxes = new ArrayList<>();
        cashBoxes.add(new CashBox(Nominal.R5000, 1));
        cashBoxes.add(new CashBox(Nominal.R100, 1));
        cashBoxes.add(new CashBox(Nominal.R200, 1));
        cashBoxes.add(new CashBox(Nominal.R500, 1));
        cashBoxes.add(new CashBox(Nominal.R1000, 1));

        CashBoxRepository cashBoxRepository = new CashBoxRepositoryImpl(cashBoxes);
        CashBoxConverter cashConverter = new GreedyCashBoxConverter();
        cashService = new CashServiceImpl(cashBoxRepository, cashConverter);
    }

    @DisplayName("Тест метода внести наличные - упешно")
    @Test
    public void putCash_ShouldPassSuccess() {
        // given
        List<CashBox> cashBoxes = List.of(
                new CashBox(Nominal.R100, 1),
                new CashBox(Nominal.R200, 1),
                new CashBox(Nominal.R500, 1),
                new CashBox(Nominal.R1000, 1),
                new CashBox(Nominal.R5000, 1));

        cashService.put(cashBoxes);

        List<CashBox> availableCashBoxes = cashService.getAvailable();
        availableCashBoxes.forEach(v -> assertEquals(2L, v.getCount()));
        assertEquals(13600, toSum(availableCashBoxes));
    }

    @DisplayName("Тест метода получить наличные - упешно")
    @Test
    public void getCash_ShouldPassSuccess() {
        // given
        int sum = 6800;

        cashService.get(sum);

        List<CashBox> availableCashBoxes = cashService.getAvailable();
        availableCashBoxes.forEach(v -> assertEquals(0L, v.getCount()));
        assertEquals(0, toSum(availableCashBoxes));
    }

    @DisplayName("Тест метода узнать информацию о количестве оставшихся наличных - упешно")
    @Test
    public void getAvailableCash_ShouldPassSuccess() {
        List<CashBox> availableCashBoxes = cashService.getAvailable();

        assertEquals(5, availableCashBoxes.size());
        availableCashBoxes.forEach(v -> assertEquals(1L, v.getCount()));
        assertEquals(6800, toSum(availableCashBoxes));
    }

    @DisplayName("Тест метода получить наличные - неуспешно - в банкомате недостаточно средств")
    @Test
    public void getAvailableCash_ShouldThrowCashConverterException() {
        // given
        int sum = 6900;

        assertThrows(CashBoxConverterException.class, () -> cashService.get(sum));
    }

    private long toSum(List<CashBox> cashBoxList) {
        return cashBoxList.stream()
                .flatMapToLong(cashBox -> LongStream.of(cashBox.getNominal().getValue() * cashBox.getCount()))
                .sum();
    }
}
