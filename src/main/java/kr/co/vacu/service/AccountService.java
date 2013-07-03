package kr.co.vacu.service;

import kr.co.vacu.domain.Account;

public class AccountService {
	/**
	 * AccountManger 인터페이스 선언
	 */
	private AccountManager accountManager;

	/**
	 * 객체 초기화
	 * 
	 * @param manager
	 */
	public void setAccountManager(AccountManager manager) {
		this.accountManager = manager;
	}

	/**
	 * 두 계좌 사이 송금기능
	 * 
	 * @param senderId
	 * @param beneficiaryId
	 * @param amount
	 */
	public void transfer(String senderId, String beneficiaryId, long amount) {
		Account sender = this.accountManager.findAccountForUser(senderId);
		Account beneficiary = this.accountManager
				.findAccountForUser(beneficiaryId);

		sender.debit(amount);
		beneficiary.credit(amount);
		this.accountManager.updateAccount(sender);
		this.accountManager.updateAccount(beneficiary);
	}
}
