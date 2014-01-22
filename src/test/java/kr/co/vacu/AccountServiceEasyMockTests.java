package kr.co.vacu;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import kr.co.vacu.domain.Account;
import kr.co.vacu.service.AccountManager;
import kr.co.vacu.service.AccountService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//http://openframework.or.kr/framework_reference/easymock/2.3/Documentation_ko.html
public class AccountServiceEasyMockTests {
	private AccountManager mockAccountManager;

	@Before
	public void setUp() {
		// 목 객체를 생성한다.
		mockAccountManager = createMock("mockAccountManager", AccountManager.class);
	}

	@Test
	public void testTransferOk() {
		Account senderAccount = new Account("1", 200);
		Account beneficiaryAccount = new Account("2", 100);

		mockAccountManager.updateAccount(senderAccount);
		mockAccountManager.updateAccount(beneficiaryAccount);

		// 기대되는 행위 및 리턴 값 기록 한다.
		// expect : 기대되는 행위 메서드
		// addReturn : 리턴
		expect(mockAccountManager.findAccountForUser("1")).andReturn(senderAccount);
		expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiaryAccount);
		// 해당 목 객체를 수행한다.
		replay(mockAccountManager);

		AccountService accountService = new AccountService();
		accountService.setAccountManager(mockAccountManager);
		accountService.transfer("1", "2", 50);

		assertEquals(150, senderAccount.getBalance());
		assertEquals(150, beneficiaryAccount.getBalance());
	}

	@After
	public void tearDown() {
		// 테스트 실행
		verify(mockAccountManager);
	}
}
