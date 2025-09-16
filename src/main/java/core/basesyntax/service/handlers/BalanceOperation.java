package core.basesyntax.service.handlers;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class BalanceOperation implements OperationHandler {

    @Override
    public void handle(FruitTransaction transaction) {
        Storage.setFruitQuantity(transaction.getFruit(), transaction.getQuantity());
    }
}
