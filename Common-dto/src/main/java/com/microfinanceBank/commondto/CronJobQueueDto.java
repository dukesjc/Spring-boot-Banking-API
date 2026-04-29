package com.microfinanceBank.commondto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 크론 작업 큐 DTO
 * 대출 이자 및 원금 상환 관련 정보를 담는 데이터 전송 객체
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CronJobQueueDto implements  Serializable{
    /** 부채 이자 */
    private BigDecimal debtInterest;
    /** 현재 부채 */
    private BigDecimal currentDebt;
    /** 차용자 계좌 번호 */
    private Long borrowerAccountNumber;
    /** 대출 ID */
    private String loanId;
    /** 출금 금액 */
    private BigDecimal amountWithdrawn;


}
