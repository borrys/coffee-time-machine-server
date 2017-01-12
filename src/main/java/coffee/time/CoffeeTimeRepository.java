package coffee.time;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.Optional;

@Singleton
public class CoffeeTimeRepository {
  private Instant coffeeTime;
  private final CoffeeTimeEventsStore eventsStore;

  @Inject
  public CoffeeTimeRepository(CoffeeTimeEventsStore eventsStore) {
    this.eventsStore = eventsStore;
  }

  void setCoffeeTime(Instant coffeeTime) {
    if (!coffeeTime.equals(this.coffeeTime)) {
      this.coffeeTime = coffeeTime;
      eventsStore.coffeeTimeSet();
    }
  }

  Optional<Instant> getCoffeeTime() {
    return Optional.ofNullable(coffeeTime);
  }
}
