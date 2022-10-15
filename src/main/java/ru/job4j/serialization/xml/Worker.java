package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "worker")
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker {
    
    @XmlAttribute
    private boolean isActive;
    
    @XmlAttribute
    private int salary;
    
    private Contact contact;
    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private String[] skills;

    public Worker(boolean isActive, int salary, Contact contact, String[] skills) {
        this.isActive = isActive;
        this.salary = salary;
        this.contact = contact;
        this.skills = skills;
    }
    
    public Worker() {
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