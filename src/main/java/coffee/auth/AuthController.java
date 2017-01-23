package coffee.auth;

import org.pac4j.core.profile.UserProfile;
import org.pac4j.oauth.client.GitHubClient;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.pac4j.RatpackPac4j;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class AuthController implements Action<Chain> {
  private final String clientId;
  private final String clientSecret;

  @Inject
  public AuthController(@Named("github.clientId") String clientId,
                        @Named("github.clientSecret") String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  @Override
  public void execute(Chain chain) throws Exception {
    GitHubClient gitHubClient = new GitHubClient(clientId, clientSecret);
    chain
        .all(RatpackPac4j.authenticator(gitHubClient))
        .all(RatpackPac4j.requireAuth(GitHubClient.class))
        .path("", ctx -> ctx.render(ctx.get(UserProfile.class).toString()));
  }
}
