package com.microfinanceBank.Customer.controller;

import com.microfinanceBank.Customer.Config.filters.annotation.XssFilter;
import com.microfinanceBank.commondto.transaction.TransactionDto;
import com.microfinanceBank.commondto.transaction.TransferTransactionDto;
import com.microfinanceBank.Customer.service.BankService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 은행 서비스 컨트롤러
 * 출금 및 송금 등의 거래 기능을 담당합니다
 */
@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
@Validated
@Hidden
public class BankServiceController {
    private final BankService bankService;

    /**
     * 출금
     * 계좌에서 금액을 출금합니다
     *
     * @param transaction 거래 정보
     * @return 출금 결과
     */
    @PostMapping("withdraw")
    public ResponseEntity withdraw(@Valid @RequestBody TransactionDto transaction) {
        bankService.withdraw(transaction);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 송금
     * 한 계좌에서 다른 계좌로 송금합니다
     *
     * @param transfer 송금 정보
     * @return 송금 결과
     */
    @PostMapping("transfer")
    public ResponseEntity transfer(@Valid @RequestBody TransferTransactionDto transfer) {
        bankService.transfer(transfer);
        return new ResponseEntity(HttpStatus.OK);
    }
}