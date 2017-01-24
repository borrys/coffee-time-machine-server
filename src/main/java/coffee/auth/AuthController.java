package coffee.auth;

import org.pac4j.core.profile.UserProfile;
import org.pac4j.oauth.client.GitHubClient;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.pac4j.RatpackPac4j;
import ratpack.session.Session;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static ratpack.pac4j.RatpackPac4j.requireAuth;

@Singleton
public class AuthController implements Action<Chain> {
  private final String clientId;
  private final String clientSecret;
  private final String uiUrl;
  private final UsersRepository usersRepository;

  @Inject
  public AuthController(@Named("github.clientId") String clientId,
                        @Named("github.clientSecret") String clientSecret,
                        @Named("ui.url") String uiUrl,
                        UsersRepository usersRepository) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.uiUrl = uiUrl;
    this.usersRepository = usersRepository;
  }

  @Override
  public void execute(Chain chain) throws Exception {
    GitHubClient gitHubClient = new GitHubClient(clientId, clientSecret);
    chain
        .all(RatpackPac4j.authenticator(gitHubClient))
        .all(requireAuth(GitHubClient.class))
        .all(ctx -> {
          UserProfile userProfile = ctx.get(UserProfile.class);
          User user = usersRepository.addUser(
              userProfile.getId(),
              userProfile.getAttribute("login").toString(),
              userProfile.getAttribute("avatar_url").toString()
          );
          Session session = ctx.get(Session.class);
          session.getData().then(s -> {
            s.set(User.class, user);
            ctx.redirect(302, uiUrl);
          });
        });
  }
}
