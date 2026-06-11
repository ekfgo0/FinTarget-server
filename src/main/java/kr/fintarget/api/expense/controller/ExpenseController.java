package kr.fintarget.api.expense.controller;

import kr.fintarget.api.expense.dto.ExpenseResponse;
import kr.fintarget.api.expense.dto.ExpenseSyncRequest;
import kr.fintarget.api.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/sync")
    public ResponseEntity<Void> syncExpenses(
            @AuthenticationPrincipal String userId,
            @RequestBody List<ExpenseSyncRequest> requests) {
        expenseService.syncExpenses(UUID.fromString(userId), requests);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getExpenses(
            @AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(expenseService.getExpenses(UUID.fromString(userId)));
    }

    @GetMapping("/period")
    public ResponseEntity<List<ExpenseResponse>> getExpensesByPeriod(
            @AuthenticationPrincipal String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(
            expenseService.getExpensesByPeriod(UUID.fromString(userId), start, end)
        );
    }
}
