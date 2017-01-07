package coffee.arrivals;

import coffee.json.JsonResource;

public class Arrival implements JsonResource {
  private String name;
  private long time;

  Arrival(String name, long time) {
    this.name = name;
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public long getTime() {
    return time;
  }
}
