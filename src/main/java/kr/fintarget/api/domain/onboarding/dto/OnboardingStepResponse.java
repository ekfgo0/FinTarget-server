package kr.fintarget.api.domain.onboarding.dto;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
@Getter
@Builder
public class OnboardingStepResponse {
    private int step;
    private int totalSteps;
    private double progress;
    private String question;
    private String answerType;
    private List<OptionDto> options;
    private boolean isComplete;
    @Getter
    @Builder
    public static class OptionDto {

        private String label;
        private String value;

    }
}
