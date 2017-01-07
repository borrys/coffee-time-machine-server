package coffee;

import coffee.arrivals.ArrivalsRetrieval;
import coffee.json.JsonRenderer;
import ratpack.server.RatpackServer;

import static ratpack.registry.Registry.single;

public class Main {
  public static void main(String... args) throws Exception {
    RatpackServer.start(server -> server
        .registry(single(new JsonRenderer()))
        .handlers(chain -> chain
            .get(ctx -> ctx.render("Hello, World!!!"))
            .get("arrivals", new ArrivalsRetrieval())
        )
    );
  }
}
