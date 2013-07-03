package kr.co.vacu;

import org.junit.Test;

public class SampleJUnitTest {
	private int i = 1;

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