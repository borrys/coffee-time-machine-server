package coffee.auth;

import javax.inject.Singleton;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class UsersRepository {
  private Map<UUID, User> users = new ConcurrentHashMap<>();

  public synchronized User addUser(String githubId, String displayName, String avatar) {
    return users.values().stream()
        .filter(u -> githubId.equals(u.getGithubId()))
        .findAny()
        .orElseGet(() -> {
          User user = new User();
          user.setDisplayName(displayName);
          user.setGithubId(githubId);
          user.setAvatar(avatar);
          return user;
        });
  }
}
