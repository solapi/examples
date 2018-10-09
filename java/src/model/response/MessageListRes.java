package model.response;

import java.util.ArrayList;

public class MessageListRes {
    private int errorCount = 0;
    private ArrayList<MessageRes> resultList;

    public int getErrorCount() {
        return errorCount;
    }

    public ArrayList<MessageRes> getResultList() {
        return resultList;
    }
}
