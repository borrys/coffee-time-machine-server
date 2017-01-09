package coffee.arrivals;

import coffee.json.JsonResource;

import java.time.Instant;

public class Arrival implements JsonResource {
  private String name;
  private Instant time;

  Arrival(String name, Instant time) {
    this.name = name;
    this.time = time;
  }

  String getName() {
    return name;
  }

  Instant getTime() {
    return time;
  }
}
