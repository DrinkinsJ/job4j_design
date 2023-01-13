package ru.job4j.ood.srp.examples;

/**
 * Класс правильно реализует методы интерфейсов согласно SRP. Проблема в том, что в классе имеется конструктор
 * В котором можно заранее задать объект, нам необходимо получить объект согласно методу scan()
 */
public class DocScanAndSender implements DocScan, DocSend {
    Object o;

    public DocScanAndSender(Object o) {
        this.o = o;
    }

    @Override
    public Object scan() {
        return o;
    }

    @Override
    public void send() {

    }
}
