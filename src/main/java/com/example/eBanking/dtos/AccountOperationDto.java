package com.example.eBanking.dtos;

import com.example.eBanking.enums.OprationType;

import lombok.Data;

import java.util.Date;


@Data
public class AccountOperationDto {
    private Long id;
    private Date operationDate;
    private double amount;
    private OprationType type;
    private String description;
}
