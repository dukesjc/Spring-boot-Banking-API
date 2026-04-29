package com.microfinanceBank.commondto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 출금 큐 DTO
 * 출금 거래 정보를 담는 데이터 전송 객체
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class WithdrawQueue implements Serializable {
    /** 출금 거래 정보 */
    TransactionDto withdrawal;
    /** 거래 세부 정보 */
    TransactionDetailsDto transactionDetails;
}
