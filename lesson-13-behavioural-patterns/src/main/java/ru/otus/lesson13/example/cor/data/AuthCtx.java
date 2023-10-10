package ru.otus.lesson13.example.cor.data;

public class AuthCtx {
    private String userName;
    private String password;
    private User user;
    private Realm realm;
    private Resource resource;
    private boolean granted = false;

    public AuthCtx(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(final Realm realm) {
        this.realm = realm;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(final Resource resource) {
        this.resource = resource;
    }

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(final boolean granted) {
        this.granted = granted;
    }
}
