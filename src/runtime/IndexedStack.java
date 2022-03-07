package runtime;

import java.util.HashMap;

/**
 * In-mem storage of objects (used in {@code Table})
 */
public class IndexedStack {
	protected long objects_new_id = Long.MIN_VALUE;
	protected HashMap<Long,Object> objects = new HashMap<Long,Object>();

	/**
	 * Puts new object in a stack
	 * @param obj is an Object to store
	 * @return index of stored object
	 */
	public long put(Object obj) {
		objects.put(objects_new_id, obj);
		return objects_new_id++;
	}

	/**
	 * Gets an Object by stored index
	 * @param index of stored object
	 * @return stored Object
	 */
	public Object get(long index) {
		return objects.get(index);
	}

	/**
	 * Removes an Object from a stack
	 * @param index of stored object
	 * @return deleted object
	 */
	public Object remove(long index) {
		return objects.remove(index);
	}

	/**
	 * Clears stack with all stored objects
	 */
	public void clear() {
		objects_new_id = Long.MIN_VALUE;
		objects.clear();
	}
}
