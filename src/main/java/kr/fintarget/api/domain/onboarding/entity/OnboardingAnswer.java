package kr.fintarget.api.domain.onboarding.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "onboarding_answer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OnboardingAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "onboarding_id")
    private String id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "step", nullable = false)
    private Integer step;

    @Column(name = "value", nullable = false)
    private String value;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public OnboardingAnswer(UUID userId, Integer step, String value) {
        this.userId = userId;
        this.step = step;
        this.value = value;
    }
}