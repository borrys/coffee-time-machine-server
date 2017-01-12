package coffee.time;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;

@Singleton
public class CoffeeTimeController implements Handler {

  private final CoffeeTimeRepository repository;

  @Inject
  public CoffeeTimeController(CoffeeTimeRepository repository) {
    this.repository = repository;
  }

  @Override
  public void handle(Context ctx) throws Exception {
    ctx.byMethod(m -> m
        .get(() -> {
          Long timestamp = repository.getCoffeeTime().map(Instant::toEpochMilli).orElse(null);
          ctx.render(new CoffeeTimeResource(timestamp));
        })
        .post(() -> ctx.parse(CoffeeTimeResource.class)
            .then(time -> {
              repository.setCoffeeTime(time.toInstant());
              ctx.render(time);
            })
        ));
  }
}
