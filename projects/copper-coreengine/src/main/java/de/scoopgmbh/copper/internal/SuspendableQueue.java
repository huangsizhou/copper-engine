package de.scoopgmbh.copper.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuspendableQueue<T> implements Queue<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(SuspendableQueue.class);
	
	private Queue<T> queue;
	private boolean suspended = false;

	public SuspendableQueue(Queue<T> queue) {
		if (queue == null) throw new NullPointerException();
		this.queue = queue;
	}
	
	public void setSuspended(boolean suspended) {
		logger.info("Setting suspended to {}", suspended);
		this.suspended = suspended;
	}
	
	public boolean isSuspended() {
		return suspended;
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return queue.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return queue.iterator();
	}

	@Override
	public Object[] toArray() {
		return queue.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return queue.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		return queue.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return queue.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return queue.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return queue.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return queue.retainAll(c);
	}

	@Override
	public void clear() {
		queue.clear();
	}

	@Override
	public boolean add(T e) {
		return queue.add(e);
	}

	@Override
	public boolean offer(T e) {
		return queue.offer(e);
	}

	@Override
	public T remove() {
		if (suspended) throw new NoSuchElementException();

		return queue.remove();
	}

	@Override
	public T poll() {
		if (suspended) return null;
		
		return queue.poll();
	}

	@Override
	public T element() {
		if (suspended) throw new NoSuchElementException();

		return queue.element();
	}

	@Override
	public T peek() {
		if (suspended) return null;
		
		return queue.peek();
	}

}