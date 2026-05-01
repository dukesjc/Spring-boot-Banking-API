package com.microfinanceBank.Customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.microfinanceBank.Customer.enums.AccountType;
import com.microfinanceBank.Customer.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * 계좌 엔티티
 * 고객의 계좌 정보를 저장합니다
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    /** 계좌 번호 (기본키) */
    @Id
    @Column(unique = true, nullable = false, updatable = false, name = "acct_num")
    private Long accountNumber;

    /** NUBAN 번호 (고유값) */
    @Column(unique = true)
    private String nubanNo;

    /** 계좌 잔액 */
    @Column(name = "balance", nullable = false)
    private BigDecimal accountBalance;

    /** 계좌 유형 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    /** 계좌 상태 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /** 계좌 소유 고객 */
    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    },
            fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    /** 계좌의 직불 카드 */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonBackReference
    private DebitCard debitCard;

    /** 계좌 생성 날짜 */
    @CreationTimestamp
    private Date dateCreated;

    /** 마지막 거래 날짜 */
    @UpdateTimestamp
    private Date lastActivity;
}