package kr.fintarget.api.domain.onboarding;
import kr.fintarget.api.common.ApiResponse;
import kr.fintarget.api.domain.onboarding.dto.OnboardingAnswerRequest;
import kr.fintarget.api.domain.onboarding.dto.OnboardingStepResponse;
import kr.fintarget.api.domain.onboarding.service.OnboardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/onboarding")
@RequiredArgsConstructor
public class OnboardingController {
    private final OnboardingService onboardingService;
    @GetMapping("/step")
    public ResponseEntity<ApiResponse<OnboardingStepResponse>> getStep(
            @RequestParam(defaultValue = "1") int step) {
        return ResponseEntity.ok(ApiResponse.ok(onboardingService.getStep(step)));
    }
    @PostMapping("/answer")
    public ResponseEntity<ApiResponse<OnboardingStepResponse>> submitAnswer(
            @AuthenticationPrincipal String userId,
            @RequestBody OnboardingAnswerRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(onboardingService.submitAnswer(userId, request)));
    }
}
