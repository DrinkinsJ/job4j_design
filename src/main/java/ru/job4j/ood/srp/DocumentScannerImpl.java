package ru.job4j.ood.srp;


/**
 * Нарушение SRP. Класс реализует интерфейс с двумя методами, которые делают
 * разные задачи, один сохраняет, второй отправляет.
 * Анти паттерн GOD OBJECT.
 * Необходимо разбить интерфейс на два отдельных интерфейса scan и send
 * <p>
 * (С) «не следует делать классы, которые отвечают одновременно за расчет
 * платежного баланса и за построение кратчайшего маршрута доставки»
 */
public class DocumentScannerImpl implements DocumentScanner {

    @Override
    public Object scan() {
        return null;
    }

    @Override
    public void send() {
    }
}
