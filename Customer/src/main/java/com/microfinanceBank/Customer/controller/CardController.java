package com.microfinanceBank.Customer.controller;

import com.microfinanceBank.Customer.Config.filters.annotation.XssFilter;
import com.microfinanceBank.Customer.dto.AccountDto;
import com.microfinanceBank.Customer.dto.CardRequest;
import com.microfinanceBank.Customer.dto.CustomerDto;
import com.microfinanceBank.Customer.enums.AccountType;
import com.microfinanceBank.Customer.service.AccountService;
import com.microfinanceBank.Customer.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 카드 컨트롤러
 * 직불 카드 신청 등의 기능을 담당합니다
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    /**
     * 카드 신청
     * 직불 카드를 신청합니다
     *
     * @param cardRequest 카드 신청 정보
     * @return 신청 결과
     */
    @PostMapping("card-request")
    @Operation(summary = "카드 신청", description = "직불 카드를 신청합니다", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "카드 신청 성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))})})
    public ResponseEntity requestCard(@Valid @RequestBody CardRequest cardRequest) {
        cardService.cardRequest(cardRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}