package coffee;

import coffee.auth.AuthController;
import coffee.events.EventsController;
import coffee.arrivals.ArrivalsController;
import coffee.time.CoffeeTimeController;
import ratpack.guice.Guice;
import ratpack.http.MutableHeaders;
import ratpack.server.RatpackServer;

public class Main {
  public static void main(String... args) throws Exception {
    RatpackServer.start(server -> server
        .registry(Guice.registry(b -> b.module(CoffeeModule.class)))
        .handlers(chain -> chain
            .all(ctx -> {
              MutableHeaders headers = ctx.getResponse().getHeaders();
              headers.set("Access-Control-Allow-Origin", "*");
              headers.set("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
              ctx.next();
            })
            .path("arrivals", ArrivalsController.class)
            .path("coffeeTime", CoffeeTimeController.class)
            .prefix("auth", AuthController.class)
            .get("events", EventsController.class)
        )
    );
  }
}
