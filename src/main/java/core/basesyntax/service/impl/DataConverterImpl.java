package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;

import java.util.List;
import java.util.stream.Collectors;

public class DataConverterImpl implements DataConverter {
    @Override
    public List<FruitTransaction> convertToTransaction(List<String> inputReport) {
        if (inputReport == null || inputReport.isEmpty()) {
            throw new RuntimeException("Input report cannot be null or empty");
        }
        if (!inputReport.get(0).trim().equals("type,fruit,quantity")) {
            throw new RuntimeException("Incorrect file header format.");
        }
        return inputReport.stream()
                .skip(1)
                .map(this::parseLine)
                .collect(Collectors.toList());
    }

    private FruitTransaction parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new RuntimeException("Invalid line format: " + line);
        }

        try {
            String operationCode = parts[0].trim();
            String fruit = parts[1].trim();
            int quantity = Integer.parseInt(parts[2].trim());

            FruitTransaction.Operation operation = FruitTransaction.Operation.getByCode(operationCode);

            if(quantity < 0) {
                throw new RuntimeException("Quantity cannot be negative: " + line);
            }
            if (fruit.trim().isEmpty()) {
                throw new RuntimeException("Fruit name cannot be empty: " + line);
            }

            return new FruitTransaction(operation, fruit, quantity);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error parsing line: " + line, e);
        }
    }
}
