package kr.fintarget.api.goal.dto;

import kr.fintarget.api.goal.entity.Goal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record GoalResponse(
    UUID goalId,
    String title,
    Long targetAmount,
    Long currentAmount,
    LocalDate deadline,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static GoalResponse from(Goal goal) {
        return new GoalResponse(
            goal.getGoalId(),
            goal.getTitle(),
            goal.getTargetAmount(),
            goal.getCurrentAmount(),
            goal.getDeadline(),
            goal.getStatus().name(),
            goal.getCreatedAt(),
            goal.getUpdatedAt()
        );
    }
}
