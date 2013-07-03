package kr.co.vacu.service;

import java.util.*;

import kr.co.vacu.domain.Account;

public class MockAccountManager implements AccountManager {

	private Map<String, Account> accounts = new HashMap<String, Account>();

	/**
	 * 아이디와 account 객체를 HashMap객체에 put
	 * 
	 * @param userId
	 * @param account
	 */
	@Override
	public void addAccount(String userId, Account account) {
		this.accounts.put(userId, account);
	}

	/**
	 * 아이디로 HashMap객체에서 account 객체를 찾아 리턴
	 */
	@Override
	public Account findAccountForUser(String userId) {
		return this.accounts.get(userId);
	}

	/**
	 * 계정 정보를 갱신하며 반환값은 없다.
	 */
	@Override
	public void updateAccount(Account account) {
		// do nothing
	}

	@Override
	public Collection<Account> listAllValuse() {
		return this.accounts.values();
	}
	
}
