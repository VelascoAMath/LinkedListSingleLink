import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Test;

class BHolderTest {

	public static <T extends Comparable<? super T>> boolean isSorted(Iterable<T> iterable) {
		Iterator<T> iter = iterable.iterator();
		if (!iter.hasNext()) {
			return true;
		}
		T t = iter.next();
		while (iter.hasNext()) {
			T t2 = iter.next();
			if (t.compareTo(t2) > 0) {
				return false;
			}
			t = t2;
		}
		return true;
	}

	@Test
	void testAddChild() {
		BHolder[] list = new BHolder[100];
		BHolder holder = new BHolder();
		List<Integer> solution = new ArrayList<>();
		for (int i = 1; i <= list.length; i++) {
			solution.add(i);
		}
		Collections.shuffle(solution);

		for (int i = 0; i < list.length; i++) {
			list[i] = new BHolder();
			list[i].add(solution.get(i));
			holder.addChild(list[i]);
			System.out.println(holder.getChildren());
		}

		assertTrue(holder.getChildren().size() == list.length);

		assertTrue(isSorted(holder.getChildren()));
	}

	private boolean isSorted(ArrayList<BHolder> children) {
		for (int i = 1; i < children.size(); i++) {
			if (children.get(i - 1).getLast() > children.get(i).getFirst()) {
				return false;
			}
		}
		return true;
	}

	@Test
	void testAdd() {
		BHolder h = new BHolder(10);

		List<Integer> solution = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				solution.add(i);
			}
		}
		Collections.shuffle(solution);
		for (int x : solution) {
			h.add(x);
		}
		assertEquals(h.list.size(), 100);

		assertTrue(isSorted(h.list));
	}

}
