package kr.fintarget.api.policy.dto;

import java.util.UUID;

public record UserPolicyCreateRequest(
    UUID policyId,
    String status
) {}
