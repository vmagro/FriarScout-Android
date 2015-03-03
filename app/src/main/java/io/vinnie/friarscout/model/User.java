package io.vinnie.friarscout.model;

/**
 * Created by vmagro on 3/3/15.
 */
public class User {
    private String email;
    private String name;
    private boolean isAdmin;

    public User() {

    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
