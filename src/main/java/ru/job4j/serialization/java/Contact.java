package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    @Serial private static final long serialVersionUID = -158623476271041227L;
    private final int zipCode;
    private final String phone;
    
    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" 
                + "zipCode=" + zipCode 
                + ", phone='" + phone + '\'' 
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        
        File tempFile = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            oos.writeObject(contact);
        }
        
       try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile))) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
