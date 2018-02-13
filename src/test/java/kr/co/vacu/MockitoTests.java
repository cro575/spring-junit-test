package kr.co.vacu;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.spy;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
 


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
 
class Task {
    public String doTask(String name) {
        System.out.println("HELLO WORLD! " + name);
        return name;
    }
}
 
class ProcessTask {
    private List<Task> tasks = new ArrayList<Task>();
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public void doTasks(String name) {
        for (Task task : tasks)
            task.doTask(name);
    }
}
 
class TaskAnswer implements Answer<String> {
 
    @Override
    public String answer(InvocationOnMock invocation) throws Throwable {
        Object[] objs = invocation.getArguments();
        
        if (objs.length > 0)
            return (String) objs[0];
 
        return "default";
    }
}
 
//https://github.com/mockito/mockito/wiki
//https://github.com/mockito/mockito/wiki/Mockito-features-in-Korean
public class MockitoTests {
 
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testMethodCall() {
        ProcessTask taskProcessor = new ProcessTask();
        Task task = mock(Task.class);
        taskProcessor.addTask(task);
        
        String name = new String("Heart");
        taskProcessor.doTasks(name);
        
        // method 호출 확인 : verify(mock, 횟수 조건).메소드명(파라미터 리스트);
        // 해당 파라미터가 들어온 경우에 대한 횟수 체크 가능 (파라미터 비교는 equals() 를 사용)
        // never() == times(0), only == times(1), atLeast() / atMost()
        verify(task, times(1)).doTask("Heart");
        verify(task, never()).doTask("Hello");
        
        // Matchers 를 통해 파라미터의 type 단위 비교도 가능
        verify(task, times(1)).doTask(anyString());
    }
 
    @Test
    public void testMockMethodImplementation() {
        Task task = mock(Task.class);
        
        // Answer 의 경우 메소드 구현이 짧다면 anonymous class 로 바로 만들 수도 있다
        // target method 의 return type 이 void 인 경우, doAnswer() 를 사용하여 구현한다
        TaskAnswer answer = new TaskAnswer();
        when(task.doTask(isA(String.class))).thenAnswer(answer);
        
        assertSame(task.doTask("Heart"), "Heart");
        assertNotSame(task.doTask("Heart"), "Hello");
        
        // 메소드 호출 시 IllegalArgumentException 을 만들어서 던져 준다
        // target method 의 return type 이 void 인 경우, doThrow() 를 사용하여 구현한다
        when(task.doTask(isA(String.class))).thenThrow(new IllegalArgumentException("params are incorrect!"));
 
        // Task.doTask(String) 이 실제로 IllegalArgumentException 이 발생하는 지 테스트
        exception.expect(IllegalArgumentException.class);
        task.doTask("Hello");
    }
    

    @Test
    public void test_mock() {
    	// mock 생성
    	List mockedList = mock(List.class);

	    // mock 객체 사용
	    mockedList.add("one");
	    mockedList.add("two");
	
	    // 검증 하기
	    verify(mockedList).add("one");
	    //verify(mockedList).clear();
    }

    @Test
    public void test_stub() {
    	// interface 뿐 아니라 구체 클래스도 mock으로 만들 수 있다.
	    LinkedList mockedList = mock(LinkedList.class);
	     
	    // stubbing
	    when(mockedList.get(0)).thenReturn("first");
	    when(mockedList.get(1)).thenThrow(new RuntimeException());
	     
	    // 첫 번째 element를 출력한다.
	    System.out.println(mockedList.get(0));
	     
	    // runtime exception이 발생한다.
	    System.out.println(mockedList.get(1));
	     
	    // 999번째 element 얻어오는 부분은 stub되지 않았으므로 null이 출력
	    System.out.println(mockedList.get(999));
	      
	    // stubbing 된 부분이 호출되는지 확인할 수 있긴 하지만 불필요한 일입니다.
	    // 만일 코드에서 get(0)의 리턴값을 확인하려고 하면, 다른 어딘가에서 테스트가 깨집니다.
	    // 만일 코드에서 get(0)의 리턴값에 대해 관심이 없다면, stubbing되지 않았어야 합니다. 더 많은 정보를 위해서는 여기를 읽어보세요.
	    verify(mockedList).get(0);
    }

