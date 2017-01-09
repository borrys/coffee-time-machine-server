package coffee.arrivals;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ArrivalsController implements Handler {
  private final ArrivalsRepository arrivalsRepository;

  @Inject
  public ArrivalsController(ArrivalsRepository arrivalsRepository) {
    this.arrivalsRepository = arrivalsRepository;
  }

  @Override
  public void handle(Context ctx) throws Exception {
    ctx.byMethod(m -> m
        .get(() -> {
          ArrivalListResource list = new ArrivalListResource(arrivalsRepository.getAll());
          ctx.render(list);
        })
        .post(() -> {
          ctx.parse(ArrivalResource.class)
              .map(ArrivalResource::toArrival)
              .then(a -> {
                arrivalsRepository.add(a);
                ctx.render(new ArrivalResource(a));
              });
        }));
  }
}
