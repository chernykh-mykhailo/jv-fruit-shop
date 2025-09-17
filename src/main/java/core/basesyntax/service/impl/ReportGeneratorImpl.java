package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String generate() {
        StringBuilder report = new StringBuilder("fruit,quantity\n");
        for (String fruit : Storage.getFruitStorage().keySet()) {
            report.append(fruit)
                    .append(',')
                    .append(Storage.getFruitStorage().get(fruit))
                    .append('\n');
        }
        return report.toString();
    }
}
