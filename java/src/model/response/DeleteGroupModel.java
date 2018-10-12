package model.response;

import java.util.ArrayList;

public class DeleteGroupModel {
    private String groupId;
    private int errorCount;
    private ArrayList<DeleteGroupResultModel> resultList;

    public String getGroupId() {
        return groupId;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public ArrayList<DeleteGroupResultModel> getResultList() {
        return resultList;
    }
}
