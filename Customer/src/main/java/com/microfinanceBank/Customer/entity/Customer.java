package com.microfinanceBank.Customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.microfinanceBank.Customer.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 고객 엔티티
 * 고객의 기본 정보를 저장합니다
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    /** 고객 ID (기본키) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 고객 이메일 (고유값) */
    @Column(unique = true, nullable = false)
    private String email;

    /** Keycloak ID (고유값) */
    @Column(nullable = false, unique = true, updatable = false)
    private String keycloakId;

    /** 고객 성 */
    @Column(nullable = false)
    private String firstName;

    /** 고객 이름 */
    @Column(nullable = false)
    private String lastName;

    /** 고객 비밀번호 */
    private String password;

    /** 고객의 계좌 목록 */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Account> accounts;

    /** 프로필 이미지 URL */
    private String imageUrl;

    /** 연락처 */
    private String contactNumber;

    /** 주소 정보 */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_address_id")
    private Address address;

    /** 고객 세부 정보 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private CustomerDetails customerDetails;

    /** 고객 상태 */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    /** 생성 날짜 */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date creationDate;

    /** 생성 시간 */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Time time;

    /**
     * 계좌 추가
     * 고객에게 새로운 계좌를 추가합니다
     *
     * @param account 추가할 계좌
     */
    public void addAccount(Account account) {
        if (account != null) {
            if (this.accounts == null) {
                accounts = new HashSet<>();
            }
            accounts.add(account);
            account.setCustomer(this);
        }
    }
}