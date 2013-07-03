package kr.co.vacu.domain;

public class Account {
	/**
	 * 계좌 아이디
	 */
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	/**
	 * 계좌 잔고
	 */
	private long balance;


	public Account() {

	}
	
	/**
	 * 초기화
	 * 
	 * @param accountId
	 * @param initialBalance
	 */
	public Account(String accountId, long initialBalance) {
		this.accountId = accountId;
		this.balance = initialBalance;
	}

	/**
	 * 출금
	 * 
	 * @param amount
	 */
	public void debit(long amount) {
		this.balance -= amount;
	}

	/**
	 * 입금
	 * 
	 * @param amount
	 */
	public void credit(long amount) {
		this.balance += amount;
	}

	/**
	 * 현재 잔고
	 * 
	 * @return
	 */
	public long getBalance() {
		return this.balance;
	}
}
