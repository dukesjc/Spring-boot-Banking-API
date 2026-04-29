package com.microfinanceBank.commondto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 입금 큐 DTO
 * 입금 거래 정보와 거래 세부 정보를 포함하는 데이터 전송 객체
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class DepositQueue implements Serializable {
    /** 입금 거래 정보 */
    TransactionDto deposit;
    /** 거래 세부 정보 */
    TransactionDetailsDto transactionDetails;
}
