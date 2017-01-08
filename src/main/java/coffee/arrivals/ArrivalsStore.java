package coffee.arrivals;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ArrivalsStore {
  private Map<String, Arrival> arrivals = new HashMap<>();

  void add(Arrival a) {
    arrivals.put(a.getName(), a);
  }

  List<Arrival> getAll() {
    return new ArrayList<>(arrivals.values());
  }
}