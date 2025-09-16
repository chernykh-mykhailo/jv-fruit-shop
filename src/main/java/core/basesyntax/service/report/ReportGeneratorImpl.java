package core.basesyntax.service.report;

import core.basesyntax.db.Storage;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String generate() {
        StringBuilder report = new StringBuilder("fruit,quantity\n");
        for (String fruit : Storage.fruitStorage.keySet()) {
            report.append(fruit)
                    .append(',')
                    .append(Storage.fruitStorage.get(fruit))
                    .append('\n');
        }
        return report.toString();
    }
}
