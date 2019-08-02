package com.dismember.infodotav1.Model;

public class Main {
    private String title, image, desc;

    public Main(String title, String image, String desc) {
        this.title = title;
        this.image = image;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Main() {
    }
}
