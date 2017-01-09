package coffee.arrivals;

import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.websocket.WebSockets;

import javax.inject.Inject;
import javax.inject.Singleton;

import static ratpack.jackson.Jackson.toJson;

@Singleton
public class ArrivalEventsController implements Handler {
  private final Publisher<Arrival> publisher;

  @Inject
  public ArrivalEventsController(Publisher<Arrival> arrivalPublisher) {
    this.publisher = arrivalPublisher;
  }

  @Override
  public void handle(Context ctx) throws Exception {
    Publisher<String> jsonPublisher = Flowable.fromPublisher(publisher).map(a -> toJson(ctx).apply(a));
    WebSockets.websocketBroadcast(ctx, jsonPublisher);
  }
}
