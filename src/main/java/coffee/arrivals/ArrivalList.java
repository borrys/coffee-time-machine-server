package coffee.arrivals;

import coffee.json.JsonResource;

import java.util.List;

public class ArrivalList implements JsonResource {
  private List<Arrival> arrivals;

  ArrivalList(List<Arrival> arrivals) {
    this.arrivals = arrivals;
  }

  public List<Arrival> getArrivals() {
    return arrivals;
  }
}
