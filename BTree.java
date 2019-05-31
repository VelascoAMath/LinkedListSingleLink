import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class BTree implements Set, Collection {

	int degree;
	BHolder root;

	public BTree(int degree) throws Exception {
		if(degree < 3) {
			System.err.println("Degree (" + degree + ") is too small!");
			throw new Exception();
		}
		this.degree = degree;
		root = null;
	}

	public boolean add(int arg0) {
		if (root == null) {
			root = new BHolder(degree);
		}
		BHolder current = root;
		current.add(arg0);
		if(current.size() == degree) {
			rebalanceInsertion(current);
		}
		
		return false;
	}


	private void rebalanceInsertion(BHolder current) {
		
	}

	@Override
	public String toString() {
		return "BTree [degree=" + degree + ", root=" + root + "]";
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		for (Object object : c) {
			add(object);
		}
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {
		BTree tree = new BTree(5);

		for (int i = 0; i < 100; i++) {
			tree.add(i);
		}

		System.out.println(tree);
	}

	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

}
