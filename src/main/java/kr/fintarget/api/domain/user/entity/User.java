package kr.fintarget.api.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false, unique = true)
    private String providerId;

    private String name;
    private String email;
    private String region;

    @Column(nullable = false)
    private boolean onboardingCompleted = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void updateProfile(String name, String region) {
        if (name != null) this.name = name;
        if (region != null) this.region = region;
    }

    public void completeOnboarding() {
        this.onboardingCompleted = true;
    }
}