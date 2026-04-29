package com.microfinanceBank.commondto.transaction;

/**
 * 거래 유형 열거형
 * 거래의 종류를 나타냅니다
 */
public enum TransactionType {
    /** 입금 */
    DEPOSIT,
    /** 출금 */
    WITHDRAW,
    /** 송금 */
    TRANSFER
}
