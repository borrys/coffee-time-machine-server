package coffee.arrivals;

import coffee.events.Event;
import org.reactivestreams.Subscriber;

import javax.inject.Inject;
import javax.inject.Singleton;

import static coffee.events.Event.ARRIVAL_DECLARED;

@Singleton
class ArrivalsEventStore {

  private final Subscriber<Event> subscriber;

  @Inject
  ArrivalsEventStore(Subscriber<Event> subscriber) {
    this.subscriber = subscriber;
  }

  void arrivalDeclared() {
    subscriber.onNext(ARRIVAL_DECLARED);
  }
}
