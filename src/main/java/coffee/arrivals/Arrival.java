package coffee.arrivals;

import coffee.json.JsonResource;

import java.time.ZonedDateTime;

class Arrival implements JsonResource {
  private String name;
  private ZonedDateTime time;

  Arrival(String name, ZonedDateTime time) {
    this.name = name;
    this.time = time;
  }

  String getName() {
    return name;
  }

  ZonedDateTime getTime() {
    return time;
  }
}
