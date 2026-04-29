package com.microfinanceBank.commondto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 위치 정보 DTO
 * 거래가 발생한 위치의 지리적 정보를 담는 데이터 전송 객체
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto implements Serializable {
    /** 위도 */
    private String latitude;
    /** 경도 */
    private String longitude;
}
