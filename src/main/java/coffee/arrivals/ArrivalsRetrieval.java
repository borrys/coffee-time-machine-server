package coffee.arrivals;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ArrivalsRetrieval implements Handler {
  private ArrivalsStore arrivalsStore;

  @Inject
  public ArrivalsRetrieval(ArrivalsStore arrivalsStore) {
    this.arrivalsStore = arrivalsStore;
  }

  @Override
  public void handle(Context ctx) throws Exception {
    ArrivalListResource list = new ArrivalListResource(arrivalsStore.getAll());
    ctx.render(list);
  }
}
