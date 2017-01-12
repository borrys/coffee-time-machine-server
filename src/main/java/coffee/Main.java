package coffee;

import coffee.events.EventsController;
import coffee.arrivals.ArrivalsController;
import coffee.time.CoffeeTimeController;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;

public class Main {
  public static void main(String... args) throws Exception {
    RatpackServer.start(server -> server
        .registry(Guice.registry(b -> b.module(CoffeeModule.class)))
        .handlers(chain -> chain
            .path("arrivals", ArrivalsController.class)
            .path("coffeeTime", CoffeeTimeController.class)
            .get("events", EventsController.class)
        )
    );
  }
}
