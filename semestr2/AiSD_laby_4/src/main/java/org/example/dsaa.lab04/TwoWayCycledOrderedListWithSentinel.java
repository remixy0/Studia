package org.example.dsaa.lab04;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E extends Comparable<E>> implements IList<E>{

	private class Element{
		public Element(E e) {
			this.object = e;
		}
		public Element(E e, Element next, Element prev) {
			this.object = e;
            this.next = next;
            this.prev = prev;
		}
		// add element e after this
		public void addAfter(Element elem) {
			this.next = elem;
		}
		// assert it is NOT a sentinel
		public void remove() {
			this.prev.next = this.next;
			this.next.prev = this.prev;
            this.object = null;
		}
		E object;
		Element next=null;
		Element prev=null;
	}


	Element sentinel;
	int size;

	private class InnerIterator implements Iterator<E>{
		Element current;
		public InnerIterator() {
			this.current = sentinel;
		}
		@Override
		public boolean hasNext() {
            return this.current.next != sentinel;
		}

		@Override
		public E next() {
			this.current = this.current.next;
			return this.current.object;
		}
	}

	private class InnerListIterator implements ListIterator<E>{
		Element current;
		public InnerListIterator() {
			this.current = sentinel;
		}
		@Override
		public boolean hasNext() {
			return this.current.next != sentinel;
		}

		@Override
		public E next() {
			this.current = this.current.next;
			return this.current.object;
		}
		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean hasPrevious() {
			return this.current.prev != sentinel;
		}
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public E previous() {
            this.current = this.current.prev;
			return this.current.object;
		}
		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}
	public TwoWayCycledOrderedListWithSentinel() {
        this.sentinel = new Element(null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
        Element current=this.sentinel.next;
        while(current != sentinel && current.object.compareTo(e)<=0) {
            current=current.next;
        }

        Element newNode = new Element(e, current, current.prev);
        current.prev.next = newNode;
        current.prev = newNode;

        return true;
	}

	private Element getElement(int index) {
		Element current=this.sentinel.next;
        for(int i=0;i<index;i++){
            current=current.next;
        }
		return current;
	}

	private Element getElement(E obj) {
        Element current=this.sentinel.next;
		while(current.next != sentinel) {
            if(current.object.equals(obj)) {
                return current;
            }
            current=current.next;
        }
		return null;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
    }

	@Override
	public void clear() {
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
	}

	@Override
	public boolean contains(E element) {
        Element current=this.sentinel.next;
        while(current.next != sentinel) {
            if(current.object.equals(element)) {
                return true;
            }
            current=current.next;
        }
        return false;
	}

	@Override
	public E get(int index) {
        Element current=this.sentinel.next;
        for(int i=0;i<index;i++){
            current=current.next;
        }
        return current.object;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
        Element current=this.sentinel.next;
        int index=0;
        while(current.next != sentinel) {
            if(current.object.equals(element)) {
                return index;
            }
            index ++;
            current=current.next;
        }
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return this.sentinel.next == sentinel;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator();
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
        current.next.prev = current;

        return old;
    }

    @Override
    public boolean remove(E e) {
        Element current = sentinel;
        while (current.next != sentinel) {
            if (e.equals(current.next.object)) {
                current.next = current.next.next;
                current.next.prev = current;
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
        while (current != sentinel) {
            current = current.next;
            index++;
        }
        return index;
    }

	//@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        other.iterator().forEachRemaining(e ->{
            this.add(e);
        });
        other.clear();
	}
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e) {
        Element current = sentinel;
        while (current.next != sentinel) {
            if (e.equals(current.next.object)) {
                current.next = current.next.next;
                current.next.prev = current;
            }else{
                current = current.next;
            }

        }
    }
}

