package cn.edu.baiyunu.myapplication.wenxinyiyan;

/**
 * @ClassName Message
 * @Description
 * @Author kong5
 * @Date 2024/6/30 21:27
 * @Version 1.0
 */
public class YiMessage {
    private String role;
    private String content;

    public YiMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
