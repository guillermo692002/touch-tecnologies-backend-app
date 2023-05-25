package biz.touchtechnologies.backendchallanege.application.dto.auth;

public class TokenJWT {
    private String token;

    public TokenJWT() {
    }

    public TokenJWT(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
