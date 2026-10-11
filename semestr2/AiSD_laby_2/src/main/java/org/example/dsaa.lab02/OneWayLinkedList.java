package org.example.dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E> {

    private class Element {
        public Element(E e) {
            this.object = e;
        }

        E object;
        Element next = null;
    }

    Element sentinel;

    private class InnerIterator implements Iterator<E> {
        private Element element;

        public InnerIterator() {
            this.element = sentinel;
        }

        @Override
        public boolean hasNext() {
            return this.element.next != null;
        }

        @Override
        public E next() {
            this.element = this.element.next;
            return this.element.object;
        }
    }

    public OneWayLinkedList() {
        this.sentinel = new Element(null);
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Element newElement = new Element(e);
        Element current = sentinel;

        while (current.next != null) {
            current = current.next;
        }
        current.next = newElement;

        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        Element newElement = new Element(element);
        Element current = sentinel;
        if (index < 0 || index > size()) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        newElement.next = current.next;
        current.next = newElement;
    }


    @Override
    public void clear() {
        sentinel.next = null;
    }

    @Override
    public boolean contains(E element) {
        Element current = sentinel;
        while (current.next != null) {
            current = current.next;
            if (current.object.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        if (index < 0 || index >= size()) {
            throw new NoSuchElementException();
        }
        Element current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.object;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        if (index < 0 || index >= size()) {
            throw new NoSuchElementException();
        }
        Element current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E old = current.object;
        current.object = element;
        return old;
    }

    @Override
    public int indexOf(E element) {
        Element current = sentinel.next;
        int index = 0;
        while (current != null) {
            if (element.equals(current.object)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        if (index < 0 || index >= size()) {
            throw new NoSuchElementException();
        }
        Element current = sentinel;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E old = current.next.object;
        current.next = current.next.next;
        return old;
    }

    @Override
    public boolean remove(E e) {
        Element current = sentinel;
        while (current.next != null) {
            if (e.equals(current.next.object)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        Element current = sentinel.next;
        int index = 0;
        while (current != null) {
            current = current.next;
            index++;
        }
        return index;
    }

}

