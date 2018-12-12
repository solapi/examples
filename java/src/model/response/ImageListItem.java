package model.response;

public class ImageListItem {
    File file;
    boolean delflag;
    String accountId;
    String dateCreated;
    String dateUpdated;
    String imageId;

    public String getFileName() {
        return file.getName();
    }

    public long getFileSize() {
        return file.getSize();
    }

    public boolean getDelFlag() {
        return delflag;
    }

    public String getImageId() {
        return imageId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }
}
