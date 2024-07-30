package com.example.eBanking.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
}
