package com.example.eBanking;

import com.example.eBanking.dtos.BankAccountDto;
import com.example.eBanking.dtos.CurrentBankAccountDto;
import com.example.eBanking.dtos.CustomerDto;
import com.example.eBanking.dtos.SavingBankAccountDto;
import com.example.eBanking.entities.*;
import com.example.eBanking.enums.AccountStatus;
import com.example.eBanking.enums.OprationType;
import com.example.eBanking.exceptions.BalanceNotSufficientException;
import com.example.eBanking.exceptions.BankAccountNotFoundException;
import com.example.eBanking.exceptions.CustomerNotFoundException;
import com.example.eBanking.repositories.AccountOperationRepository;
import com.example.eBanking.repositories.BankAccountRepository;
import com.example.eBanking.repositories.CustomerRepository;
import com.example.eBanking.services.BamkService;
import com.example.eBanking.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
		return args -> {
			Stream.of("Hassan","Imane","Mohammed").forEach(name->{
				CustomerDto customer = new CustomerDto();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listCustomer().forEach(customer -> {
				try {
					bankAccountService.saveCurrentBankAccount(Math.random()*9000,9000,customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5, customer.getId());

				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
                }
            });

			List<BankAccountDto> bankAccounts = bankAccountService.bankAccountList();
			for (BankAccountDto bankAccount:bankAccounts){
				for (int i = 0 ; i<10; i++){
					String accountId;
					if (bankAccount instanceof SavingBankAccountDto){
						accountId=((SavingBankAccountDto) bankAccount).getId();
					}else {
						accountId=((CurrentBankAccountDto) bankAccount).getId();
					}
					bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
					bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
				}
			}

		};
	}

	//@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository accountOperationRepository){
		return args -> {
			Stream.of("Hassan","Mohammed","Ayoub").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(cust->{

				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(cust);
				currentAccount.setOverDraft(90000);
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cust);
				savingAccount.setInterstRate(5.5);
				bankAccountRepository.save(savingAccount);
			});

			bankAccountRepository.findAll().forEach(acc->{
				for (int i = 0 ; i<10 ; i++){
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*12000);
					accountOperation.setType(Math.random()>0.5? OprationType.DEBIT: OprationType.CREDIT);
					accountOperation.setBankAccount(acc);
					accountOperationRepository.save(accountOperation);

				}
			});
		};
	}
}
