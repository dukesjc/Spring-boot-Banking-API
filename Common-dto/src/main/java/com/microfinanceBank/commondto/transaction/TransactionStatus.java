package com.microfinanceBank.commondto.transaction;

/**
 * 거래 상태 열거형
 * 거래의 현재 상태를 나타냅니다
 */
public enum TransactionStatus {
    /** 성공 */
    SUCCESS,
    /** 실패 */
    FAILED,
    /** 대기 중 */
    PENDING
}
