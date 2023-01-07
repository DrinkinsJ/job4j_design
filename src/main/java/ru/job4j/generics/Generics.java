package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("Animal"));
        second.add(new Predator("Animal", false));
        third.add(new Tiger("Tiger", true, 30));

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        /*        gen.printBoundedWildCard(first); */
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        /*        gen.printLowerBoundedWildCard(third); */
    }

    public void printObject(List<?> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}

class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }
}

class Predator extends Animal {
    boolean pred;

    public Predator(String name, boolean pred) {
        super(name);
        this.pred = pred;
    }
}

class Tiger extends Predator {
    int toothCount;

    public Tiger(String name, boolean predator, int toothCount) {
        super(name, predator);
        this.toothCount = toothCount;
    }
}
