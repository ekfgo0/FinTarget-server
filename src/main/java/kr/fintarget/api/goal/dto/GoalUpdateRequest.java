package kr.fintarget.api.goal.dto;

import java.time.LocalDate;

public record GoalUpdateRequest(
    String title,
    Long targetAmount,
    Long currentAmount,
    LocalDate deadline
) {}
