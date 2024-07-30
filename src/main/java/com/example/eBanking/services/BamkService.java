package com.example.eBanking.services;


import com.example.eBanking.entities.BankAccount;
import com.example.eBanking.entities.CurrentAccount;
import com.example.eBanking.entities.SavingAccount;
import com.example.eBanking.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BamkService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=
                bankAccountRepository.findById("0c5ed164-d18a-4738-b71f-86bb703800fc").orElse(null);
        if (bankAccount != null){
            System.out.println("**********************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount){
                System.out.println("Over Draft =>"+((CurrentAccount)bankAccount).getOverDraft());
            }else if (bankAccount instanceof SavingAccount){
                System.out.println("Le taux d'interet =>"+((SavingAccount)bankAccount).getInterstRate());
            }
            bankAccount.getAccountOperations().forEach(op->{
                System.out.println("=======================");
                System.out.println(op.getType());
                System.out.println(op.getAmount());
                System.out.println(op.getOperationDate());
            });
        }

    }
}
