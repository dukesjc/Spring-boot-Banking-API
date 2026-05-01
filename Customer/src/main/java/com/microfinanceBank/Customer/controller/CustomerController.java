package com.microfinanceBank.Customer.controller;

import com.microfinanceBank.Customer.dto.CustomerDto;
import com.microfinanceBank.Customer.dto.Register;
import com.microfinanceBank.Customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

/**
 * 고객 컨트롤러
 * 고객 정보 생성, 조회, 수정, 삭제 등의 기능을 담당합니다
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    /**
     * 고객 생성
     * 새로운 고객 계좌를 생성합니다
     *
     * @param register 고객 등록 정보
     * @return 생성된 고객 DTO
     */
    @PostMapping("customer")
    @Operation(summary = "고객 생성", description = "고객 계좌를 생성합니다", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "고객 계좌 생성 성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))}),
            @ApiResponse(responseCode = "409", description = "이메일 주소가 이미 존재합니다",
                    content = @Content)})
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody Register register) {
        CustomerDto savedUser = customerService.createCustomer(register);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * 모든 고객 조회
     * 관리자용 기능으로 모든 고객을 조회합니다 (ADMIN 권한 필요)
     *
     * @return 모든 고객 목록
     */
    @GetMapping("customers")
    @RolesAllowed("ADMIN")
    @Operation(summary = "모든 고객 조회", description = "고객 목록을 반환합니다", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))}),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 접근",
                    content = @Content)})
    @Retryable(maxAttempts = 2)
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    /**
     * 고객 정보 수정
     * 고객 계좌 정보를 수정합니다
     *
     * @param customer 고객 DTO
     * @return 수정된 고객 DTO
     */
    @PatchMapping("customer")
    @Operation(summary = "고객 정보 수정", description = "고객 계좌 정보를 수정합니다", tags = "Patch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))})})
    public ResponseEntity<CustomerDto> updateCustomerDetails(@Valid @RequestBody CustomerDto customer) {
        CustomerDto customerDto = customerService.updateCustomerDetails(customer);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    /**
     * 고객 계좌 삭제
     * 고객 및 모든 계좌를 삭제합니다
     *
     * @param id 고객 ID
     * @param keycloakId Keycloak ID
     * @return 삭제 결과
     */
    @DeleteMapping("customer")
    @Operation(summary = "고객 삭제", description = "고객 및 모든 계좌를 삭제합니다", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로필 삭제 완료",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Void.class))})})
    public ResponseEntity deleteById(@RequestParam("id") Long id, @RequestParam("keycloakId") String keycloakId) {
        customerService.deleteCustomer(id, keycloakId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}