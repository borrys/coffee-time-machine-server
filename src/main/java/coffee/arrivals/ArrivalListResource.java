package coffee.arrivals;

import coffee.json.JsonResource;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ArrivalListResource implements JsonResource {
  private List<ArrivalResource> arrivals;

  ArrivalListResource(List<Arrival> arrivals) {
    this.arrivals = arrivals.stream()
        .map(ArrivalResource::new)
        .collect(toList());
  }

  public List<ArrivalResource> getArrivals() {
    return arrivals;
  }

}
