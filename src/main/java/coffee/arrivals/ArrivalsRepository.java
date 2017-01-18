package coffee.arrivals;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ArrivalsRepository {

  private final ArrivalsEventStore eventStore;
  private final Map<String, Arrival> arrivals;

  @Inject
  public ArrivalsRepository(ArrivalsEventStore eventStore) {
    this.eventStore = eventStore;
    this.arrivals = new HashMap<>();
  }

  synchronized void add(Arrival a) {
    if (!arrivals.containsValue(a)) {
      arrivals.put(a.getName(), a);
      eventStore.arrivalDeclared();
    }
  }

  synchronized List<Arrival> getAll() {
    return new ArrayList<>(arrivals.values());
  }
}