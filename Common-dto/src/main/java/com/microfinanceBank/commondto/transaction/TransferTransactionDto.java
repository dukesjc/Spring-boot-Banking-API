package com.microfinanceBank.commondto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 송금 거래 DTO
 * 계좌 간 송금 정보를 담는 데이터 전송 객체
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferTransactionDto implements Serializable {

    /** 원본 계좌 번호 (필수) */
    @NotNull
    private Long sourceAccount;
    /** 수신자 계좌 번호 (필수) */
    @NotNull
    private Long destinationAccount;
    /** 송금 금액 (필수, 양수) */
    @NotNull
    @Positive
    private BigDecimal amount;
    /** 송금 설명 (필수, 공백 불가) */
    @NotNull
    @NotBlank
    private String description;
    /** 위치 정보 */
    private LocationDto locationDto;
}
