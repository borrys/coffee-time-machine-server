package coffee.arrivals;

import org.reactivestreams.Subscriber;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ArrivalsEventStore {

  private final Subscriber<Arrival> subscriber;

  @Inject
  ArrivalsEventStore(Subscriber<Arrival> subscriber) {
    this.subscriber = subscriber;
  }

  void arrivalDeclared(Arrival arrival) {
    subscriber.onNext(arrival);
  }
}
