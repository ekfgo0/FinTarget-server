package kr.fintarget.api.domain.auth.dto;
import lombok.Getter;
@Getter
public class AppleLoginRequest {
    private String identityToken;
    private String authorizationCode;
    private String fullName;
    private String email;
}
