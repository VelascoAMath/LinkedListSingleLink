import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BHolder {

	ArrayList<Integer> list;
	ArrayList<BHolder> children;

	public BHolder(int degree) {
		super();
		list = new ArrayList<Integer>(degree);
		children = new ArrayList<BHolder>();
	}

	public void add(int item) {

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

	@Override
	public String toString() {
		return "BHolder [list=" + list + "]";
	}

	public int size() {
		return list.size();
	}

}
