package model.response;

import java.util.ArrayList;

public class AddMessageListRes {
    private int errorCount = 0;
    private ArrayList<MessageModel> resultList;

    public int getErrorCount() {
        return errorCount;
    }

    public ArrayList<MessageModel> getResultList() {
        return resultList;
    }
}
