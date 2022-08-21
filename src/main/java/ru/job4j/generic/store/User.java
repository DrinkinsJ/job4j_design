package ru.job4j.generic.store;

public class User extends Base {

    private final String username;

    public User(String id, String name) {
        super(id);
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    @Override public String toString() {
        return "User{" 
                + "id='" + getId() + '\'' 
                + "username='" + username + '\'' 
                + '}';
    }
}
