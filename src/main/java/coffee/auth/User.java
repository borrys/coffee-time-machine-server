package coffee.auth;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
  private final UUID id;
  private String githubId;
  private String displayName;
  private String avatar;

  public User(UUID id) {
    this.id = id;
  }

  public User() {
    this(UUID.randomUUID());
  }

  public UUID getId() {
    return id;
  }

  public String getGithubId() {
    return githubId;
  }

  public void setGithubId(String githubId) {
    this.githubId = githubId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
