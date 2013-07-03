package kr.co.vacu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SampleJUnitTest {
	private int i = 1;

	@Before
	public void before() {
		System.out.println("before");
	}
	
	@After
	public void after() {
		System.out.println("before");
	}
	
	@Test
	public void test1() {
		i++;
		System.out.println(i);
	}

	@Test
	public void test2() {
		i++;
		System.out.println(i);
	}
}