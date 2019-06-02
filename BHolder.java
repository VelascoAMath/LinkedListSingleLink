import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BHolder {

	ArrayList<Integer> list;
	private ArrayList<BHolder> children;
	private BHolder parent;

	public BHolder getParent() {

		checkChildren();
		return parent;
	}

	public BHolder() {
		super();
		list = new ArrayList<Integer>();
		setChildren(new ArrayList<BHolder>());
		checkChildren();
	}

	public BHolder(int degree) {
		super();
		list = new ArrayList<Integer>(degree);
		setChildren(new ArrayList<BHolder>());
		checkChildren();
	}

	public void add(int item) {

		checkChildren();
		if (list.size() == 0) {
			list.add(item);
			return;
		}

		if (list.size() == 1) {
			if (item < list.get(0)) {
				list.add(0, item);
			} else {
				list.add(item);
			}
			return;
		}

		if (item < list.get(0)) {
			list.add(0, item);
			return;
		}
		if (list.get(list.size() - 1) <= item) {
			list.add(item);
			return;
		}

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1) <= item && item < list.get(i)) {
				list.add(i, item);
				return;
			}
		}
		assertTrue(false);
	}

	public int getFirst() {
		checkChildren();
		return list.get(0);
	}

	public int getLast() {
		checkChildren();
		// System.out.println();
		// System.out.println("Get last " + list);
		// System.out.println("Get last " + toString());
		return list.get(list.size() - 1);
	}

	void checkChildren() {
		for (BHolder child : getChildren()) {
			assertFalse(child.list.isEmpty());
			assertNotEquals(parent, child);
			assertEquals(this, child.parent);
		}
	}

	@Override
	public String toString() {
		return "BHolder [list=" + list + "]";
	}

	public int size() {
		checkChildren();
		return list.size();
	}

	public int popRight() {
		checkChildren();
		return list.remove(list.size() - 1);
	}

	public int popLeft() {
		checkChildren();
		return list.remove(0);
	}

	public BHolder popLeftChild() {
		return children.remove(0);
	}
	
	public BHolder popRightChild() {
		return children.remove(children.size() - 1);
	}

	public void addChild(BHolder child) {
		checkChildren();

		assertNotNull(child);
		// System.out.println("Adding child " + child + " under " + list);

		assertFalse(child.list.isEmpty());
		child.setParent(this);
		if (getChildren().size() == 0) {
			getChildren().add(child);
			return;
		}

		if (child.getLast() < getChildren().get(0).getFirst()) {
			getChildren().add(0, child);
			return;
		}

		if (getChildren().get(getChildren().size() - 1).getLast() <= child.getFirst()) {
			getChildren().add(child);
			return;
		}

		for (int i = getChildren().size() - 1; i >= 1; i--) {
			if (getChildren().get(i - 1).getLast() <= child.getFirst()
					&& child.getLast() < getChildren().get(i).getFirst()) {
				getChildren().add(i, child);
				return;
			}
		}

		assertTrue(false);
	}
	
	public void removeChild(BHolder child) {
		children.remove(child);
	}

	public void setParent(BHolder parent) {
		checkChildren();
		this.parent = parent;
	}

	public boolean hasChildren() {
		checkChildren();
		return getChildren().size() != 0;
	}

	public ArrayList<BHolder> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<BHolder> children) {
		this.children = children;
		checkChildren();
	}

}
