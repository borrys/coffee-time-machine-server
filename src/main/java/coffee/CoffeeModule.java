package coffee;

import coffee.arrivals.ArrivalsController;
import coffee.arrivals.ArrivalsRepository;
import coffee.events.Event;
import coffee.events.EventsController;
import coffee.json.JsonRenderer;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class CoffeeModule extends AbstractModule {

  @Override
  protected void configure() {
    FlowableProcessor<Event> arrivalProcessor = PublishProcessor.create();

    bind(ArrivalsRepository.class);
    bind(ArrivalsController.class);
    bind(EventsController.class);
    bind(new TypeLiteral<Publisher<Event>>(){}).toInstance(arrivalProcessor);
    bind(new TypeLiteral<Subscriber<Event>>(){}).toInstance(arrivalProcessor);
    bind(JsonRenderer.class);
  }
}
