package model.response;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class AddMessageModel {
    private String _id;
    private JsonObject kakaoOptions;
    private String type;
    private String country;
    private String subject;
    private String imageId;
    private String dateProcessed;
    private String dateReported;
    private String dateReceived;
    private String statusCode;
    private String networkCode;
    private ArrayList<LogModel> log;
    private String replacement;
    private String autoTypeDetect;
    private String messageId;
    private String groupId;
    private String accountId;
    private String text;
    private String from;
    private String to;
    private JsonObject customFields;
    private String dateCreated;
    private String dateUpdated;
    private String reason;
    private String networkName;

    public String get_id() {
        return _id;
    }

    public JsonObject getKakaoOptions() {
        return kakaoOptions;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public String getSubject() {
        return subject;
    }

    public String getImageId() {
        return imageId;
    }

    public String getDateProcessed() {
        return dateProcessed;
    }

    public String getDateReported() {
        return dateReported;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getNetworkCode() {
        return networkCode;
    }

    public ArrayList<LogModel> getLog() {
        return log;
    }

    public String getReplacement() {
        return replacement;
    }

    public String getAutoTypeDetect() {
        return autoTypeDetect;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getText() {
        return text;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public JsonObject getCustomFields() {
        return customFields;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public String getReason() {
        return reason;
    }

    public String getNetworkName() {
        return networkName;
    }
}
