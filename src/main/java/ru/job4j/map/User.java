package ru.job4j.map;

import java.util.*;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Alex", 21, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User user2 = new User("Alex", 21, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.printf("user1 - hashCode: %s, hash: %s, bucket %s %n", hashCode1, hash1, bucket1);
        System.out.printf("user2 - hashCode: %s, hash: %s, bucket %s %n", hashCode2, hash2, bucket2);
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "One");
        simpleMap.put(2, "Two");
        simpleMap.put(3, "Three");
        simpleMap.put(4, "FOR");
        System.out.println(simpleMap.put(8, "EIGHT"));
        System.out.println(simpleMap.size());
        simpleMap.put(null, "NotNull");
        System.out.println(simpleMap.get(6));
        System.out.println(simpleMap.get(1));
        System.out.println(simpleMap.get(2));
        System.out.println(simpleMap.get(3));
        System.out.println(simpleMap.get(4));
        System.out.println(simpleMap.size());
        System.out.println(simpleMap.get(null));
        Object o = null;
        int hash = o.hashCode();
        System.out.println(hash);
    }
}
