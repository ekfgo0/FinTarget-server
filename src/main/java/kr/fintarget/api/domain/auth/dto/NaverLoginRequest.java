package kr.fintarget.api.domain.auth.dto;
import lombok.Getter;
@Getter
public class NaverLoginRequest {
    private String authorizationCode;
    private String state;
}
