package coffee.arrivals;

import coffee.json.JsonResource;

import java.time.Instant;

class ArrivalResource implements JsonResource {
  private String name;
  private long time;

  ArrivalResource() {
  }

  ArrivalResource(Arrival arrival) {
    this.name = arrival.getName();
    this.time = arrival.getTime().toEpochMilli();
  }

  public String getName() {
    return name;
  }

  public long getTime() {
    return time;
  }

  Arrival toArrival() {
    return new Arrival(name, Instant.ofEpochMilli(time));
  }
}
