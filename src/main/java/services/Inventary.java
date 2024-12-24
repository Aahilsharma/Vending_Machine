package services;

import java.util.HashMap;
import java.util.Map;

public class Inventary<T> {

	private final Map<T, Integer> inventary = new HashMap<>();

	public int getQuantity(T item) {
		return inventary.getOrDefault(item, 0);
	}

	public void add(T item, int quantity) {
		inventary.put(item, getQuantity(item) + quantity);
	}

	public void deduct(T item) {
		if (getQuantity(item) > 0) {
			inventary.put(item, getQuantity(item) - 1);
		}
	}

	public boolean hasItem(T item) {
		return getQuantity(item) > 0;
	}

	public void clear() {
		inventary.clear();
	}
}
