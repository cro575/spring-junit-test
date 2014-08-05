package kr.co.vacu;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
//import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.both;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.junit.matchers.JUnitMatchers.everyItem;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.hamcrest.core.CombinableMatcher;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssertTests {

	@BeforeClass
	static public void beforeClass() {
		System.out.println("beforeClass");
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

	@Test
	public void testAssertArrayEquals() {
		byte[] expected = "trial".getBytes();
		byte[] actual = "trial".getBytes();
		org.junit.Assert.assertArrayEquals("failure - byte arrays not same",
				expected, actual);
	}

	@Test
	public void testAssertEquals() {
		org.junit.Assert.assertEquals("failure - strings not same", 5l, 5l);
	}

	@Test
	public void testAssertFalse() {
		org.junit.Assert.assertFalse("failure - should be false", false);
	}

	@Test
	public void testAssertNotNull() {
		org.junit.Assert.assertNotNull("should not be null", new Object());
	}

	@Test
	public void testAssertNotSame() {
		org.junit.Assert.assertNotSame("should not be same Object",
				new Object(), new Object());
	}

	@Test
	public void testAssertNull() {
		org.junit.Assert.assertNull("should be null", null);
	}

	@Test
	public void testAssertSame() {
		Integer aNumber = Integer.valueOf(768);
		org.junit.Assert.assertSame("should be same", aNumber, aNumber);
	}

}