package pacMan3;
/**
 * It's my own Linked List.
 * @author CoolerMaster
 *
 * @param <E>
 */
public class MyLinkedList<E> {
	private Node<E> head;
	private int numItems;

	public MyLinkedList() {
		numItems = 0;
		head = null;
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public int size() {
		return numItems;
	}

	private Node<E> find(int index) {
		Node<E> curr = head;
		for (int skip = 1; skip < index; skip++) {
			curr = curr.next;
		}
		return curr;
	}

	public void add(E newItem) {
		if (head == null) {
			head = new Node<E>(newItem);
		} else {
			Node<E> curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}

			curr.next = new Node<E>(newItem);
		}
		numItems++;
	}

	public void add(E newItem, int index) throws Exception {
		if (index > numItems)
			throw new Exception("Out of Bounds!");

		Node<E> curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}

		curr.next = new Node<E>(newItem);
		numItems++;
	}

	public void addLast(E lastItem) {
		Node<E> curr = head;
		while (curr.next != null)
			curr = curr.next;

		curr.next = new Node<E>(lastItem);
	}

	public E get() {
		return head.item;
	}

	public E get(int index) throws Exception {
		if (index > numItems)
			throw new Exception("Out of Bounds!");

		Node<E> curr = head;
		for (int i = 0; i < index; i++)
			curr = curr.next;

		return curr.item;
	}

	public E getFirst() {
		return head.item;
	}

	public void remove(int i) throws Exception {

		Node<E> curr = head;
		if (i >= numItems)
			throw new Exception("OUT OF BOUNDS!");

		int count = 1;
		if (i == 0) {
			head = head.next;
		} else if (i == numItems - 1) {
			while (curr.next.next != null)
				curr = curr.next;
			curr.next = null;
		} else {

			while (count < i) {
				curr = curr.next;
				count++;
			}
			curr.next = curr.next.next;
		}

		numItems--;
	}

	public void removeAll() {
		head = null;
		numItems = 0;
	}

	public String toString() {
		if (head == null)
			return "[]";
		else {
			Node<E> curr = head;
			String s = "[" + curr.item;
			curr = curr.next;
			while (curr.next != null) {
				s += ", " + curr.item;
				curr = curr.next;
			}

			s = s + ", " + curr.item + "]";
			return s;
		}
	}

	public void clear() {
		head = null;

	}

	public void removeLast() {
		if (size() == 0) {
		} else if (size() == 1)
			head = null;
		else {
			Node<E> curr = head;
			while (curr.next.next != null)
				curr = curr.next;
			curr.next = null;
		}

	}

	// public static void main(String[] args) throws Exception {
	// MyLinkedList<Integer> a = new MyLinkedList<Integer>();
	// for (int i = 0; i < 10; i++)
	// a.add(i);
	//
	// // a.remove(3);
	// System.out.println(a.get(10));
	//
	// }
}
