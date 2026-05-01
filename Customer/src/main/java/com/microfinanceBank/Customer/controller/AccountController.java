package com.microfinanceBank.Customer.controller;

import com.microfinanceBank.Customer.Config.filters.annotation.XssFilter;
import com.microfinanceBank.Customer.dto.AccountDto;
import com.microfinanceBank.Customer.dto.CustomerDto;
import com.microfinanceBank.Customer.enums.AccountType;
import com.microfinanceBank.Customer.service.AccountService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * 계좌 컨트롤러
 * 계좌 생성, 조회, 삭제 등의 기능을 담당합니다
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    /**
     * 계좌 생성
     * 기존 고객을 위해 추가 계좌를 생성합니다
     *
     * @param customerId 고객 ID
     * @param accountType 계좌 유형
     * @return 생성된 계좌 DTO
     */
    @PostMapping("account")
    @Operation(summary = "계좌 생성", description = "기존 고객을 위해 추가 계좌를 생성합니다", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "계좌 생성 성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))})})
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestParam("customer-id") Long customerId, @RequestParam("account-type") AccountType accountType) {
        AccountDto account = accountService.createAccount(accountType, customerId);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    /**
     * 계좌 조회
     * 계좌 번호로 계좌 정보를 조회합니다
     *
     * @param accountNumber 계좌 번호
     * @return 계좌 DTO
     */
    @GetMapping("account")
    @Operation(summary = "계좌 조회", description = "계좌 번호로 계좌를 조회합니다", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))}),
            @ApiResponse(responseCode = "404", description = "계좌를 찾을 수 없습니다",
                    content = @Content)})
    @Retryable(maxAttempts = 2)
    public ResponseEntity<AccountDto> getCustomerByAccountNumber(@RequestParam("accountNumber") Long accountNumber) {
        AccountDto account = accountService.getAccountByAccountNumber(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /**
     * 모든 계좌 조회
     * 관리자용 기능으로 모든 계좌를 조회합니다 (ADMIN 권한 필요)
     *
     * @return 모든 계좌 목록
     */
    @GetMapping("accounts")
    @RolesAllowed("ADMIN")
    @Operation(summary = "모든 계좌 조회", description = "모든 계좌를 조회합니다", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDto.class))})})
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        var account = accountService.getAllAccounts();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /**
     * 계좌 삭제
     * ID로 계좌를 삭제합니다
     *
     * @param id 계좌 ID
     * @return 삭제 결과
     */
    @DeleteMapping("account")
    @Operation(summary = "계좌 삭제", description = "ID로 계좌를 삭제합니다", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계좌 삭제 완료",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Void.class))})})
    @Retryable(maxAttempts = 1)
    public ResponseEntity deleteById(@RequestParam("id") Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}