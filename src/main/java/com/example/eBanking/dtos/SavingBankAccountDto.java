package com.example.eBanking.dtos;

import lombok.Data;
import com.example.eBanking.enums.AccountStatus;
import java.util.Date;

@Data
public class SavingBankAccountDto extends BankAccountDto{
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDto customerDto;
    private double interstRate;

}
