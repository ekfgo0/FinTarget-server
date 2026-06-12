package kr.fintarget.api.domain.nudge.dto;
import kr.fintarget.api.domain.nudge.entity.Nudge;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
@Getter
@Builder
public class NudgeResponse {
    private String nudgeId;
    private String type;
    private String message;
    private String relatedCategory;
    private Long savedAmount;
    private Integer dDayShortenIfActed;
    private LocalDateTime createdAt;
    private boolean isRead;
    public static NudgeResponse from(Nudge nudge) {
        return NudgeResponse.builder()
                .nudgeId(nudge.getId())
                .type(nudge.getType())
                .message(nudge.getMessage())
                .relatedCategory(nudge.getRelatedCategory())
                .savedAmount(nudge.getSavedAmount())
                .dDayShortenIfActed(nudge.getDDayShortenIfActed())
                .createdAt(nudge.getCreatedAt())
                .isRead(nudge.isRead())
                .build();
    }
}
