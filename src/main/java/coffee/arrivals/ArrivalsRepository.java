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

  void add(Arrival a) {
    arrivals.put(a.getName(), a);
    eventStore.arrivalDeclared();
  }

  List<Arrival> getAll() {
    return new ArrayList<>(arrivals.values());
  }
}