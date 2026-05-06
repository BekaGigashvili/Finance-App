package com.javaprojects.financeapp.service;

import com.javaprojects.financeapp.model.Trade;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public List<Trade> readTradesFromFile(String filePath) throws IOException {
        List<Trade> trades = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");

            Trade trade = new Trade();
            trade.setSymbol(parts[0]);
            trade.setQuantity(Integer.parseInt(parts[1]));
            trade.setPrice(Double.parseDouble(parts[2]));

            trades.add(trade);
        }

        return trades;
    }
}
