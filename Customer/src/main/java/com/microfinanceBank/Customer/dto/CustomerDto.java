package com.microfinanceBank.Customer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.microfinanceBank.Customer.controller.CustomerController;
import com.microfinanceBank.Customer.customConstraints.UniqueEmail;
import com.microfinanceBank.Customer.entity.Account;
import com.microfinanceBank.Customer.entity.CustomerDetails;
import com.microfinanceBank.Customer.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

/**
 * 고객 DTO
 * 고객 정보를 전송할 때 사용되는 데이터 전송 객체
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {
    /** 고객 ID */
    private Long id;

    /** Keycloak ID */
    private String keycloakId;

    /** 고객 비밀번호 (필수) */
    @NotNull
    private String password;

    /** 고객 성 (필수) */
    @NotNull
    private String firstName;

    /** 고객 이름 (필수) */
    @NotNull
    private String lastName;

    /** 고객 계좌 목록 (읽기 전용) */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Account> accounts;

    /** 고객 세부 정보 */
    private CustomerDetailsDto customerDetails;

    /** 프로필 이미지 URL */
    private String imageUrl;

    /** 고객 이메일 (필수, 고유) */
    @Email
    @NotNull
    @UniqueEmail
    private String email;

    /** 연락처 (필수) */
    @NotNull
    private String contactNumber;

    /** 주소 정보 (필수) */
    @NotNull
    private AddressDto address;

    /** 고객 상태 */
    private Status status;

    /** 생성 날짜 (읽기 전용) */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date creationDate;

    /** 생성 시간 (읽기 전용) */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Time time;
}