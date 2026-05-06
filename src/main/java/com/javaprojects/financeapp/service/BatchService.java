package com.javaprojects.financeapp.service;

import com.javaprojects.financeapp.model.Trade;
import com.javaprojects.financeapp.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BatchService {

    private final FileService fileService;
    private final TradeRepository tradeRepository;
    private final ProcessingService processingService;

    public BatchService(FileService fileService,
                        TradeRepository tradeRepository,
                        ProcessingService processingService) {
        this.fileService = fileService;
        this.tradeRepository = tradeRepository;
        this.processingService = processingService;
    }

    @Scheduled(fixedRate = 60000)
    public void runBatch() {
        try {
            List<Trade> trades = fileService.readTradesFromFile("input/trades.csv");
            tradeRepository.saveAll(trades);

            log.info("Batch started");
            processingService.process();
            log.info("Batch ended");

        } catch (Exception e) {
            log.error("Error occurred", e);
        }
    }
}
