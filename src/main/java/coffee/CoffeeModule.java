package coffee;

import coffee.arrivals.Arrival;
import coffee.arrivals.ArrivalEventsController;
import coffee.arrivals.ArrivalsController;
import coffee.arrivals.ArrivalsRepository;
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
    FlowableProcessor<Arrival> arrivalProcessor = PublishProcessor.create();

    bind(ArrivalsRepository.class);
    bind(ArrivalsController.class);
    bind(ArrivalEventsController.class);
    bind(new TypeLiteral<Publisher<Arrival>>(){}).toInstance(arrivalProcessor);
    bind(new TypeLiteral<Subscriber<Arrival>>(){}).toInstance(arrivalProcessor);
    bind(JsonRenderer.class);
  }
}
