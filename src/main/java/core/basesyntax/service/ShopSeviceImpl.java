package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.handlers.OperationHandler;
import core.basesyntax.service.strategy.OperationStrategy;
import java.util.List;

public class ShopSeviceImpl implements ShopService {
    private final OperationStrategy operationStrategy;

    public ShopSeviceImpl(OperationStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
    }

    @Override
    public void process(List<FruitTransaction> transactions) {
        for (FruitTransaction transaction : transactions) {
            OperationHandler handler = operationStrategy.get(transaction.getOperation());
            handler.handle(transaction);
        }
    }
}
