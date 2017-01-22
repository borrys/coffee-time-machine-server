package coffee;

import coffee.arrivals.ArrivalsController;
import coffee.auth.AuthController;
import coffee.events.Event;
import coffee.events.EventsController;
import coffee.json.JsonRenderer;
import coffee.time.CoffeeTimeController;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CoffeeModule extends AbstractModule {

  @Override
  protected void configure() {
    FlowableProcessor<Event> arrivalProcessor = PublishProcessor.create();
    Names.bindProperties(binder(), getProperties());

    bind(ArrivalsController.class);
    bind(CoffeeTimeController.class);
    bind(EventsController.class);
    bind(AuthController.class);
    bind(new TypeLiteral<Publisher<Event>>(){}).toInstance(arrivalProcessor);
    bind(new TypeLiteral<Subscriber<Event>>(){}).toInstance(arrivalProcessor);
    bind(JsonRenderer.class);
  }

  private Properties getProperties() {
    Properties properties = new Properties();
    try (FileInputStream stream = new FileInputStream("config/coffee.properties")) {
      properties.load(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties;
  }
}
