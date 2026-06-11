package kr.fintarget.api.simulation.dto;

import java.util.UUID;

public record SimulationRequest(
    UUID goalId,
    Long monthlySaving,
    UUID userPolicyId
) {}
