package kr.fintarget.api.domain.user;
import kr.fintarget.api.common.ApiResponse;
import kr.fintarget.api.domain.user.dto.UpdateProfileRequest;
import kr.fintarget.api.domain.user.dto.UserProfileResponse;
import kr.fintarget.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getProfile(
            @AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(ApiResponse.ok(userService.getProfile(userId)));
    }
    @PutMapping("/me/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateProfile(
            @AuthenticationPrincipal String userId,
            @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(userService.updateProfile(userId, request)));
    }
}
