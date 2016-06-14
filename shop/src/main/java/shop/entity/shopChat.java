package shop.entity;

import java.util.Date;

public class shopChat {
    private Integer chatId;

    private String chatBody;

    private Integer chatFrom;

    private Integer chatTo;

    private Date chatTime;

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getChatBody() {
        return chatBody;
    }

    public void setChatBody(String chatBody) {
        this.chatBody = chatBody == null ? null : chatBody.trim();
    }

    public Integer getChatFrom() {
        return chatFrom;
    }

    public void setChatFrom(Integer chatFrom) {
        this.chatFrom = chatFrom;
    }

    public Integer getChatTo() {
        return chatTo;
    }

    public void setChatTo(Integer chatTo) {
        this.chatTo = chatTo;
    }

    public Date getChatTime() {
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }
}