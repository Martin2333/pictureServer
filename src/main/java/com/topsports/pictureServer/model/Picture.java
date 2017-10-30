package com.topsports.pictureServer.model;

/**
 * Created by huang.cj on 2017/8/2.
 */
public class Picture {
    private int id;
    private String pictureId;
    private String pictureCode;
    private String categoryName;
    private String brandCode;
    private String pictureType;
    private String keyPicture;
    private String picturePath;
    private String format;
    private String pictureSize;
    private String memorySize;
    private String sourceSystem;
    private String enable;

    public Picture() {}
    public Picture(String pictureId,String pictureCode,String categoryName,String brandCode,
                   String picturePath,String format) {
        this.pictureId = pictureId;
        this.pictureCode = pictureCode;
        this.categoryName = categoryName;
        this.brandCode = brandCode;
        this.picturePath = picturePath;
        this.format = format;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureCode() {
        return pictureCode;
    }

    public void setPictureCode(String pictureCode) {
        this.pictureCode = pictureCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getKeyPicture() {
        return keyPicture;
    }

    public void setKeyPicture(String keyPicture) {
        this.keyPicture = keyPicture;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(String pictureSize) {
        this.pictureSize = pictureSize;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
