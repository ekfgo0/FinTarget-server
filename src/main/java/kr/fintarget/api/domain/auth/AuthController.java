package kr.fintarget.api.domain.auth;
import kr.fintarget.api.common.ApiResponse;
import kr.fintarget.api.domain.auth.dto.AppleLoginRequest;
import kr.fintarget.api.domain.auth.dto.AuthResponse;
import kr.fintarget.api.domain.auth.dto.KakaoLoginRequest;
import kr.fintarget.api.domain.auth.dto.NaverLoginRequest;
import kr.fintarget.api.domain.user.entity.User;
import kr.fintarget.api.domain.user.repository.UserRepository;
import kr.fintarget.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private ResponseEntity<ApiResponse<AuthResponse>> socialLogin(String providerId, String provider) {
        boolean isNewUser = !userRepository.findByProviderAndProviderId(provider, providerId).isPresent();
        User user = userRepository.findByProviderAndProviderId(provider, providerId)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .provider(provider)
                                .providerId(providerId)
                                .build()
                ));
        String accessToken = jwtUtil.generateToken(user.getId(), provider);
        String refreshToken = jwtUtil.generateRefreshToken(user.getId());
        AuthResponse response = AuthResponse.builder()
                .isNewUser(isNewUser)
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(3600)
                .provider(provider)
                .build();
        return ResponseEntity.status(isNewUser ? 201 : 200).body(
                isNewUser ? ApiResponse.created(response) : ApiResponse.ok(response)
        );
    }
    @PostMapping("/kakao/login")
    public ResponseEntity<ApiResponse<AuthResponse>> kakaoLogin(@RequestBody KakaoLoginRequest request) {
        return socialLogin(request.getAuthorizationCode(), "KAKAO");
    }
    @PostMapping("/naver/login")
    public ResponseEntity<ApiResponse<AuthResponse>> naverLogin(@RequestBody NaverLoginRequest request) {
        return socialLogin(request.getAuthorizationCode(), "NAVER");
    }
    @PostMapping("/apple/login")
    public ResponseEntity<ApiResponse<AuthResponse>> appleLogin(@RequestBody AppleLoginRequest request) {
        return socialLogin(request.getIdentityToken(), "APPLE");
    }
    @PostMapping("/token/refresh")
    public ResponseEntity<ApiResponse<?>> refreshToken(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(401).body(ApiResponse.error(401, "Invalid refresh token"));
        }
        String userId = jwtUtil.getUserId(refreshToken);
        String newAccessToken = jwtUtil.generateToken(userId, "REFRESH");
        return ResponseEntity.ok(ApiResponse.ok(Map.of("accessToken", newAccessToken, "expiresIn", 3600)));
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout() {
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
