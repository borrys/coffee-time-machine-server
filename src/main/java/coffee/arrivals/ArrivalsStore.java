package coffee.arrivals;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrivalsStore {
  private Map<String, Arrival> arrivals = new HashMap<>();

  public void add(String name, ZonedDateTime time) {
    arrivals.put(name, new Arrival(name, time));
  }

  public List<Arrival> getAll() {
    return new ArrayList<>(arrivals.values());
  }
}
