package model.response;

import java.util.ArrayList;

public class AddMessageListModel {
    private int errorCount = 0;
    private ArrayList<MessageModel> resultList;

    public int getErrorCount() {
        return errorCount;
    }

    public ArrayList<MessageModel> getResultList() {
        return resultList;
    }
}
