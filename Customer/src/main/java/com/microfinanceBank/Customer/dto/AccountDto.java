package com.microfinanceBank.Customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microfinanceBank.Customer.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 계좌 DTO
 * 계좌 정보를 전송할 때 사용되는 데이터 전송 객체
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    /** 계좌 번호 */
    @JsonProperty
    private Long accountNumber;

    /** NUBAN 번호 */
    @JsonProperty
    private String nubanNo;

    /** 계좌 잔액 */
    @JsonProperty
    private BigDecimal accountBalance;

    /** 계좌 유형 */
    @JsonProperty
    private AccountType accountType;

    /** 고객 정보 */
    @JsonProperty
    private CustomerDto customer;
}