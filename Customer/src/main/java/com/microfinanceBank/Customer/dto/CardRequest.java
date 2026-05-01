package com.microfinanceBank.Customer.dto;

import com.microfinanceBank.Customer.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 카드 신청 DTO
 * 카드 신청 정보를 전송할 때 사용되는 데이터 전송 객체
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequest {
    /** 계좌 번호 (필수) */
    @NotNull
    private Long accountNumber;

    /** 카드 유형 (필수) */
    @NotNull
    private CardType cardType;
}