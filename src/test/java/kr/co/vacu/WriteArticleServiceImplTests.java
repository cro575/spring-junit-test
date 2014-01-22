package kr.co.vacu;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import kr.co.vacu.dao.ArticleDao;
import kr.co.vacu.dao.IdGenerator;
import kr.co.vacu.domain.Article;
import kr.co.vacu.service.WriteArticleServiceImpl;

import org.junit.Test;

//http://javacan.tistory.com/148
public class WriteArticleServiceImplTests {
	@Test
    public void writeArticle() {
        // mock 객체 생성
        ArticleDao mockedDao = mock(ArticleDao.class);
        IdGenerator mockedGenerator = mock(IdGenerator.class);
        
        when(mockedGenerator.getNextId()).thenReturn("1");
        
        WriteArticleServiceImpl writeArticleService = new WriteArticleServiceImpl();
        writeArticleService.setArticleDao(mockedDao);
        writeArticleService.setIdGenerator(mockedGenerator);
        
        Article article = new Article();
        Article writtenArticle = writeArticleService.writeArticle(article);
        
        assertNotNull(writtenArticle);
        assertNotNull(writtenArticle.getId());

        verify(mockedGenerator).getNextId();
        verify(mockedDao).insert(article);
  
        /*        
        Mock 객체를 사용하는 이유는 테스트 하려는 클래스가 연관된 객체와 올바르게 협업하는 지를 테스트 하기 위함도 있다.
        따라서, Mock 객체의 메서드가 올바르게 실행되는 지 확인해볼 필요가 있다. Mock 객체의 특정 메서드가 호출되었는 지 확인하려면 
        Mockito.verify() 메서드와 Mock 객체의 메서드를 함께 사용하면 된다. 아래는 사용 예이다.

        위 코드에서 verify(mockedGenerator).getNextId() 메서드는 mockedGenerator 객체의 getNextId() 메서드가 호출되었는 지의 여부를 확인한다.
        verify(mockedDao).insert(article) 메서드의 경우 mockedDao 객체의 insert() 메서드 호출 중에서 article 객체를 인자로 전달받는 호출이 있었는 지 여부를 확인한다.
        일단 Mock 객체가 만들어지면 해당 Mock 객체는 메서드 호출을 모두 기억하기 때문에, 어떤 메서드 호출이든 검증할 수 있다.

        
		Article article = new Article();
        when(mockedArticleDao.insert(article)).thenReturn(article));
        
		when(mockedListService.getArticles(1)).thenReturn(someList);
		// 인자 값으로 1을 전달하여 getArticles() 메서드를 호출했는 지의 여부
		verify(mockedListService).getArticles(1);        
        
        //임의의 정수 값을 인자로 전달받은 메서드 호출을 when()과 verify()에서 표현하고 싶다면
        when(mockedListService.getArticles(anyInt())).thenReturn(someList);
        verify(mockedListService).getArticles(anyInt());        
        
        Matchers 클래스는 anyInt() 뿐만 아니라 anyString(), anyDouble(), anyLong(), anyList(), anyMap() 등의 메서드를 제공하는데
        이들 메서드에 대한 자세한 내용은 http://mockito.googlecode.com/svn/branches/1.6/javadoc/org/mockito/Matchers.html 사이트를 참        
        

        //인자 중 한가지라도 Argument Matcher를 사용하면 나머지 인자에 대해서도 Matcher를 사용해야 한다. 예를 들어, 아래 코드는 예외를 발생한다.
        Authenticator authenticator = mock(Authenticator.class);
        when(authenticator.authenticate(anyString(), "password")).thenReturn(authObj);
        
        //만약 여러 인자 중 특정 값을 명시해야 하는 경우가 필요하다면 eq() Matcher를 사용하면 된다. 아래는 위 코드를 eq()를 이용해서 수정한 코드를 보여주고 있다.
        Authenticator authenticator = mock(Authenticator.class);
        when(authenticator.authenticate(anyString(), eq("password"))).thenReturn(authObj);        
        
        //Mock 객체의 메서드 호출시 예외를 발생시키고 싶을 때가 있는데, 이런 경우에는 thenThrow() 메서드를 사용하면 된다.
        when(mockedDao.insert(article)).thenThrow(new RuntimeException("invalid title"));
        
        //메서드가 지정한 회수 만큼 호출되었는 지의 여부를 확인하려면 times() 메서드를 사용하면 된다. 예를 들어, Mock 객체의 특정 메서드가 3번 호출되었는 지 확인하려면 다음과 같이 verify() 메서드의 두 번째 인자에 times() 메서드를 (정확히는 times() 메서드의 리턴 값을) 전달해주면 된다.
        verify(mockedAuthenticator, times(3)).authenticate(anyString(), anyString());
        
		//times(int) - 지정한 회수 만큼 호출되었는 지 검증
		//never() - 호출되지 않았는지 여부 검증
		//atLeastOnce() - 최소한 한번은 호출되었는 지 검증
		//atLeast(int) - 최소한 지정한 회수 만큼 호출되었는 지 검증
		//atMost(int) - 최대 지정한 회수 만큼 호출되었는 지 검증        
*/
        
    }
}

/*
 
Answer를 이용한 메서드 구현

Mock 객체를 사용하다보면 직접 Mock의 동작 방식을 구현해 주고 싶을 때가 있다. (사실, 필자가 개인적으로 굳이 간단한 Mock 라이브러릴 만든 이유도 이것 때문이었다.) 이런 경우 thenAnswer() 메서드와 Answer 인터페이스를 사용하면 된다. 아래 코드는 사용 예이다.

when(mockedGenerator.getNextId()).thenAnswer(new Answer<Integer>() {
    private int nextId = 0;
    public Integer answer(InvocationOnMock invocation) throws Throwable {
        return new Integer(++nextId);
    }
});
위와 같이 Answer를 사용하면, mockedGenerator의 getNextId() 메서드를 호출할 때 마다 answer() 메서드가 호출된다. 위 코드의 경우 getnextId() 메서드가 호출될 때 마다 1씩 증가된 값을 리턴하는 Anwser 구현  클래스를 리턴하였다.

만약 파라미터로 전달되는 값을 사용하고 싶다면 answer() 메서드에 전달된 InvocationOnMock을 이용하면 된다. 아래 코드는 사용 예이다.

when(authenticator.authenticate(anyString(), anyString())).thenAnswer(new Answer<Object> (){
    public Object answer(InvocationOnMock invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        String userId = (String) arguments[0];
        String password = (String) arguments[1];
        Object authObject = null;
        // ...
        return authObject;
    }
});


 
//@Mock 어노테이션을 이용한 코드 단순화
 
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(MockitoJUnit44Runner.class)
public class WriteArticleServiceImplTest {

    @Mock Authenticator authenticator;
    @Mock ArticleDao mockedDao;
    @Mock IdGenerator mockedGenerator;
    
    @Test
    public void setup() {
        when(authenticator.authenticate(anyString(), eq("password"))).thenReturn(null);
        ...
    }

}
또는 테스트가 실행되기 전에 명시적으로 MockitoAnnotations.initMocks(this) 메서드를 호출해주면 된다.

public class WriteArticleServiceImplTest {

    @Mock Authenticator authenticator;
    @Mock ArticleDao mockedDao;
    @Mock IdGenerator mockedGenerator;
    
    @Before
    public void test() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void writeArticle() {
        when(authenticator.authenticate(anyString(), eq("password"))).thenReturn(null);
        ...
    }
}
    
*/