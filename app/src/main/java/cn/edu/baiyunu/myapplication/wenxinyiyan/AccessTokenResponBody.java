package cn.edu.baiyunu.myapplication.wenxinyiyan;

public class AccessTokenResponBody {
    private String refresh_token;
    private String expires_in;
    private String session_key;
    private String access_token;
    private String scope;
    private String session_secret;

    public AccessTokenResponBody(String refresh_token, String expires_in, String session_key, String access_token, String scope, String session_secret) {
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.session_key = session_key;
        this.access_token = access_token;
        this.scope = scope;
        this.session_secret = session_secret;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSession_secret() {
        return session_secret;
    }

    public void setSession_secret(String session_secret) {
        this.session_secret = session_secret;
    }
}
