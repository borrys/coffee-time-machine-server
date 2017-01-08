package coffee;

import coffee.arrivals.ArrivalsRetrieval;
import coffee.arrivals.ArrivalsStore;
import coffee.json.JsonRenderer;
import com.google.inject.AbstractModule;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class CoffeeModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ArrivalsStore.class).toInstance(initializedStore());
    bind(ArrivalsRetrieval.class);
    bind(JsonRenderer.class);
  }

  private ArrivalsStore initializedStore() {
    ArrivalsStore arrivalsStore = new ArrivalsStore();
    arrivalsStore.add("John", ZonedDateTime.now().plus(15, ChronoUnit.MINUTES));
    return arrivalsStore;
  }
}
