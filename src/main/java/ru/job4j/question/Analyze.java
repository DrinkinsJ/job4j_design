package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info();
        Map<Integer, String> diffMap = new HashMap<>();
        for (User prev : previous) {
            diffMap.put(prev.getId(), prev.getName());
        }
        for (User curr : current) {
            if (!diffMap.containsKey(curr.getId())) {
                info.setAdded(info.getAdded() + 1);
            } else if (!curr.getName()
                    .equals(diffMap.get(curr.getId()))) {
                info.setChanged(info.getChanged() + 1);
            }
            info.setDeleted(previous.size() - current.size() + info.getAdded());
        }
        return info;
    }
}
