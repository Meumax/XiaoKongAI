package cn.edu.baiyunu.myapplication.wenxinyiyan;

public class YiRespondBody {
    private String id;
    private String object;
    private int created;

    private int sentence_id;

    private boolean is_end;

    private boolean is_truncated;

    private String result;

    private boolean need_clear_history;
    private int ban_round;
    private YiUsage usage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getSentence_id() {
        return sentence_id;
    }

    public void setSentence_id(int sentence_id) {
        this.sentence_id = sentence_id;
    }

    public boolean isIs_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }

    public boolean isIs_truncated() {
        return is_truncated;
    }

    public void setIs_truncated(boolean is_truncated) {
        this.is_truncated = is_truncated;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isNeed_clear_history() {
        return need_clear_history;
    }

    public void setNeed_clear_history(boolean need_clear_history) {
        this.need_clear_history = need_clear_history;
    }

    public int getBan_round() {
        return ban_round;
    }

    public void setBan_round(int ban_round) {
        this.ban_round = ban_round;
    }

    public YiUsage getUsage() {
        return usage;
    }

    public void setUsage(YiUsage usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "WenxinYiyanRespondBody{" +
                "id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", created=" + created +
                ", sentence_id=" + sentence_id +
                ", is_end=" + is_end +
                ", is_truncated=" + is_truncated +
                ", result='" + result + '\'' +
                ", need_clear_history=" + need_clear_history +
                ", ban_round=" + ban_round +
                ", usage=" + usage +
                '}';
    }
}
