package com.example.digirealtor.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digirealtor.Dtos.AnalysisDto;
import com.example.digirealtor.Dtos.TransactinResponseDto;
import com.example.digirealtor.Dtos.TransactionRequestDto;
import com.example.digirealtor.Services.TransactionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create/{tenantId}")
    public ResponseEntity<TransactinResponseDto> createTransaction(@RequestBody @Validated TransactionRequestDto transactionDto,
            @PathVariable("tenantId") String tenantId) {
        return new ResponseEntity<TransactinResponseDto>(transactionService.createTransaction(transactionDto, tenantId),
                HttpStatus.CREATED);

    }
    @GetMapping("/all/{tenantId}")
    public ResponseEntity<List<TransactinResponseDto>> getTransactionByTenantId( @PathVariable("tenantId") String tenantId) {
        System.out.println("kjoikop;kpo;lkpo;lkop;lkp;okop;l");
        return new ResponseEntity<List<TransactinResponseDto>>(transactionService.getTransactionByTenantId(tenantId), HttpStatus.OK);

    }
    @GetMapping("/all/{ownerId}")
    public ResponseEntity<List<AnalysisDto>> getTransactionforProperty( @PathVariable("ownerId") String ownerId) {
        System.out.println("mhhhh");
        return new ResponseEntity<List<AnalysisDto>>(transactionService.getTransactionforProperty(ownerId), HttpStatus.OK);

    }

}
