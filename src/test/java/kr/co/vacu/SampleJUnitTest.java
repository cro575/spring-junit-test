package kr.co.vacu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class SampleJUnitTest {
	private int i = 0;

	@BeforeClass
	static public void beforeClass() {
		System.out.println("beforeClass_");
	}
	
	@AfterClass
	static public void afterClass() {
		System.out.println("afterClass");
	}
	
	@Before
	public void before() {
		System.out.println("before");
	}
	
	@After
	public void after() {
		System.out.println("after");
	}

	@Ignore
	public void testIgnore() {
		System.out.println("testIgnore");
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