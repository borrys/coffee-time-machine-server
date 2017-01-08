package coffee.arrivals;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ArrivalsController implements Handler {
  private ArrivalsStore arrivalsStore;

  @Inject
  public ArrivalsController(ArrivalsStore arrivalsStore) {
    this.arrivalsStore = arrivalsStore;
  }

  @Override
  public void handle(Context ctx) throws Exception {
    ctx.byMethod(m -> m
        .get(() -> {
          ArrivalListResource list = new ArrivalListResource(arrivalsStore.getAll());
          ctx.render(list);
        })
        .post(() -> {
          ctx.parse(ArrivalResource.class)
              .map(ArrivalResource::toArrival)
              .then(a -> {
                arrivalsStore.add(a);
                ctx.render(new ArrivalResource(a));
              });
        }));
  }
}
