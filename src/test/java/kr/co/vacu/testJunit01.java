package kr.co.vacu;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class testJunit01 {

	@Test
	public void testCreationBean() {
		String names[] = { "y2kpooh", "hwang" };
		String names2[] = { "y2kpooh", "hwang" };
		assertArrayEquals(names2, names);

		class someClass {

			public List getSomeList() {
				List list = new ArrayList();
				list.add("aa");
				list.add("bb");
				list.add("cc");

				return list;
			}
		};

		List someList = new someClass().getSomeList();

		assertNotNull("조회결과 null", someList);
		assertTrue(someList.size() > 0);
		assertEquals(3, someList.size());
	}
	
/*	assertThat(a,is(b)) -  assertThat(값,matcher)    a와 b가 같은지 비교한다. 틀리면 false, 맞으면 true 여기서 is는 matcher 이다. is로 인해 assertthat은 equals같은 비교하는 기능을 가지게된다.
	assertThat(a,is(not((b))) - 같지않는
	assertThat(a,is(not(sameInstance((b)))) - 실제로 같은 인스턴스인가
	assertThat(a,hasItem(b)) - a의 콜렉션의 제네릭타입 이 b의 형과 일치하는지	
*/	
}
