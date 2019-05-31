import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
	void testAdd() {
		BHolder h = new BHolder(10);

		List<Integer> solution = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			solution.add(i);
		}
		Collections.shuffle(solution);
		for (int x : solution) {
			h.add(x);
		}

		assertEquals(h.list.size(), 10);
		
		assertTrue(isSorted(h.list));
	}

}
