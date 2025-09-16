package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import java.util.List;
import java.util.stream.Collectors;

public class DataConverterImpl implements DataConverter {
    @Override
    public List<FruitTransaction> convertToTransaction(List<String> inputReport) {
        return inputReport.stream()
                .skip(1)
                .map(this::parseLine)
                .collect(Collectors.toList());
    }

    private FruitTransaction parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        String operationCode = parts[0].trim();
        String fruit = parts[1].trim();
        int quantity = Integer.parseInt(parts[2].trim());

        FruitTransaction.Operation operation = FruitTransaction.Operation.getByCode(operationCode);

        return new FruitTransaction(operation, fruit, quantity);
    }
}
