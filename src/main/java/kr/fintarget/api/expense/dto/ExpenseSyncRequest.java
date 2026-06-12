package kr.fintarget.api.expense.dto;

import java.time.LocalDate;

public record ExpenseSyncRequest(
    Long amount,
    String category,
    String description,
    LocalDate spentAt
) {}
