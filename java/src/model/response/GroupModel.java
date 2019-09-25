package model.response;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class GroupModel {
    private ArrayList<JsonObject> log;
    private JsonObject agent;
    private JsonObject count;
    private String accountId;
    private String apiVersion;
    private String groupId;
    private String dateCreated;
    private String dateUpdated;
    private String _id;
    private String status;

    public ArrayList<JsonObject> getLog() {
        return log;
    }

    public JsonObject getAgent() {
        return agent;
    }

    public JsonObject getCount() {
        return count;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public String get_id() {
        return _id;
    }

    public String getStatus() {
        return status;
    }
}
