package kr.fintarget.api.domain.user.dto;
import lombok.Getter;
@Getter
public class UpdateProfileRequest {
    private String name;
    private String email;
    private String region;
}