package com.example.football_manager.controller;

import com.example.football_manager.dto.TransferRequestDto;
import com.example.football_manager.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDto requestDto) {
        try {
            transferService.transferPlayer(requestDto);
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            // Можна використати @ControllerAdvice для глобальної обробки
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
