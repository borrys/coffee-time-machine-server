package coffee.arrivals;

import coffee.json.JsonResource;

import java.time.Instant;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Arrival arrival = (Arrival) o;
    return Objects.equals(name, arrival.name) &&
        Objects.equals(time, arrival.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, time);
  }
}
