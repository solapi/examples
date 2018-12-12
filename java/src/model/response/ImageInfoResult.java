package model.response;

public class ImageInfoResult {
    String imageId;
    File file;

    public String getImageId() {
        return imageId;
    }

    public String getFileName() {
        return file.getName();
    }

    public long getFileSize() {
        return file.getSize();
    }

}

class File {
    String name;
    long size;

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
