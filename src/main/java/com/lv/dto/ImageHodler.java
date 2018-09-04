package com.lv.dto;

import java.io.InputStream;

public class ImageHodler {

    private InputStream image;
    private String imageName;

    public ImageHodler(InputStream image,String imageName){
        this.image=image;
        this.imageName=imageName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
