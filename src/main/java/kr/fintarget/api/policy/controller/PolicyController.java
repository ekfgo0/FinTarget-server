package kr.fintarget.api.policy.controller;

import kr.fintarget.api.policy.dto.PolicyResponse;
import kr.fintarget.api.policy.dto.UserPolicyCreateRequest;
import kr.fintarget.api.policy.dto.UserPolicyResponse;
import kr.fintarget.api.policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping
    public ResponseEntity<List<PolicyResponse>> getMatchingPolicies(
            @RequestParam int age,
            @RequestParam Long income) {
        return ResponseEntity.ok(policyService.getMatchingPolicies(age, income));
    }

    @GetMapping("/my")
    public ResponseEntity<List<UserPolicyResponse>> getUserPolicies(
            @AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(policyService.getUserPolicies(UUID.fromString(userId)));
    }

    @PostMapping("/my")
    public ResponseEntity<UserPolicyResponse> createUserPolicy(
            @AuthenticationPrincipal String userId,
            @RequestBody UserPolicyCreateRequest request) {
        return ResponseEntity.ok(
            policyService.createUserPolicy(UUID.fromString(userId), request)
        );
    }

    @DeleteMapping("/my/{userPolicyId}")
    public ResponseEntity<Void> deleteUserPolicy(
            @AuthenticationPrincipal String userId,
            @PathVariable UUID userPolicyId) {
        policyService.deleteUserPolicy(UUID.fromString(userId), userPolicyId);
        return ResponseEntity.noContent().build();
    }
}
