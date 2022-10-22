package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.nio.file.Files;

@XmlRootElement(name = "contact")
public class Contact {
    @XmlAttribute
    private int zipCode;
    @XmlAttribute
    private String phone;
    
    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" 
                + "zipCode=" + zipCode 
                + ", phone='" + phone + '\'' 
                + '}';
    }
}