package coffee;

import coffee.arrivals.ArrivalEventsController;
import coffee.arrivals.ArrivalsController;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;

public class Main {
  public static void main(String... args) throws Exception {
    RatpackServer.start(server -> server
        .registry(Guice.registry(b -> b.module(CoffeeModule.class)))
        .handlers(chain -> chain
            .get(ctx -> ctx.render("Hello, World!!!"))
            .prefix("arrivals", c -> c
                .path(ArrivalsController.class)
                .get("events", ArrivalEventsController.class)
            )
        )
    );
  }
}
