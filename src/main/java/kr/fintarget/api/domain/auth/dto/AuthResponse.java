package kr.fintarget.api.domain.auth.dto;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class AuthResponse {
    private boolean isNewUser;
    private String userId;
    private String accessToken;
    private String refreshToken;
    private int expiresIn;
    private String provider;
}
