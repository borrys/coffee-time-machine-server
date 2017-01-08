package coffee;

import coffee.arrivals.ArrivalsController;
import coffee.arrivals.ArrivalsStore;
import coffee.json.JsonRenderer;
import com.google.inject.AbstractModule;

public class CoffeeModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ArrivalsStore.class);
    bind(ArrivalsController.class);
    bind(JsonRenderer.class);
  }
}
