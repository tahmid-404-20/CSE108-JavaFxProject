package util;

import java.io.Serializable;

public class Message implements Serializable {
    private String from;
    private String text;

    public Message() {
    }

    public String getFrom() {
        return from;
    }
    public String getText() {
        return text;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public void setText(String text) {
        this.text = text;
    }
}
