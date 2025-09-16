package core.basesyntax.service.handlers;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class PurchaseOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction) {
        int currentQuantity = Storage.fruitStorage.getOrDefault(transaction
                .getFruit(), 0);
        int newQuantity = currentQuantity - transaction.getQuantity();
        if (newQuantity < 0) {
            throw new RuntimeException("Not enough fruits " + transaction.getFruit() + " available "
            + currentQuantity + ", but " + transaction.getQuantity() + " was bought.");
        }
        Storage.fruitStorage.put(transaction.getFruit(), currentQuantity - transaction
                .getQuantity());
    }
}
