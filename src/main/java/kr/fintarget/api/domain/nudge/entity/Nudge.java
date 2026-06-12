package kr.fintarget.api.domain.nudge.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "nudges")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Nudge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false, length = 500)
    private String message;
    private String relatedCategory;
    private Long savedAmount;
    private Integer dDayShortenIfActed;
    @Column(nullable = false)
    private boolean isRead;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
    }
    public void markAsRead() {
        this.isRead = true;
    }
}
