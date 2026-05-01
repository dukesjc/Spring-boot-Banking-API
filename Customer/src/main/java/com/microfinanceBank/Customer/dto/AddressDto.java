package com.microfinanceBank.Customer.dto;

import com.microfinanceBank.Customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 주소 DTO
 * 주소 정보를 전송할 때 사용되는 데이터 전송 객체
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {
    /** 주소 ID */
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