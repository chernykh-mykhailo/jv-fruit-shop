package core.basesyntax.db;

import java.util.Map;

public interface Storage {
  void updateQuantity(String fruit, int quantity);
  int getQuantity(String fruit);
  Map<String, Integer> getAllFruits();
}
