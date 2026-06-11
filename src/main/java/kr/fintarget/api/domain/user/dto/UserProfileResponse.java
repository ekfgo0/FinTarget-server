package kr.fintarget.api.domain.user.dto;
import kr.fintarget.api.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class UserProfileResponse {
    private String userId;
    private String name;
    private String email;
    private String region;
    private String provider;
    public static UserProfileResponse from(User user) {
        return UserProfileResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .region(user.getRegion())
                .provider(user.getProvider())
                .build();
    }
}
