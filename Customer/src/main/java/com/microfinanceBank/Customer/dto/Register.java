package com.microfinanceBank.Customer.dto;

import com.microfinanceBank.Customer.Config.filters.annotation.XssFilter;
import com.microfinanceBank.Customer.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 고객 등록 DTO
 * 고객 등록 정보를 전송할 때 사용되는 데이터 전송 객체
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Register {
    /** 고객 정보 */
    @Valid
    private CustomerDto customer;

    /** 초기 계좌 유형 (필수) */
    @NotNull
    private AccountType accountType;
}