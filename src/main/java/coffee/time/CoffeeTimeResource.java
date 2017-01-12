package coffee.time;

import coffee.json.JsonResource;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class CoffeeTimeResource implements JsonResource {

  private Long time;

  public CoffeeTimeResource() {
  }

  CoffeeTimeResource(Long time) {
    this.time = time;
  }

  public Long getTime() {
    return time;
  }

  Instant toInstant() {
    return Instant.ofEpochMilli(time);
  }
}
