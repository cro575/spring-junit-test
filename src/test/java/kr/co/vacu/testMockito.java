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
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
 
public class testMockito {
 
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
}