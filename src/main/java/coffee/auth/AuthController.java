package coffee.auth;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.util.MultiValueMap;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class AuthController implements Action<Chain> {
  private static final String GITHUB_AUTHORIZE_URL = "https://github.com/login/oauth/authorize";
  private final String clientId;
  private final String clientSecret;
  private final String uiUrl;

  @Inject
  public AuthController(@Named("github.clientId") String clientId,
                        @Named("github.clientSecret") String clientSecret,
                        @Named("ui.url") String uiUrl) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.uiUrl = uiUrl;
  }

  @Override
  public void execute(Chain chain) throws Exception {
    chain
        .get("", ctx -> {
          String params = Stream.of(
              "client_id=" + clientId,
              "redirect_uri=http://localhost:5050/auth/accepted"
          ).collect(Collectors.joining("&"));
          ctx.redirect(GITHUB_AUTHORIZE_URL + "?" + params);
        })
        .get("accepted", ctx -> {
          MultiValueMap<String, String> params = ctx.getRequest().getQueryParams();
          System.out.println("user authorized [code: '" + params.get("code") + "']");
          ctx.redirect(uiUrl);
        });
  }
}
