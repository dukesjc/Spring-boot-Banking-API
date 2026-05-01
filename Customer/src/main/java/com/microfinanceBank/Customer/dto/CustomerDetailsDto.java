package com.microfinanceBank.Customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 * 고객 세부 정보 DTO
 * 고객의 개인 세부 정보를 전송할 때 사용되는 데이터 전송 객체
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerDetailsDto implements Serializable {
    /** 출생 날짜 */
    private LocalDate dob;

    /** 직업 */
    private String occupation;

    /** 결혼 상태 */
    private String maritalStatus;

    /** 장애 여부 */
    private String disability;
}