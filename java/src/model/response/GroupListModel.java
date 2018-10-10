package model.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class GroupListModel {
    public Map<String, GroupModel> getGroupList() {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, GroupModel>>() {}.getType();
        return gson.fromJson(groupList, mapType);
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getHasNext() {
        return hasNext;
    }

    private JsonObject groupList;
    private int offset;
    private int limit;
    private int hasNext;
}
