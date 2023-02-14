package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean isPresent;
        if (findItem(childName).isPresent()) {
            return false;
        }
        if (parentName.equals(Menu.ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            isPresent = true;
        } else {
            Optional<ItemInfo> parentOptional = findItem(parentName);
            isPresent = parentOptional.isPresent();
            parentOptional.ifPresent(i -> i.menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate)));
        }
        return isPresent;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(i -> new MenuItemInfo(i.menuItem, i.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new InfoIterator();
    }

    private class InfoIterator implements Iterator<MenuItemInfo> {

        private final Iterator<ItemInfo> iterator = new DFSIterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public MenuItemInfo next() {
            ItemInfo itemInfo = iterator.next();
            return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
        }
    }

    private Optional<ItemInfo> findItem(String name) {
        for (DFSIterator iterator = new DFSIterator(); iterator.hasNext();) {
            ItemInfo itemInfo = iterator.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                return Optional.of(itemInfo);
            }
        }
        return Optional.empty();
    }

    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private static class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}