package com.example.eBanking.services;

import com.example.eBanking.dtos.*;
import com.example.eBanking.entities.BankAccount;
import com.example.eBanking.entities.CurrentAccount;
import com.example.eBanking.entities.Customer;
import com.example.eBanking.entities.SavingAccount;
import com.example.eBanking.exceptions.BalanceNotSufficientException;
import com.example.eBanking.exceptions.BankAccountNotFoundException;
import com.example.eBanking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    //Customer saveCustomer(Customer customer);

    CustomerDto saveCustomer(CustomerDto customerDto);
    CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDto saveSavingBankAccount(double initialBalance, double interstRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDto> listCustomer();
    BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDto> bankAccountList();

    CustomerDto getCustomer(Long costumerId) throws CustomerNotFoundException;

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteCustomer(Long customerId);

    List<AccountOperationDto> accountHistory(String accountId);

    AccountHistoryDto getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDto> searchCustomers(String keyword);
}