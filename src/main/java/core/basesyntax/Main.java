package core.basesyntax;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.DataConverterImpl;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.ShopSeviceImpl;
import core.basesyntax.service.file.FileReader;
import core.basesyntax.service.file.FileReaderImpl;
import core.basesyntax.service.file.FileWriter;
import core.basesyntax.service.file.FileWriterImpl;
import core.basesyntax.service.handlers.BalanceOperation;
import core.basesyntax.service.handlers.OperationHandler;
import core.basesyntax.service.handlers.PurchaseOperation;
import core.basesyntax.service.handlers.ReturnOperation;
import core.basesyntax.service.handlers.SupplyOperation;
import core.basesyntax.service.report.ReportGenerator;
import core.basesyntax.service.report.ReportGeneratorImpl;
import core.basesyntax.service.strategy.OperationStrategy;
import core.basesyntax.service.strategy.OperationStrategyImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String INPUT_FILE_PATH = "reportToRead.csv";
    private static final String OUTPUT_FILE_PATH = "finalReport.csv";

    public static void main(String[] arg) {
        FileReader fileReader = new FileReaderImpl();
        List<String> inputReport = null;
        try {
            inputReport = fileReader.read(INPUT_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());
        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);

        ShopService shopService = new ShopSeviceImpl(operationStrategy);
        shopService.process(transactions);

        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String resultingReport = reportGenerator.generate();

        FileWriter fileWriter = new FileWriterImpl();
        try {
            fileWriter.write(resultingReport, OUTPUT_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
