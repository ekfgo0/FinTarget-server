package kr.fintarget.api.domain.auth.dto;
import lombok.Getter;
@Getter
public class KakaoLoginRequest {
    private String authorizationCode;
    private String redirectUri;
}
