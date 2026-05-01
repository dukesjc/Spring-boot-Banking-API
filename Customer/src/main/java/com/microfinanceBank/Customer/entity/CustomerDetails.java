package com.microfinanceBank.Customer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.microfinanceBank.Customer.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * 고객 세부 정보 엔티티
 * 고객의 개인 세부 정보를 저장합니다
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails implements Serializable {
    /** 세부 정보 ID (기본키) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 출생 날짜 */
    private LocalDate dob;

    /** 직업 */
    private String occupation;

    /** 결혼 상태 */
    private String maritalStatus;

    /** 장애 여부 */
    private String disability;
}