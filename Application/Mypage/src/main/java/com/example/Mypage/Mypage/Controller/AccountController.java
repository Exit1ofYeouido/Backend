package com.example.Mypage.Mypage.Controller;

import com.example.Mypage.Mypage.Dto.out.GetPointResponseDto;
import com.example.Mypage.Mypage.Service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/point")
    @Operation(description = "나의 현재 포인트 조회")
    public ResponseEntity<GetPointResponseDto> getPoint(@RequestHeader("memberId") Long memberId) {
        //TODO : Long Type으로 매핑되지 않은 경우에 대한 Exception Handler 만들기
        GetPointResponseDto getPointResponseDto = accountService.getPoint(memberId);
        return ResponseEntity.ok(getPointResponseDto);
    }

    @GetMapping("/point-history")
    @Operation(description = "나의 포인트 변동내역 조회")
    public ResponseEntity<?> getPointHistory(@RequestHeader("memberId") Long memberId,
                                             @RequestParam(defaultValue = "0") int index,
                                             @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(accountService.getPointHistory(memberId, index, size));
    }

    @GetMapping("/stocks")
    @Operation(description = "나의 보유주식 조회")
    public ResponseEntity<?> getMyStocks(@RequestHeader("memberId") Long memberId) {
        return ResponseEntity.ok(accountService.getAllMyStocks(memberId));
    }
}
