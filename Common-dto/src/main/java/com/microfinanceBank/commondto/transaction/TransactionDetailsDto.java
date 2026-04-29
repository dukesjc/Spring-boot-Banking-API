package com.microfinanceBank.commondto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 거래 세부 정보 DTO
 * 거래의 상태, 유형, 위치 등 세부 정보를 담는 데이터 전송 객체
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailsDto  implements Serializable {
    /** 거래 상태 (성공, 실패, 대기 중 등) */
    private TransactionStatus transactionStatus;
    /** 거래 유형 (입금, 출금, 송금 등) */
    private TransactionType transactionType;
    /** 거래 위치 정보 */
    private LocationDto locationDto;
}
