import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class BTree {

	int degree;
	int size;
	BHolder root;

	public BTree(int degree) throws Exception {
		if (degree < 3) {
			System.err.println("Degree (" + degree + ") is too small!");
			throw new Exception();
		}
		this.degree = degree;
		root = null;
	}

	public boolean add(int item) {
		if (root == null) {
			root = new BHolder(degree);
		}
		size++;
		BHolder current = root;

		while (current.hasChildren()) {
			if (current.getLast() <= item) {
				current = current.getChildren().get(current.getChildren().size() - 1);
				continue;
			}

			if (item < current.getFirst()) {
				current = current.getChildren().get(0);
				continue;
			}
			for (int i = current.getChildren().size() - 1; i >= 1; i--) {
				if (current.list.get(i - 1) <= item && item < current.list.get(i)) {
					current = current.getChildren().get(i);
					break;
				}
			}

		}

		current.add(item);
		if (current.size() == degree) {
			rebalanceInsertion(current);
		}

		return true;
	}

	private void rebalanceInsertion(BHolder current) {
		while (current.size() == degree) {
			BHolder left = new BHolder();
			BHolder right = new BHolder();

			int halfPoint = current.size() / 2;
			assertNotEquals(halfPoint, 0);
			for (int i = 0; i < halfPoint; i++) {
				right.add(current.popRight());
			}

			while (current.size() != 1) {
				left.add(current.popLeft());
			}

			if (current.hasChildren()) {

				while(right.size() + 1 != right.getChildren().size()) {
					right.addChild(current.popRightChild());
				}
				
				while(left.size() + 1 != left.getChildren().size()) {
					left.addChild(current.popRightChild());
				}
				
				assertFalse(current.hasChildren());
			}

			BHolder parent;
			if (current == root) {
				parent = new BHolder();
				root = parent;
			}

			else {
				parent = current.getParent();
			}
			parent.removeChild(current);

			parent.addChild(left);
			parent.addChild(right);
			parent.add(current.getFirst());
			current = parent;
		}
	}

	public boolean contains(int item) {
		if (root == null) {
			return false;
		}
		BHolder current = root;

		while (true) {
			if (!current.hasChildren()) {
				return current.list.contains(item);
			}

			if (current.getLast() == item) {
				return true;
			}
			if (current.getFirst() == item) {
				return true;
			}
			if (item < current.getFirst()) {
				current = current.getChildren().get(0);
				continue;
			}

			if (current.getLast() < item) {
				current = current.getChildren().get(current.getChildren().size() - 1);
				continue;
			}

			for (int i = current.size() - 1; i >= 1; i--) {
				if (current.list.get(i) == item) {
					return true;
				} else if (current.list.get(i - 1) <= item && item < current.list.get(i)) {
					current = current.getChildren().get(i);
					break;
				}
			}

		}

	}

	@Override
	public String toString() {
		if (root == null) {
			return "";
		}
		StringBuilder s = new StringBuilder();
		LinkedList<BHolder> holder = new LinkedList<>();
		holder.add(root);

		while (true) {

			LinkedList<BHolder> children = new LinkedList<>();

			while (!holder.isEmpty()) {
				BHolder b = holder.getFirst();
				s.append(holder.getFirst().toString());
				s.append(" | ");
				if (b.hasChildren()) {
					assertEquals(b.list.size() + 1, b.getChildren().size());
				}
				children.addAll(b.getChildren());

				holder.removeFirst();

			}
			s.append('\n');

			holder = children;
			if (holder.isEmpty()) {
				break;
			}
		}
		return s.toString();
	}

	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		for (Object object : c) {
			add((int) object);
		}
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	// @Override
	public Object[] toArray() {
		Object[] result = new Object[size()];
		int index = 0;
		LinkedList<BHolder> s = new LinkedList<>();
		s.add(root);

		while (!s.isEmpty()) {
			if (s.getLast().hasChildren()) {
				LinkedList<BHolder> children = new LinkedList<>();
				for (BHolder b : s.getLast().getChildren()) {
					children.addFirst(b);
				}
				s.addAll(children);
			} else {
				ArrayList<Integer> list = s.getLast().list;
				for (int i = 0; i < list.size(); i++) {
					result[index] = list.get(i);
					index++;
				}
				s.removeLast();
			}
		}

		return result;

	}
	//
	// @Override
	// public Object[] toArray(Object[] a) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	public static void main(String[] args) throws Exception {
		BTree tree = new BTree(3);

		for (int i = 200; i >= 0; i--) {
			tree.add(i);
			System.out.println(tree);
		}

	}

}
