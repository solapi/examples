package model.response;

import java.util.ArrayList;

public class DeleteGroupRes {
    private String groupId;
    private int errorCount;
    private ArrayList<DeleteGroupResult> resultList;

    public String getGroupId() {
        return groupId;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public ArrayList<DeleteGroupResult> getResultList() {
        return resultList;
    }
}
