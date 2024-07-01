package cn.edu.baiyunu.myapplication.wenxinyiyan;

import java.util.List;

public class YiRequestBody {
    private List<YiMessage> messages;

    public YiRequestBody(List<YiMessage> messages) {
        this.messages = messages;
    }

    public List<YiMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<YiMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "YiRequestBody{" +
                "messages=" + messages +
                '}';
    }
}
