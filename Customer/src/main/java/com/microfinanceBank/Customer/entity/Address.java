package com.microfinanceBank.Customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 주소 엔티티
 * 고객의 주소 정보를 저장합니다
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    /** 주소 ID (기본키) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 거리 */
    private String street;

    /** 도시 */
    private String city;

    /** 주/도 */
    private String state;

    /** 국가 */
    private String country;

    /** 우편번호 */
    private String postalCode;
}