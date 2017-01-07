package coffee.arrivals;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Date;

import static java.util.Collections.singletonList;

public class ArrivalsRetrieval implements Handler {

  private static final int MINUTE = 60_000;

  @Override
  public void handle(Context ctx) throws Exception {
    Arrival arrival = new Arrival("John", new Date().getTime() + 10 * MINUTE);
    ArrivalList list = new ArrivalList(singletonList(arrival));
    ctx.render(list);
  }
}
