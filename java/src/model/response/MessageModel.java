package model.response;

public class MessageModel {
    private String groupId;
    private String messageId;
    private String statusCode;
    private String statusMessage;
    private String to;
    private String type;
    private String from;
    private String customFields;
    private String country;
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public String getCountry() {
        return country;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getTo() {
        return to;
    }

    public String getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public String getCustomFields() {
        return customFields;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getMessageId() {
        return messageId;
    }

}
