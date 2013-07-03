package kr.co.vacu;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.Locale;

import kr.co.vacu.controller.AccountController;
import kr.co.vacu.domain.Account;
import kr.co.vacu.service.AccountManager;
import kr.co.vacu.service.AccountService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-*.xml"})
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
//@ContextConfiguration(locations={"file:src/main/**/root-*.xml"})

public class TestAccountControllerEasyMock {

	 Log logger = LogFactory.getLog(TestAccountControllerEasyMock.class);
	 
	@Autowired
	private AccountController accountController;

	@Test
	public void testController() {
		
		  MockHttpServletRequest request = new MockHttpServletRequest();
		  MockHttpServletResponse response = new MockHttpServletResponse();
		  Model model = new ExtendedModelMap();
		  Locale locale = new Locale("");
		  
		  request.setParameter("keyword", "나이키");
		  
		  String result = accountController.list(locale, model, request, response);
		  logger.debug(result);
		  
		  assertEquals(result,"account/list");
		  assertEquals(model.asMap().get("keyword"), "나이키");
	}
}
