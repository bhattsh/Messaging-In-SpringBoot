package com.capgemini.bankWebApp.account.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.bankWebApp.account.entity.SavingsAccount;
import com.capgemini.bankWebApp.account.service.SavingsAccountService;
import com.capgemini.bankWebApp.transactionService.entity.Transaction;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

	@Autowired
	private SavingsAccountService savingsService;
	
	
	@PostMapping
	public void addSavingsAccount(@RequestBody SavingsAccount account) {
		savingsService.addNewAccount(account);
	}
	
	@GetMapping("{accountNumber}")
	public ResponseEntity<Optional<SavingsAccount>> getSavingsAccountById(@PathVariable Integer accountNumber) {
		
		Optional<SavingsAccount> savingAccount = savingsService.getSavingAccountById(accountNumber);
		
		if(savingAccount == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		 return new ResponseEntity<>(savingAccount, HttpStatus.OK);
	}
	
	@GetMapping("{accountNumber}/balance")
	public ResponseEntity<Double> getBalance(@PathVariable Integer accountNumber) {
		
		Optional<SavingsAccount> savingAccount = savingsService.getSavingAccountById(accountNumber);
	
		if(savingAccount == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Double currentBalance = savingAccount.get().getCurrentBalance();
		 return new ResponseEntity<>(currentBalance, HttpStatus.OK);
	}

	
	public ResponseEntity<Object> updateBalance(Transaction transaction) {
		System.out.println("account no is :"+transaction.getAccountNumber());
		Optional<SavingsAccount> savingAccount = savingsService.getSavingAccountById(transaction.getAccountNumber());
		
		if(savingAccount == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		savingAccount.get().setCurrentBalance(transaction.getCurrentBalance());
		savingsService.addNewAccount(savingAccount.get());
		
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<SavingsAccount>> getAllSavingsAccount() {
		
		List<SavingsAccount> savingsAccount = savingsService.getAllSavingsAccount();
		
		if(savingsAccount == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		 return new ResponseEntity<>(savingsAccount, HttpStatus.OK);
	}
	
	@DeleteMapping("{accountNumber}")
	public ResponseEntity<String> deleteSavingsAccount(@PathVariable Integer accountNumber) {
		
		Optional<SavingsAccount> savingAccount = savingsService.getSavingAccountById(accountNumber);
		
		if(savingAccount == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		savingsService.deleteSavingAccount(savingAccount.get());
		 return new ResponseEntity<>("Account Deleted Successfully", HttpStatus.OK);
	}

}
