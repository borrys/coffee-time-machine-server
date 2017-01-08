package coffee.arrivals;

import coffee.json.JsonResource;

import java.time.ZoneId;
import java.time.ZoneOffset;
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

  static class ArrivalResource {
    private String name;
    private long time;

    private ArrivalResource(Arrival arrival) {
      this.name = arrival.getName();
      this.time = arrival.getTime().toInstant().toEpochMilli();
    }

    public String getName() {
      return name;
    }

    public long getTime() {
      return time;
    }
  }
}
