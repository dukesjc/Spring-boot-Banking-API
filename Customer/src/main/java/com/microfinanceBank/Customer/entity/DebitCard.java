package com.microfinanceBank.Customer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.microfinanceBank.Customer.enums.CardStatus;
import com.microfinanceBank.Customer.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 직불 카드 엔티티
 * 고객의 직불 카드 정보를 저장합니다
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitCard {
    /** 카드 ID (기본키) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 카드가 연결된 계좌 목록 */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "debitCard", cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JsonManagedReference
    private Set<Account> accounts;

    /** 카드 번호 (고유값) */
    @Column(nullable = false, unique = true)
    private String cardNo;

    /** CVV 번호 */
    @Column(nullable = false)
    private int cvvNo;

    /** 카드 유형 */
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    /** 카드 상태 */
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    /** 카드 발급 날짜 */
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate issuedDate;

    /** 카드 만료 날짜 */
    @Column(nullable = false)
    private LocalDate expireDate;

    /** 마지막 거래 날짜 */
    @UpdateTimestamp
    private Date lastActivity;

    /**
     * 계좌 추가
     * 직불 카드에 계좌를 추가합니다
     *
     * @param account 추가할 계좌
     */
    public void addAccount(Account account) {
        if (account != null) {
            if (this.accounts == null)
                this.accounts = new HashSet<>();
            this.accounts.add(account);
            account.setDebitCard(this);
        }
    }
}