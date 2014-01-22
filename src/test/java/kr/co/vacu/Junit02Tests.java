package kr.co.vacu;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import kr.co.vacu.controller.AccountController;
import kr.co.vacu.domain.Account;
import kr.co.vacu.service.AccountManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-*.xml"})

//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
//@Transactional

public class Junit02Tests {

/*	@Test
	 public void testUserDeptSearch() {
	  fail("Not yet implemented");
	 }*/
	
	@Test
	public void accountControllerTest() {
		// ApplicationContext 생성
		ApplicationContext applicationContext = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-*.xml");// classpath:/applicationContext.xml
		// 테스트 대상 객체 꺼내오기
		AccountController accountController = applicationContext.getBean(AccountController.class);
		assertThat(accountController, is(notNullValue()));
		// 테스트
	}
	
	@Test
	public void formSubmitSuccess(){
		AccountController controller = new AccountController();
		AccountManager accountManager = mock(AccountManager.class);
		controller.setAccountManager(accountManager);
		Account account = new Account();
		account.setAccountId("accid");
		BindingResult result = new BeanPropertyBindingResult(account, "account");
		Model model = new ExtendedModelMap();
		String viewName = controller.formSubmit(account, result, model);
		assertThat(viewName, is("redirect:account/list"));
		verify(accountManager).addAccount("1", account);
	}
	
	@Test
	public void formSubmitFail(){
		AccountController controller = new AccountController();
		AccountManager accountManager = mock(AccountManager.class);
		controller.setAccountManager(accountManager);
		Account account = new Account();

		BindingResult result = new BeanPropertyBindingResult(account, "account");
		result.rejectValue("accountId", "required", "input title please");
		Model model = new ExtendedModelMap();
		String viewName = controller.formSubmit(account, result, model);
		assertThat(viewName, is("account/form"));
	}
}
