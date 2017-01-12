package coffee.time;

import coffee.events.Event;
import org.reactivestreams.Subscriber;

import javax.inject.Inject;
import javax.inject.Singleton;

import static coffee.events.Event.COFFEE_TIME_SET;

@Singleton
public class CoffeeTimeEventsStore {

  private final Subscriber<Event> subscriber;

  @Inject
  public CoffeeTimeEventsStore(Subscriber<Event> subscriber) {
    this.subscriber = subscriber;
  }

  void coffeeTimeSet() {
    subscriber.onNext(COFFEE_TIME_SET);
  }
}
