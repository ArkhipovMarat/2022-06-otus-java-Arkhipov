package ru.otus.testframework.service.impl;

import ru.otus.testframework.dto.Message;
import ru.otus.testframework.service.ReportService;

import java.util.List;

public class ReportServiceImpl implements ReportService {
    @Override
    public void printReport(List<Message> messageList) {
        System.out.println("\n-----ОТЧЕТ О ПРОХОЖДЕНИИ ТЕСТОВ-------");
        int count = 0;
        int success = 0;

        for (Message message : messageList) {
            System.out.println(message);

            List<Message.Report> reportList = message.getReportList();
            for (Message.Report mreport : reportList) {
                if (mreport.getMethodType().equals("Test")) {
                    count++;
                }
                if (mreport.getStatus().equals(Message.Status.SUCCESS)
                        && mreport.getMethodType().equals("Test")) {
                    success++;
                }
            }
        }

        System.out.println("\nВСЕГО ПРОВЕДЕНО ТЕСТОВ: " + count);
        System.out.println("ИЗ НИХ УСПЕШНО: " + success);
    }
}
