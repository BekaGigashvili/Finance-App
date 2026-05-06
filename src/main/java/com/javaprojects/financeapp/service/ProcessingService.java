package com.javaprojects.financeapp.service;

import com.javaprojects.financeapp.model.Result;
import com.javaprojects.financeapp.model.Trade;
import com.javaprojects.financeapp.repository.ResultRepository;
import com.javaprojects.financeapp.repository.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProcessingService {

    private final TradeRepository tradeRepository;
    private final ResultRepository resultRepository;

    public ProcessingService(TradeRepository tradeRepository,
                             ResultRepository resultRepository) {
        this.tradeRepository = tradeRepository;
        this.resultRepository = resultRepository;
    }

    public void process() {

        List<Trade> trades = tradeRepository.findAll();

        Map<String, List<Trade>> grouped =
                trades.stream().collect(Collectors.groupingBy(Trade::getSymbol));

        for (String symbol : grouped.keySet()) {

            List<Trade> symbolTrades = grouped.get(symbol);

            int totalQty = symbolTrades.stream().mapToInt(Trade::getQuantity).sum();

            double avgPrice =
                    symbolTrades.stream()
                            .mapToDouble(t -> t.getPrice() * t.getQuantity()).sum() / totalQty;

            double pnl = (120 - avgPrice) * totalQty;

            Result result = new Result();
            result.setSymbol(symbol);
            result.setTotalQuantity(totalQty);
            result.setAvgPrice(avgPrice);
            result.setPnl(pnl);

            resultRepository.save(result);
        }
    }
}
