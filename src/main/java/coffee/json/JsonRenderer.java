package coffee.json;

import ratpack.handling.Context;
import ratpack.render.RendererSupport;

import static ratpack.jackson.Jackson.json;

public class JsonRenderer extends RendererSupport<JsonResource> {

  @Override
  public void render(Context ctx, JsonResource jsonResource) throws Exception {
    ctx.header("content-type", "application/json");
    ctx.render(json(jsonResource));
  }
}
