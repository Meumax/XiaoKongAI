package cn.edu.baiyunu.myapplication.entities;

public class MyMessage {

    private String time;
    private String text;
    private boolean isUser;

    public MyMessage(String date, String text, boolean isUser) {
        this.time = date;
        this.text = text;
        this.isUser = isUser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public boolean isUser() {
        return isUser;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "time='" + time + '\'' +
                ", text='" + text + '\'' +
                ", isUser=" + isUser +
                '}';
    }
}