    @Test
    public void test_Argument_matchers() {
    	
	    LinkedList mockedList = mock(LinkedList.class);

	    // 내장된 argument matcher인 anyInt()를 이용한 stubbing
    	when(mockedList.get(anyInt())).thenReturn("element");
    	 
    	// hamcrest를 이용한 stubbing (isValid()는 사용자가 정의한 hamcrest matcher이다.)
    	//when(mockedList.contains(argThat(isValid()))).thenReturn("element");

    	// 다음 코드는 "element"를 출력한다. 
    	System.out.println(mockedList.get(999));
    	 
    	// argument matcher를 이용해 검증할 수도 있다.
    	verify(mockedList).get(anyInt());
    	
    	
    	//verify(mock).get(anyInt(), anyString(), eq("third argument"));
    	// 위의 코드는 옳은 코드입니다. - eq()는 matcher의 일종입니다.
    	   
    	//verify(mock).get(anyInt(), anyString(), "third argument");
    	// 위의 코드는 틀렸습니다. - 세 번째 파라미터가 argument matcher 없이 전달되었으므로 예외가 던져질 것입니다.    	
    }
    
    @Test
    public void test_times() {
    	// mock 생성
    	List mockedList = mock(List.class);

    	// mock 설정
    	mockedList.add("once");
    	 
    	mockedList.add("twice");
    	mockedList.add("twice");
    	 
    	mockedList.add("three times");
    	mockedList.add("three times");
    	mockedList.add("three times");
    	 
    	// 아래의 두 가지 검증 방법은 동일하다. times(1)은 기본값이라 생략되도 상관없다.
    	verify(mockedList).add("once");
    	verify(mockedList, times(1)).add("once");

    	// 정확히 지정된 횟수만큼만 호출되는지 검사한다.
    	verify(mockedList, times(2)).add("twice");
    	verify(mockedList, times(3)).add("three times");

    	// never()를 이용하여 검증한다. never()는 times(0)과 같은 의미이다.
    	verify(mockedList, never()).add("never happened");

    	// atLeast()와 atMost()를 이용해 검증한다.
    	verify(mockedList, org.mockito.Mockito.atLeastOnce()).add("three times");
    	verify(mockedList, org.mockito.Mockito.atLeast(2)).add("five times");
    	verify(mockedList, org.mockito.Mockito.atMost(5)).add("three times");
    }
    
    @Test
    public void test_순서_검증() {
    	List firstMock = mock(List.class);
    	List secondMock = mock(List.class);
    	 
    	// mock을 사용한다.
    	firstMock.add("was called first");
    	secondMock.add("was called second");

    	// mock이 순서대로 실행되는지 확인하기 위해 inOrder 객체에 mock을 전달한다.
    	InOrder inOrder = org.mockito.Mockito.inOrder(firstMock, secondMock);

    	// 다음 코드는 firstMock이 secondMock 보다 먼저 실행되는 것을 확인한다.
    	inOrder.verify(firstMock).add("was called first");
    	inOrder.verify(secondMock).add("was called second");
    }

    @Test
    public void test_실행되지_않았는지_확인하기() {
    	List mockOne = mock(List.class);
    	List mockTwo = mock(List.class);
    	List mockThree = mock(List.class);

    	// mock 사용하기 - mockOne만 호출된다.
    	mockOne.add("one");
    	 
    	// 일반적인 검증
    	verify(mockOne).add("one");
    	 
    	// 메소드가 호출되지 않았는지 검증
    	verify(mockOne, never()).add("two");
    	 
    	// 다른 mock이 호출되지 않았는지 검증
    	org.mockito.Mockito.verifyZeroInteractions(mockTwo, mockThree);
    }
    
    @Test
    public void test_실제_객체_감시() {
    	List list = new LinkedList();
    	List spy = spy(list);
    	// 특정 메소드만 stub 하는 것이 가능하다.
    	when(spy.size()).thenReturn(100);
    	 
    	// 스파이를 이용해 real method를 호출하기
    	spy.add("one");
    	spy.add("two");
    	 
    	// 리스트의 첫 번째 element인 "one"을 출력해라
    	System.out.println(spy.get(0));
    	 
    	// size() 가 stub 되었으므로 100이 출력된다.
    	System.out.println(spy.size());
    	 
    	// 검증도 가능
    	verify(spy).add("one");
    	verify(spy).add("two");
    	
    }    	
    @Test
    public void test_stubbing된_스파이에대해_when_사용할수없음() {
	   List list = new LinkedList();
	   List spy = spy(list);
	   
	   // real method가 호출되면, spy.get(0)은 IndexOutOfBoundsException 예외를 발생시킨다. (list는 아직 비어있으므로)
	   when(spy.get(0)).thenReturn("foo");
	   
	   // doReturn을 이용해 stubbing해야 한다.
	   org.mockito.Mockito.doReturn("foo").when(spy).get(0);    	
    }
}