package core.basesyntax.service.handlers;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class SupplyOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction) {
        int currentQuantity = Storage.getFruitStorage()
                .getOrDefault(transaction.getFruit(), 0);
        Storage.setFruitQuantity(transaction.getFruit(),
                currentQuantity + transaction.getQuantity());
    }
}
