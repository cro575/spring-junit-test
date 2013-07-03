package kr.co.vacu.service;

import java.util.Collection;

import kr.co.vacu.domain.Account;

public interface AccountManager {

		public void addAccount(String userId, Account account);

	  /**
	    * 아이디로 계좌 계정찾기
	    * 
	    * @param userId
	    * @return
	    */
	   Account findAccountForUser( String userId );

	   /**
	    * 계좌 계정 업데이트
	    * 
	    * @param account
	    */
	   void updateAccount( Account account );
	   
	   public Collection<Account> listAllValuse();
}
