package net.krg.ri.cancerregistry.iam.domain;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String username;
    private final String email;
    private final Set<Role> roles;

    public User(UUID id, String username, String email, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public Set<Role> getRoles() { return roles; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
