package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

import java.util.Arrays;

public class Worker {
    private final boolean isActive;
    private final int salary;
    private final Contact contact;
    private final String[] skills;

    public Worker(boolean isActive, int salary, Contact contact, String[] skills) {
        this.isActive = isActive;
        this.salary = salary;
        this.contact = contact;
        this.skills = skills;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getSalary() {
        return salary;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getSkills() {
        return skills;
    }

    @Override public String toString() {
        return "Worker{" 
                + "isActive=" + isActive 
                + ", salary=" + salary 
                + ", contact=" + contact 
                + ", skills=" + Arrays.toString(skills) 
                + '}';
    }
}