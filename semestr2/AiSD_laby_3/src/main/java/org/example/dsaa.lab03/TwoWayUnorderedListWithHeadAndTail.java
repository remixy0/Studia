package org.example.dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {
			this.object = e;
		}
		public Element(E e, Element next, Element prev) {
			this.object = e;
			this.next = next;
			this.prev = prev;
		}
		E object;
		Element next=null;
		Element prev=null;
	}
	
	Element head;
	Element tail;
	
	private class InnerIterator implements Iterator<E>{
		Element pos;

		public InnerIterator() {
		this.pos = head;
		}
		@Override
		public boolean hasNext() {
			return this.pos.next != tail;
		}
		
		@Override
		public E next() {
			this.pos = this.pos.next;
			return this.pos.object;
		}
	}
	
	private class InnerListIterator implements ListIterator<E>{
		Element p;

        public InnerListIterator() {
            this.p = head;
        }

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public boolean hasNext() {
			return p.next != tail;
		}

		@Override
		public boolean hasPrevious() {
			return p.prev != head;
		}

		@Override
		public E next() {
			this.p = this.p.next;
			return this.p.object;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			this.p = this.p.prev;
			return this.p.object;
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
		public void set(E e) {
			this.p.object = e;
			
		}
	}
	
	public TwoWayUnorderedListWithHeadAndTail() {
        head = new Element(null);
        tail = new Element(null);

        head.next = tail;
        tail.prev = head;

	}

    @Override
    public boolean add(E e) {
        Element newElement = new Element(e);
        Element lastElement = tail.prev;

        tail.prev = newElement;
        newElement.prev = lastElement;
        newElement.next = tail;
        lastElement.next = newElement;

        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        Element newElement = new Element(element);
        Element current = head.next;

        if (index < 0 || index > size()) {
            throw new NoSuchElementException();
        }

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        newElement.prev = current.prev;
        newElement.next = current;
        current.prev.next = newElement;
        current.prev = newElement;
    }

    @Override
    public void clear() {
        head.next= tail;
        tail.prev = head;
    }

    @Override
    public boolean contains(E element) {
        Element current = head;
        while (current.next != tail) {
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
        Element current = head.next;
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
        Element current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E old = current.object;
        current.object = element;
        return old;
    }

    @Override
    public int indexOf(E element) {
        Element current = head.next;
        int index = 0;
        while (current != tail){
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
        return head.next == tail;
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
    public E remove(int index) throws NoSuchElementException {
        if (index < 0 || index >= size()) {
            throw new NoSuchElementException();
        }
        Element current = head;

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
        Element current = head;
        while (current.next != tail) {
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
        Element current = head.next;
        int index = 0;
        while (current != tail) {
            current = current.next;
            index++;
        }
        return index;
    }

	public String toStringReverse() {
		ListIterator<E> iter=new InnerListIterator();
        E element = null;
		while(iter.hasNext())
			 element = iter.next();

        if(element==null) return "";

		String retStr="\n" + element.toString();
        while(iter.hasPrevious())
            retStr +=  "\n" + iter.previous() ;
		return retStr;
	}

	public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
		other.iterator().forEachRemaining(e ->{
            this.add(e);
        });
        other.clear();
	}
}

