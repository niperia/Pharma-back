package ma.emsi.Model;

public class AuthenticationResponse {

    private String token;
    private int userId;

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public AuthenticationResponse(String token, int userId) {
        this.token = token;
        this.userId=userId;
    }

    public String getToken() {
        return token;
    }
}
