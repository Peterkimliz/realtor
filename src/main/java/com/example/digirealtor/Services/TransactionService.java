package com.example.digirealtor.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.AnalysisDto;
import com.example.digirealtor.Dtos.TransactinResponseDto;
import com.example.digirealtor.Dtos.TransactionRequestDto;
import com.example.digirealtor.Exceptions.NotFoundException;
import com.example.digirealtor.Models.Tenant;
import com.example.digirealtor.Models.Transaction;
import com.example.digirealtor.Repositories.TenantRepository;
import com.example.digirealtor.Repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TenantRepository tenantRepository;

    public TransactinResponseDto createTransaction(TransactionRequestDto transactionDto, String tenantId) {
         Optional<Tenant> tenant = tenantRepository.findById(tenantId);
        if (!tenant.isPresent()) {
            throw new NotFoundException("Tenant not found in the system");
        }

        Transaction transaction = Transaction.builder().amount(transactionDto.getAmount())
                .createdAt(new Date(System.currentTimeMillis()))
                .propertyId(transactionDto.getPropertyId())
                .transactionId(transactionDto.getTransactionId())
                .tenant(tenant.get())
                .build();
        transactionRepository.save(transaction);

        return mapToTransactionDto(transaction);
    }

    private TransactinResponseDto mapToTransactionDto(Transaction transaction) {
        return TransactinResponseDto.builder()
                .createdAt(transaction.getCreatedAt())
                .tenant(transaction.getTenant())
                .id(transaction.getId())
                .transactionRequestDto(TransactionRequestDto.builder().amount(transaction.getAmount())
                        .propertyId(transaction.getPropertyId()).transactionId(transaction.getTransactionId()).build())
                .build();
    }

    public List<TransactinResponseDto> getTransactionByTenantId(String tenantId) {
        List<Transaction> transactions = transactionRepository.findByTenant(tenantId);
        if (transactions.size() > 0) {
          return transactions.stream().map((e) -> mapToTransactionDto(e)).toList();
        } else {
            return new ArrayList<>();
        }

    }

    public List<AnalysisDto> getTransactionforProperty(String propertyId) {
        return null;
    }

}
