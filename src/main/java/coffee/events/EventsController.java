package coffee.events;

import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.websocket.WebSockets;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EventsController implements Handler {
  private final Publisher<String> publisher;

  @Inject
  public EventsController(Publisher<Event> eventPublisher) {
    this.publisher = Flowable.fromPublisher(eventPublisher).map(Event::name);
  }

  @Override
  public void handle(Context ctx) throws Exception {
    WebSockets.websocketBroadcast(ctx, publisher);
  }
}
