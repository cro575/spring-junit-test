package kr.co.vacu;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;

//import static org.hamcrest.core.StringContains.containsString;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.both;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.junit.matchers.JUnitMatchers.either;
import static org.junit.matchers.JUnitMatchers.everyItem;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.*;

import org.junit.Test;

public class AssertThatTests {

	//https://code.google.com/p/hamcrest/wiki/Tutorial
	/*	
	Core
		anything - always matches, useful if you don't care what the object under test is
		describedAs - decorator to adding custom failure description
		is - decorator to improve readability - see "Sugar", below
	Logical
		allOf - matches if all matchers match, short circuits (like Java &&)
		anyOf - matches if any matchers match, short circuits (like Java ||)
		not - matches if the wrapped matcher doesn't match and vice versa
	Object
		equalTo - test object equality using Object.equals
		hasToString - test Object.toString
		instanceOf, isCompatibleType - test type
		notNullValue, nullValue - test for null
		sameInstance - test object identity
	Beans
		hasProperty - test JavaBeans properties
	Collections
		array - test an array's elements against an array of matchers
		hasEntry, hasKey, hasValue - test a map contains an entry, key or value
		hasItem, hasItems - test a collection contains elements
		hasItemInArray - test an array contains an element
	Number
		closeTo - test floating point values are close to a given value
		greaterThan, greaterThanOrEqualTo, lessThan, lessThanOrEqualTo - test ordering
	Text
		equalToIgnoringCase - test string equality ignoring case
		equalToIgnoringWhiteSpace - test string equality ignoring differences in runs of whitespace
		containsString, endsWith, startsWith - test string matching
	*/
		
	/*	assertThat(a,is(b)) -  assertThat(값,matcher)    a와 b가 같은지 비교한다. 틀리면 false, 맞으면 true 여기서 is는 matcher 이다. is로 인해 assertthat은 equals같은 비교하는 기능을 가지게된다.
		assertThat(a,is(not((b))) - 같지않는
		assertThat(a,is(not(sameInstance((b)))) - 실제로 같은 인스턴스인가
		assertThat(a,hasItem(b)) - a의 콜렉션의 제네릭타입 이 b의 형과 일치하는지	
	*/	
	
		
	  // JUnit Matchers assertThat
	  @Test
	  public void test01() {
		  assertThat("albumen", both(containsString("a")).and(containsString("b")));
		  assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
		  assertThat(Arrays.asList(new String[] { "fun", "ban", "net" }), everyItem(containsString("n")));
		  assertThat("colr colour cccolr" , either(containsString("color")).or(containsString("colour")));
		  assertThat("colr colour cccolr", anyOf(containsString("color"), containsString("colour")));

	  }

	  // Core Hamcrest Matchers with assertThat
	  @Test
	  public void testAssertThatHamcrestCoreMatchers() {
//	    assertThat("good", allOf(equalTo("good"), startsWith("good")));
	    assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
	    assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
//	    assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
	    assertThat(new Object(), not(sameInstance(new Object())));
	  }
	  
	  //http://www.objectpartners.com/2013/09/18/the-benefits-of-using-assertthat-over-other-assert-methods-in-unit-tests/
	  @Test
	  public void testOldAssertVSassertThat() { 
		  assertThat("test", anyOf(is("test2"), containsString("te")));
		  
		  assertArrayEquals(new String[] {"test1", "test2"}, new String[] {"test3", "test4"});		assertThat(new String[] {"test3", "test4"}, is(new String[] {"test1", "test2"}));
		  
		  assertEquals("expected", "actual");		assertThat("actual", is("expected"));

		  assertTrue(true);							assertThat(true, is(true));
		  assertFalse(false);						assertThat(true, is(false));	
		  
		  assertNull(null);							assertThat(null, nullValue());
		  assertNotNull(null);						assertThat(null, notNullValue());
		  
		  assertSame("expected", "actual"); 		assertThat("actual", sameInstance("expected"));
		  assertNotSame("expected", "actual");		assertThat("actual", not(sameInstance("expected")));
		  assertTrue(1 > 3);						assertThat(1, greaterThan(3));
		  
		  assertTrue("abc".contains("d"));			assertThat("abc", containsString("d"));
		  assertTrue("abc".contains("d"));			assertThat("abc", containsString("d"));
	  }

}
