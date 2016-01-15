package com.cmbb.smartkids.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/2 15:04
 */
public class TeletextModel implements Parcelable {


    /**
     * imgUrl : http://smart.image.alimmdn.com/system/image/2015-10-13/servicesImgFile_ZjliNjg2ODgtZWY0NS00NWJmLTlmY2MtMWQwYzVkOTZlMDM4
     * imgWidth : 1242.0
     * imgHeight : 484.0
     * content : 国庆总动员  晒照其祝福
     */

    private List<ImgsEntity> imageFiles;

    public List<ImgsEntity> getImageFiles() {
        return imageFiles;
    }

    public void setImageFiles(List<ImgsEntity> imageFiles) {
        this.imageFiles = imageFiles;
    }

    public static class ImgsEntity implements Parcelable {
        private String filePath;
        private double width;
        private double height;
        private String content;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.filePath);
            dest.writeDouble(this.width);
            dest.writeDouble(this.height);
            dest.writeString(this.content);
        }

        public ImgsEntity() {
        }

        protected ImgsEntity(Parcel in) {
            this.filePath = in.readString();
            this.width = in.readDouble();
            this.height = in.readDouble();
            this.content = in.readString();
        }

        public static final Parcelable.Creator<ImgsEntity> CREATOR = new Parcelable.Creator<ImgsEntity>() {
            public ImgsEntity createFromParcel(Parcel source) {
                return new ImgsEntity(source);
            }

            public ImgsEntity[] newArray(int size) {
                return new ImgsEntity[size];
            }
        };

        @Override
        public String toString() {
            return "ImgsEntity{" +
                    "imgUrl='" + filePath + '\'' +
                    ", imgWidth='" + width + '\'' +
                    ", imgHeight='" + height + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(imageFiles);
    }

    public TeletextModel() {
    }

    protected TeletextModel(Parcel in) {
        this.imageFiles = in.createTypedArrayList(ImgsEntity.CREATOR);
    }

    public static final Parcelable.Creator<TeletextModel> CREATOR = new Parcelable.Creator<TeletextModel>() {
        public TeletextModel createFromParcel(Parcel source) {
            return new TeletextModel(source);
        }

        public TeletextModel[] newArray(int size) {
            return new TeletextModel[size];
        }
    };

    @Override
    public String toString() {
        return "TeletextModel{" +
                "imgs=" + imageFiles +
                '}';
    }
}
