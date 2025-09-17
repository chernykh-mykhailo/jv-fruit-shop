package core.basesyntax.strategy.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;

public class ReturnOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction) {
        int currentQuantity = Storage.getFruitStorage()
                .getOrDefault(transaction.getFruit(), 0);
        int newQuantity = currentQuantity + transaction.getQuantity();
        Storage.setFruitQuantity(transaction.getFruit(), newQuantity);
    }
}
