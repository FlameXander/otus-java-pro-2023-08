package ru.otus.lesson13.example.cor.data;

import java.util.HashMap;
import java.util.Map;


public class UserStorage {

    private static final Map<String, User> users = new HashMap<>();

    static  {
        Realm green = new Realm("green");
        Realm red = new Realm("red");

        green.addResource(new Resource("resource"));

        users.put("green", new User("green", "greenpwd", green));
        users.put("red", new User("red", "redpwd", red));

    }

    public static User getUser(String name) {
        return users.get(name);
    }
}
