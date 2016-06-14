package shop.entity;

import java.util.Date;

public class shopArticle {
    private Integer arId;

    private String arBody;

    private Integer arClass;

    private String arImage;

    private Integer createUser;

    private Date createTime;

    public Integer getArId() {
        return arId;
    }

    public void setArId(Integer arId) {
        this.arId = arId;
    }

    public String getArBody() {
        return arBody;
    }

    public void setArBody(String arBody) {
        this.arBody = arBody == null ? null : arBody.trim();
    }

    public Integer getArClass() {
        return arClass;
    }

    public void setArClass(Integer arClass) {
        this.arClass = arClass;
    }

    public String getArImage() {
        return arImage;
    }

    public void setArImage(String arImage) {
        this.arImage = arImage == null ? null : arImage.trim();
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}