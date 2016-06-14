package shop.entity;

import java.util.Date;

public class shopComment {
    private Integer comId;

    private String comBody;

    private Integer comUser;

    private Integer comAr;

    private Date comTime;

    private Integer comTo;

    private Integer comReply;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getComBody() {
        return comBody;
    }

    public void setComBody(String comBody) {
        this.comBody = comBody == null ? null : comBody.trim();
    }

    public Integer getComUser() {
        return comUser;
    }

    public void setComUser(Integer comUser) {
        this.comUser = comUser;
    }

    public Integer getComAr() {
        return comAr;
    }

    public void setComAr(Integer comAr) {
        this.comAr = comAr;
    }

    public Date getComTime() {
        return comTime;
    }

    public void setComTime(Date comTime) {
        this.comTime = comTime;
    }

    public Integer getComTo() {
        return comTo;
    }

    public void setComTo(Integer comTo) {
        this.comTo = comTo;
    }

    public Integer getComReply() {
        return comReply;
    }

    public void setComReply(Integer comReply) {
        this.comReply = comReply;
    }
}