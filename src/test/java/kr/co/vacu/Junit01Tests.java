package kr.co.vacu;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class Junit01Tests {

	@Test
	public void testCreationBean() {
		String names[] = { "y2kpooh", "hwang" };
		String names2[] = { "y2kpooh", "hwang" };
		assertArrayEquals(names2, names);

		List someList = new ArrayList();
		someList.add("aa");
		someList.add("bb");
		someList.add("cc");
		
		assertNotNull("조회결과 null", someList);
		assertTrue(someList.size() > 0);
		assertEquals(3, someList.size());
	}
}
