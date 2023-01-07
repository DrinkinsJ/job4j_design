package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> node;
    private int size;
    private int modCount;

    public void add(E value) {
        size++;
        modCount++;

        Node<E> newNode = new Node<>(value, null);
        if (node == null) {
            node = newNode;
            return;
        }

        Node<E> tail = node;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = node;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int expectedMod = modCount;
            private Node<E> tail = node;

            @Override
            public boolean hasNext() {
                if (modCount != expectedMod) {
                    throw new ConcurrentModificationException();
                }
                return tail != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = tail.value;
                tail = tail.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private final E value;
        private Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
